package imu.pcloud.server.DAO;

import imu.pcloud.server.HibernateSessionFactory;
import imu.pcloud.server.been.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for User entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see imu.pcloud.server.been.User
  * @author MyEclipse Persistence Tools 
 */
public class UserDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
		//property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String SEX = "sex";
	public static final String EDUCATION = "education";
	public static final String WORKING = "working";
	public static final String SIGNATURE = "signature";



    
    public void save(User transientInstance) {
        log.debug("saving User instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
        ////getSession().flush();
        getSession().beginTransaction();
        //getSession().evict(transientInstance);
        getSession().getTransaction().commit();
        getSession().close();
    }
    
	public void delete(User persistentInstance) {
        log.debug("deleting User instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
        ////getSession().flush();
        getSession().beginTransaction();
        //getSession().evict(persistentInstance);
        getSession().getTransaction().commit();
        getSession().close();
    }
    
    public User findById( java.lang.Integer id) {
    	//getSession().flush();
    	getSession().clear();
    	HibernateSessionFactory.closeSession();
        log.debug("getting User instance with id: " + id);
        try {
            User instance = (User) getSession()
                    .get("imu.pcloud.server.been.User", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(User instance) {
    	//getSession().flush();
    	getSession().clear();
    	HibernateSessionFactory.closeSession();
        log.debug("finding User instance by example");
        try {
            List results = getSession()
                    .createCriteria("imu.pcloud.server.been.User")
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
      log.debug("finding User instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from User as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUsername(Object username
	) {
		return findByProperty(USERNAME, username
		);
	}
	
	public List findByPassword(Object password
	) {
		return findByProperty(PASSWORD, password
		);
	}
	
	public List findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List findBySex(Object sex
	) {
		return findByProperty(SEX, sex
		);
	}
	
	public List findByEducation(Object education
	) {
		return findByProperty(EDUCATION, education
		);
	}
	
	public List findByWorking(Object working
	) {
		return findByProperty(WORKING, working
		);
	}
	
	public List findBySignature(Object signature
	) {
		return findByProperty(SIGNATURE, signature
		);
	}
	

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public User merge(User detachedInstance) {
        log.debug("merging User instance");
        try {
            User result = (User) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(User instance) {
        log.debug("attaching dirty User instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(User instance) {
        log.debug("attaching clean User instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}