package imu.pcloud.server.service;
import java.util.List;
import imu.pcloud.server.DAO.PersonalPlanDAO;
import imu.pcloud.server.been.PersonalPlan;

public class PersonalPlanService {
	
	PersonalPlanDAO persnoalPlanDao = new PersonalPlanDAO();
	PersonalPlan persnoalPlan = new PersonalPlan();
	List<PersonalPlan> personalPlanList;
	
	public int addPlan(String content,String name,Integer userId){
		persnoalPlan.setContent(content); 
		persnoalPlan.setName(name);
		persnoalPlan.setUserId(userId);
		persnoalPlanDao.save(persnoalPlan);
		personalPlanList.add(persnoalPlan);
		return 0;
	}
	
	public int addPlanHasImage(String content,String name,Integer userId,Integer coverImageId){
		persnoalPlan.setContent(content); 
		persnoalPlan.setName(name);
		persnoalPlan.setUserId(userId);
		persnoalPlan.setCoverImageId(coverImageId);
		persnoalPlanDao.save(persnoalPlan);
		personalPlanList.add(persnoalPlan);
		return 0;
	}
	
	public int deletePlan(String content,String name,Integer userId){
		
		return 0;
	}
	
	public List getPlanList(Integer userId){
		
		personalPlanList = persnoalPlanDao.findByUserId(userId);
		return personalPlanList;
	}
}
