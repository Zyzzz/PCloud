package imu.pcloud.server.service;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import imu.pcloud.server.DAO.PlanCircleDAO;
import imu.pcloud.server.been.PlanCircle;

public class PlanCircleService {

	private PlanCircleDAO planCircleDAO = new PlanCircleDAO();
	private List<PlanCircle> planCircles;
	private PlanCircle planCircle;

	
	public List<PlanCircle> getPlanCircles() {
		return planCircles;
	}


	public void setPlanCircles(List<PlanCircle> planCircles) {
		this.planCircles = planCircles;
	}


	public PlanCircle getPlanCircle() {
		return planCircle;
	}


	public void setPlanCircle(PlanCircle planCircle) {
		this.planCircle = planCircle;
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
	
	public int getPlanCircleList(){
		planCircles	= planCircleDAO.findAll();
		return 0;
	}
}
