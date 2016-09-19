package imu.pcloud.server.service;

import imu.pcloud.server.DAO.CommentDAO;
import imu.pcloud.server.DAO.PersonalPlanDAO;
import imu.pcloud.server.DAO.PlanCircleDAO;
import imu.pcloud.server.DAO.SharingRecordDAO;
import imu.pcloud.server.DAO.UserDAO;
import imu.pcloud.server.been.Discover;
import imu.pcloud.server.been.PersonalPlan;
import imu.pcloud.server.been.PlanCircle;
import imu.pcloud.server.been.SharingRecord;
import imu.pcloud.server.been.User;

import java.util.ArrayList;

public class DiscoverService {
	
	CommentDAO commentDAO = new CommentDAO();
	SharingRecordDAO sharingRecordDAO = new SharingRecordDAO();
	UserDAO userDAO = new UserDAO();
	PersonalPlanDAO personalPlanDAO = new PersonalPlanDAO();
	PlanCircleDAO planCircleDAO = new PlanCircleDAO();
	ArrayList<Discover> discoverList;
	public ArrayList<Discover> findAll() {
		discoverList = new ArrayList<Discover>();
		ArrayList<SharingRecord> sharingRecords = (ArrayList<SharingRecord>) sharingRecordDAO.findAll();
		for(SharingRecord sharingRecord : sharingRecords) {
			insert(sharingRecord);
		}
		return discoverList;
	}
	
	public ArrayList<Discover> findByUserId(int id) {
		discoverList = new ArrayList<Discover>();
		SharingRecord sh = new SharingRecord();
		sh.setUserId(id);
		ArrayList<SharingRecord> sharingRecords = (ArrayList<SharingRecord>) sharingRecordDAO.findByExample(sh);
		for(SharingRecord sharingRecord : sharingRecords) {
			insert(sharingRecord);
		}
		return discoverList;
		
	}
	
	public void insert(SharingRecord sharingRecord) {
//		for(Discover d: discoverList) {
//			if(sharingRecord.getId().getPersonalPlanId() == d.getPersonalPlan().getId()) {
//				PlanCircle pc = planCircleDAO.findById(sharingRecord.getId().getPlanCircleId());
//				d.getPlanCircles().add(pc);
//				return;
//			}
//		}
		discoverList.add(findInfo(sharingRecord));
	}
	
	public Discover findInfo(SharingRecord sharingRecord) {
		Discover discover = new Discover();
		User u = userDAO.findById(sharingRecord.getUserId());
		u.cleanPrivateInfo();
		PersonalPlan p = personalPlanDAO.findById(sharingRecord.getId().getPersonalPlanId());
		PlanCircle pc = planCircleDAO.findById(sharingRecord.getId().getPlanCircleId());
		long commentTime = commentDAO.findByPersonalPlanId(sharingRecord.getId().getPersonalPlanId()).size();
		discover.setCommentTime(commentTime);
		discover.setPersonalPlan(p);
		discover.setUser(u);
		discover.setPlanCircle(pc);;
		discover.setSharingRecord(sharingRecord);
		return discover;
	}
}
