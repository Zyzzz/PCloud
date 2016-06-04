package imu.pcloud.server.service;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import imu.pcloud.server.DAO.MultiPlanDAO;
import imu.pcloud.server.DAO.MultiPlanMemberDAO;
import imu.pcloud.server.been.MultiPlan;
import imu.pcloud.server.been.MultiPlanMember;

public class MultiPlanService {

	private	MultiPlan multiPlan = new MultiPlan();
	private MultiPlanMember multiPlanMember = new MultiPlanMember();
	private MultiPlanDAO multiPlanDAO = new MultiPlanDAO();
	private MultiPlanMemberDAO multiPlanMemberDAO = new MultiPlanMemberDAO();
	private List<MultiPlan> multiPlans = new ArrayList<>();
	private List<MultiPlanMember> multiPlanMembers = new ArrayList<>();
	
	public List<MultiPlanMember> getMultiPlanMembers() {
		return multiPlanMembers;
	}
	public void setMultiPlanMembers(List<MultiPlanMember> multiPlanMembers) {
		this.multiPlanMembers = multiPlanMembers;
	}
	public MultiPlan getMultiPlan() {
		return multiPlan;
	}
	public void setMultiPlan(MultiPlan multiPlan) {
		this.multiPlan = multiPlan;
	}
	public List<MultiPlan> getMultiPlans() {
		return multiPlans;
	}
	public void setMultiPlans(List<MultiPlan> multiPlans) {
		this.multiPlans = multiPlans;
	}
		
	public int addMultiPlan(Integer userId,String name,String content,
	Integer maxmumber, Integer condition){
		multiPlan = new MultiPlan();
		multiPlan.setUserId(userId);
		multiPlan.setName(name);
		multiPlan.setContent(content);
		multiPlan.setMaxmumber(maxmumber);
		multiPlan.setUserCondition(condition);
		multiPlanDAO.save(multiPlan);
		//multiPlans = multiPlanDAO.findByExample(multiPlan);
		//System.out.print("multiPlan.getId():"+multiPlan.getId());
		multiPlanMember.setMultiPlanId(multiPlan.getId());
		multiPlanMember.setUserId(userId);
		multiPlanMemberDAO.save(multiPlanMember);
		return 0;
	}
	 
	public int joinMultiPlan(Integer userId,Integer multiPlanId){
		multiPlanMembers = multiPlanMemberDAO.findByUserId(userId);
		for(MultiPlanMember var:multiPlanMembers){
			if(var.getMultiPlanId() == multiPlanId){
				return 601;
			}
		}
		multiPlanMember.setMultiPlanId(multiPlanId);
		multiPlanMember.setUserId(userId);
		multiPlanMemberDAO.save(multiPlanMember);
		return 0;
	}
	
	public int getMultiPlanByMultiPlanId(Integer multiPlanId){
		multiPlan = multiPlanDAO.findById(multiPlanId);
		return 0 ;
	}
	
	public int getMultiPlanListByBlurryName(String blurryName){
		multiPlans = multiPlanDAO.findBybBlurryName(blurryName);
		return 0 ;
	}
	
	public int getMultiPlanListByUserId(Integer userId){
		multiPlanMembers = multiPlanMemberDAO.findByUserId(userId);
		return 0;
	}
	
	public int quitMultiPlanList(Integer userId,Integer multiPlanId){
		multiPlanMember.setMultiPlanId(multiPlanId);
		multiPlanMember.setUserId(userId);
		multiPlanMemberDAO.delete(multiPlanMember);
		return 0;
	}
}
