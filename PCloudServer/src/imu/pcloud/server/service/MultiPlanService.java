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
		multiPlan = multiPlanDAO.findById(multiPlanId);

		multiPlanMembers = multiPlanMemberDAO.findByUserId(userId);	
		if(multiPlan.getMaxmumber()==multiPlanMembers.size()){
			return 604;
		}
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
		multiPlanMembers = multiPlanMemberDAO.findByMultiPlanId(multiPlanId);
		return 0;
	}
	
	public int getMultiPlanListByBlurryName(String blurryName){
		multiPlans = multiPlanDAO.findBybBlurryName(blurryName);
		return 0;
	}
	
	
	public int getMultiPlanMembers(Integer multiPlanId){
		multiPlanMembers = multiPlanMemberDAO.findByMultiPlanId(multiPlanId);
		return 0;
	}
	public int getMultiPlanListByUserId(Integer userId){
		multiPlans.clear();
		multiPlanMembers = multiPlanMemberDAO.findByUserId(userId);
		List<MultiPlan> l = multiPlanDAO.findAll();
		for(MultiPlanMember var1:multiPlanMembers){
			for(MultiPlan var2:l){
				if(var1.getMultiPlanId()==var2.getId()){
					multiPlans.add(var2);
					break;
				}
			}
		}
		return 0;
	}
	
	public int quitMultiPlanList(Integer userId,Integer multiPlanId){
		multiPlanMember.setMultiPlanId(multiPlanId);
		multiPlanMember.setUserId(userId);
		multiPlanMemberDAO.delete(multiPlanMember);
		return 0;
	}
	public int modfiyMultiPlan(Integer multiPlanId,String name,String content,
			Integer maxmumber){
		multiPlan = multiPlanDAO.findById(multiPlanId);
		getMultiPlanMembers(multiPlanId);
		if(maxmumber<multiPlanMembers.size()){
			return 603;
		}
		multiPlan.setContent(content);
		multiPlan.setMaxmumber(maxmumber);
		multiPlan.setName(name);
		multiPlanDAO.save(multiPlan);
		return 0;
	}
	public int deleteMultiPlanList(Integer multiPlanId){
		multiPlanMembers = multiPlanMemberDAO.findByMultiPlanId(multiPlanId);
		for(MultiPlanMember var:multiPlanMembers){
			multiPlanMemberDAO.delete(var);
		}
		multiPlan = new MultiPlan();
		multiPlan.setId(multiPlanId);
		multiPlanDAO.delete(multiPlan);
		return 0;
	}
}
