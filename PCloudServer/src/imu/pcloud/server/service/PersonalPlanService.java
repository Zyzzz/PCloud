package imu.pcloud.server.service;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import imu.pcloud.server.DAO.PersonalPlanDAO;
import imu.pcloud.server.been.PersonalPlan;

public class PersonalPlanService {
	
	PersonalPlanDAO personalPlanDao = new PersonalPlanDAO();
	PersonalPlan personalPlan = new PersonalPlan();
	List<PersonalPlan> personalPlanList = new ArrayList();
	
	public PersonalPlan getPersonalPlan() {
		return personalPlan;
	}

	public void setPersonalPlan(PersonalPlan personalPlan) {
		this.personalPlan = personalPlan;
	}

	public List<PersonalPlan> getPersonalPlanList() {
		return personalPlanList;
	}

	public void setPersonalPlanList(List<PersonalPlan> personalPlanList) {
		this.personalPlanList = personalPlanList;
	}

	public int addPlan(String content,String name,Integer userId){
		personalPlan.setContent(content); 
		personalPlan.setName(name);
		personalPlan.setUserId(userId);
		personalPlanDao.save(personalPlan);
		return 0;
	}
	
	public int addPlanHasImage(String content,String name,Integer userId,Integer coverImageId){
		personalPlan.setContent(content); 
		personalPlan.setName(name);
		personalPlan.setUserId(userId);
		personalPlan.setCoverImageId(coverImageId);
		personalPlanDao.save(personalPlan);
		return 0;
	}
	
	public int modify(Integer id,String content,String name){
		personalPlan = personalPlanDao.findById(id);
		personalPlan.setContent(content);
		personalPlan.setName(name);
		personalPlanDao.save(personalPlan);
		return 0;
	}
	
	public int deletePlan(Integer id){		
		personalPlan = personalPlanDao.findById(id);
		personalPlanDao.delete(personalPlan);
		return 0;
	}
		
	public int getPlanID(String content,String name,Integer userId){
		personalPlan.setContent(content);
		personalPlan.setUserId(userId);
		personalPlan.setName(name);
		personalPlanList = personalPlanDao.findByExample(personalPlan);
		Integer id = personalPlanList.get(0).getId();
	   return id;
	}
	
	public int getPlanInfo(Integer id){
		personalPlan = personalPlanDao.findById(id);
		return 0;
	}
	
	public List getPlanList(Integer userId){
		personalPlanList = personalPlanDao.findByUserId(userId);
		return personalPlanList;
	}
}
