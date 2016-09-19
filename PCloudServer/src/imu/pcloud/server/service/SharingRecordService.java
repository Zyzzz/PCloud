package imu.pcloud.server.service;


import java.util.List;
import imu.pcloud.server.DAO.PersonalPlanDAO;
import imu.pcloud.server.DAO.SharingRecordDAO;
import imu.pcloud.server.DAO.UserDAO;
import imu.pcloud.server.been.PersonalPlan;
import imu.pcloud.server.been.SharingRecord;
import imu.pcloud.server.been.SharingRecordId;
import imu.pcloud.server.been.User;

public class SharingRecordService {
	
	SharingRecordDAO sharingRecordDAO = new SharingRecordDAO();
	SharingRecord sharingRecord = new SharingRecord();
	SharingRecordId sharingRecordId = new SharingRecordId();
	UserDAO userDAO = new UserDAO();
	PersonalPlanDAO personalPlanDAO = new PersonalPlanDAO();
	List<SharingRecord> sharingRecords;
	
	
	public SharingRecord getSharingRecord() {
		return sharingRecord;
	}

	public void setSharingRecord(SharingRecord sharingRecord) {
		this.sharingRecord = sharingRecord;
	}

	public List<SharingRecord> getSharingRecords() {
		return sharingRecords;
	}

	public void setSharingRecords(List<SharingRecord> sharingRecords) {
		this.sharingRecords = sharingRecords;
	}

	public int Sharing(Integer personalPlanId,Integer planCircleId,String cookies){
		User user = new User();
		user.setCookies(cookies);
		List<User> users = userDAO.findByExample(user);
		if(!users.isEmpty()){
			sharingRecordId.setPersonalPlanId(personalPlanId);
			sharingRecordId.setPlanCircleId(planCircleId);
			if(sharingRecordDAO.findById(sharingRecordId) != null)
				return 305;
			sharingRecord.setUserId(users.get(0).getId());
			sharingRecord.setId(sharingRecordId);
			sharingRecord.setLoadingTime(0);
			sharingRecordDAO.save(sharingRecord);
			return 0;
		}
		else {
			return 300;
		}
	}
	
	public int Sharing(
			Integer personalPlanId,
			Integer planCircleId, 
			String describe, 
			String cookies) {
		User user = new User();
		user.setCookies(cookies);
		List<User> users = userDAO.findByExample(user);
		if(!sharingRecordDAO.findByPersonalPlanId(personalPlanId).isEmpty()) {
			return 305;
		}
		if(!users.isEmpty()){
			sharingRecordId.setPersonalPlanId(personalPlanId);
			sharingRecordId.setPlanCircleId(planCircleId);
			sharingRecord.setUserId(users.get(0).getId());
			sharingRecord.setId(sharingRecordId);
			sharingRecord.setLoadingTime(0);
			sharingRecord.setDiscribe(describe);
			sharingRecordDAO.save(sharingRecord);
			return 0;
		}
		else {
			return 300;
		}
	}
	
	public int getSharingList(Integer planCircleId){
		sharingRecords = sharingRecordDAO.findByPlanCircleId(planCircleId);
		return 0;
	}
	
	public int getUserSharingList(String cookies) {
		User user = new User();
		user.setCookies(cookies);
		List<User> users = userDAO.findByExample(user);
		if(!users.isEmpty()){
			sharingRecord.setUserId(users.get(0).getId());
			sharingRecords = sharingRecordDAO.findByExample(sharingRecord);
			return 0;
		}
		else {
			return 300;
		}
	}
	
	public int deleteSharing(Integer personalPlanId,Integer planCircleId){
			sharingRecordId.setPersonalPlanId(personalPlanId);
			sharingRecordId.setPlanCircleId(planCircleId);
			sharingRecord = sharingRecordDAO.findById(sharingRecordId);
			sharingRecordDAO.delete(sharingRecord);
			return 0;
		
	}
	public int sharingDownloan(Integer personalPlanId,Integer planCircleId){
		sharingRecordId.setPersonalPlanId(personalPlanId);
		sharingRecordId.setPlanCircleId(planCircleId);
		sharingRecord = sharingRecordDAO.findById(sharingRecordId);
		int loadingTime = sharingRecord.getLoadingTime();
		loadingTime++;
		sharingRecord.setLoadingTime(loadingTime);
		sharingRecordDAO.save(sharingRecord);
		return 0;
	}
	
	
}
