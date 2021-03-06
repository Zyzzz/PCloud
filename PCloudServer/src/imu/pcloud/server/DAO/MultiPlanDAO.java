package imu.pcloud.server.DAO;

import imu.pcloud.server.HibernateSessionFactory;
import imu.pcloud.server.been.MultiPlan;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MultiPlan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see imu.pcloud.server.been.MultiPlan
 * @author MyEclipse Persistence Tools
 */
public class MultiPlanDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MultiPlanDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String NAME = "name";
	public static final String CONTENT = "content";
	public static final String MAXMUMBER = "maxmumber";
	public static final String CONDITION = "condition";
	public static final String COVER_IMAGE_ID = "coverImageId";

	public void save(MultiPlan transientInstance) {
		log.debug("saving MultiPlan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
        getSession().beginTransaction();
        getSession().getTransaction().commit();
        getSession().close();
	}

	public void delete(MultiPlan persistentInstance) {
		log.debug("deleting MultiPlan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
        getSession().beginTransaction();
        getSession().getTransaction().commit();
        getSession().close();
	}

	public MultiPlan findById(java.lang.Integer id) {
    	getSession().flush();
    	getSession().clear();
    	HibernateSessionFactory.closeSession();
		log.debug("getting MultiPlan instance with id: " + id);
		try {
			MultiPlan instance = (MultiPlan) getSession().get(
					"imu.pcloud.server.been.MultiPlan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
    public List findBybBlurryName(String blurryName) {
    	getSession().flush();
    	getSession().clear();
    	HibernateSessionFactory.closeSession();
    	try {
    		String str = "'%" + blurryName + "%'";
	    	String hqlString = "from MultiPlan where name like " + str;
			return getSession().createQuery(hqlString).list();
    	} catch(RuntimeException re){
            log.error("find by example failed", re);
            throw re;
        }
    	
    }

	public List findByExample(MultiPlan instance) {
		getSession().clear();
    	HibernateSessionFactory.closeSession();
		log.debug("finding MultiPlan instance by example");
		try {
			List results = getSession()
					.createCriteria("imu.pcloud.server.been.MultiPlan")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MultiPlan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MultiPlan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByMaxmumber(Object maxmumber) {
		return findByProperty(MAXMUMBER, maxmumber);
	}

//	public List findByCondition(Object condition) {
//		return findByProperty(CONDITION, condition);
//	}

	public List findByCoverImageId(Object coverImageId) {
		return findByProperty(COVER_IMAGE_ID, coverImageId);
	}

	public List findAll() {
		log.debug("finding all MultiPlan instances");
		try {
			String queryString = "from MultiPlan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MultiPlan merge(MultiPlan detachedInstance) {
		log.debug("merging MultiPlan instance");
		try {
			MultiPlan result = (MultiPlan) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MultiPlan instance) {
		log.debug("attaching dirty MultiPlan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MultiPlan instance) {
		log.debug("attaching clean MultiPlan instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}