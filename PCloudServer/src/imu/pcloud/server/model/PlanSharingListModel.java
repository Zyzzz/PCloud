package imu.pcloud.server.model;

import imu.pcloud.server.been.PersonalPlan;
import imu.pcloud.server.been.SharingRecord;

import java.util.ArrayList;
import java.util.List;

public class PlanSharingListModel extends BaseModel {
	List<SharingRecord> sharingRecords = new ArrayList();
	List<PersonalPlan> personalPlans = new ArrayList();
	public List<SharingRecord> getSharingRecords() {
		return sharingRecords;
	}
	public void setSharingRecords(List<SharingRecord> sharingRecords) {
		this.sharingRecords = sharingRecords;
	}
	public List<PersonalPlan> getPersonalPlans() {
		return personalPlans;
	}
	public void setPersonalPlans(List<PersonalPlan> personalPlans) {
		this.personalPlans = personalPlans;
	}
	
}
