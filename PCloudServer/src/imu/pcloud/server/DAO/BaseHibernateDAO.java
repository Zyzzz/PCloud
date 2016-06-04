package imu.pcloud.server.DAO;

import java.util.List;

import imu.pcloud.server.HibernateSessionFactory;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	/*	public List findByPersonalPlanId(Integer personalPlanId){
		//getSession().clear();
		String hqlString = "from Comment where personalPlanId = " + personalPlanId + "order by commentingTime";
		return getSession().createQuery(hqlString).list();
	}*/
	
}