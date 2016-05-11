package imu.pcloud.server.service;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import imu.pcloud.server.DAO.PlanCircleDAO;
import imu.pcloud.server.been.PlanCircle;

public class PlanCircleService {

	PlanCircleDAO planCircleDAO = new PlanCircleDAO();
	PlanCircle planCircle = new PlanCircle();
	List<PlanCircle> planCircles = new ArrayList();
	
	public PlanCircle getPlanCircle() {
		return planCircle;
	}
	public void setPlanCircle(PlanCircle planCircle) {
		this.planCircle = planCircle;
	}
	public List<PlanCircle> getPlanCircles() {
		return planCircles;
	}
	public void setPlanCircles(List<PlanCircle> planCircles) {
		this.planCircles = planCircles;
	}
	
	public int getPlanCircleList(){
		planCircles	= planCircleDAO.findAll();
		return 0;
	}
}
