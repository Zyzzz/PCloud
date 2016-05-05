package imu.pcloud.server.DAO;

import imu.pcloud.server.been.PersonalPlan;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonalPlan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see imu.pcloud.server.DAO.PersonalPlan
 * @author MyEclipse Persistence Tools
 */
public class PersonalPlanDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PersonalPlanDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String NAME = "name";
	public static final String USER_ID = "userId";
	public static final String COVER_IMAGE_ID = "coverImageId";

	public void save(PersonalPlan transientInstance) {
		log.debug("saving PersonalPlan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
        getSession().beginTransaction().commit();
        getSession().close();
	}

	public void delete(PersonalPlan persistentInstance) {
		log.debug("deleting PersonalPlan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
        getSession().beginTransaction().commit();
        getSession().close();
	}

	public PersonalPlan findById(java.lang.Integer id) {
		log.debug("getting PersonalPlan instance with id: " + id);
		try {
			PersonalPlan instance = (PersonalPlan) getSession().get(
					"imu.pcloud.server.been.PersonalPlan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PersonalPlan instance) {
		log.debug("finding PersonalPlan instance by example");
		try {
			List results = getSession()
					.createCriteria("imu.pcloud.server.been.PersonalPlan")
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
		log.debug("finding PersonalPlan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PersonalPlan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByCoverImageId(Object coverImageId) {
		return findByProperty(COVER_IMAGE_ID, coverImageId);
	}

	public List findAll() {
		log.debug("finding all PersonalPlan instances");
		try {
			String queryString = "from PersonalPlan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PersonalPlan merge(PersonalPlan detachedInstance) {
		log.debug("merging PersonalPlan instance");
		try {
			PersonalPlan result = (PersonalPlan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PersonalPlan instance) {
		log.debug("attaching dirty PersonalPlan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PersonalPlan instance) {
		log.debug("attaching clean PersonalPlan instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}