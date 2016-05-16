package imu.pcloud.server.service;

import java.util.List;

import imu.pcloud.server.DAO.PlanCircleDAO;
import imu.pcloud.server.been.PlanCircle;

public class PlanCircleService {

	private PlanCircleDAO planCircleDAO = new PlanCircleDAO();
	private List<PlanCircle> planCircleList;
	private PlanCircle planCircle;
	public List<PlanCircle> getPlanCircleList() {
		return planCircleList;
	}
	public void setPlanCircleList(List<PlanCircle> planCircleList) {
		this.planCircleList = planCircleList;
	}
	public PlanCircle getPlanCircle() {
		return planCircle;
	}
	public void setPlanCircle(PlanCircle planCircle) {
		this.planCircle = planCircle;
	}
	
	public int findAllPlanCircle() {
		planCircleList = planCircleDAO.findAll();
		return 0;
	}
	
	public int addPlanCircle(String name) {
		planCircle.setName(name);
		planCircleDAO.save(planCircle);
		return 0;
	}
	
	public int findPlanCircle(int id) {
		planCircle = planCircleDAO.findById(id);
		return 0;
	}
}
