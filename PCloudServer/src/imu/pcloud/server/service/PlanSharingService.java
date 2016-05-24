package imu.pcloud.server.service;

import java.util.ArrayList;
import java.util.List;

import imu.pcloud.server.DAO.PersonalPlanDAO;
import imu.pcloud.server.DAO.SharingRecordDAO;
import imu.pcloud.server.been.PersonalPlan;
import imu.pcloud.server.been.SharingRecord;
import imu.pcloud.server.model.PlanSharingListModel;

public class PlanSharingService {

	PlanSharingListModel planSharingListModel = new PlanSharingListModel();
	PersonalPlanDAO personalPlanDAO = new PersonalPlanDAO();
	SharingRecordDAO sharingRecordDAO = new SharingRecordDAO();
	
	public void findPersonalSharing(int userId) {
		List<PersonalPlan> personalPlans = personalPlanDAO.findByUserId(userId);
		SharingRecord sharingRecord = new SharingRecord();
		sharingRecord.setUserId(userId);
		List<SharingRecord> sharingRecords = sharingRecordDAO.findByExample(sharingRecord);
		if(personalPlans.isEmpty())
			planSharingListModel.setStatus(401);
		for(int i = 0; i < personalPlans.size(); i++) {
			int q = 0;
			for(q = 0; q < sharingRecords.size(); q++) {
				if(sharingRecords.get(q).getUserId() == personalPlans.get(i).getUserId())
					break;
			}
			if(q != sharingRecords.size())
				planSharingListModel.getPersonalPlans().add(personalPlans.get(i));
		}
		planSharingListModel.setSharingRecords(sharingRecords);
		planSharingListModel.setStatus(400);
	}

	public PlanSharingListModel getPlanSharingListModel() {
		return planSharingListModel;
	}

	public void setPlanSharingListModel(PlanSharingListModel planSharingListModel) {
		this.planSharingListModel = planSharingListModel;
	}
	
	
	
}
