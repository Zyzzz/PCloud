package imu.pcloud.server.DAO;

import imu.pcloud.server.been.SharingRecord;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for SharingRecord entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see imu.pcloud.server.DAO.SharingRecord
  * @author MyEclipse Persistence Tools 
 */
public class SharingRecordDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SharingRecordDAO.class);
		//property constants
	public static final String LOADING_TIME = "loadingTime";



    
    public void save(SharingRecord transientInstance) {
        log.debug("saving SharingRecord instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
        ////getSession().flush();
        getSession().beginTransaction();
        getSession().getTransaction().commit();
        getSession().close();
    }
    
	public void delete(SharingRecord persistentInstance) {
        log.debug("deleting SharingRecord instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
        ////getSession().flush();
        getSession().beginTransaction();
        getSession().getTransaction().commit();
        getSession().close();
    }
    
    public SharingRecord findById( imu.pcloud.server.been.SharingRecordId id) {
    	//getSession().flush();
    	//getSession().clear();
        log.debug("getting SharingRecord instance with id: " + id);
        try {
            SharingRecord instance = (SharingRecord) getSession()
                    .get("imu.pcloud.server.been.SharingRecord", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SharingRecord instance) {
    	//getSession().flush();
    	//getSession().clear();
        log.debug("finding SharingRecord instance by example");
        try {
            List results = getSession()
                    .createCriteria("imu.pcloud.server.been.SharingRecord")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding SharingRecord instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SharingRecord as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByLoadingTime(Object loadingTime
	) {
		return findByProperty(LOADING_TIME, loadingTime
		);
	}
	
	public List findByPlanCircleId(Integer planCircleId) {
    	//getSession().clear();
		String hqlString = "from SharingRecord where planCircleID = " + planCircleId;
		return getSession().createQuery(hqlString).list();
	}
	
	public List findAll() {
		log.debug("finding all SharingRecord instances");
		try {
			String queryString = "from SharingRecord";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SharingRecord merge(SharingRecord detachedInstance) {
        log.debug("merging SharingRecord instance");
        try {
            SharingRecord result = (SharingRecord) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SharingRecord instance) {
        log.debug("attaching dirty SharingRecord instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SharingRecord instance) {
        log.debug("attaching clean SharingRecord instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}