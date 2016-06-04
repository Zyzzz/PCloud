package imu.pcloud.server.DAO;

import imu.pcloud.server.been.MultiPlanMember;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MultiPlanMember entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see imu.pcloud.server.been.MultiPlanMember
 * @author MyEclipse Persistence Tools
 */
public class MultiPlanMemberDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MultiPlanMemberDAO.class);
	// property constants
	public static final String MULTI_PLAN_ID = "multiPlanId";
	public static final String USER_ID = "userId";

	public void save(MultiPlanMember transientInstance) {
		log.debug("saving MultiPlanMember instance");
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

	public void delete(MultiPlanMember persistentInstance) {
		log.debug("deleting MultiPlanMember instance");
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

	public MultiPlanMember findById(java.lang.Integer id) {
		log.debug("getting MultiPlanMember instance with id: " + id);
		try {
			MultiPlanMember instance = (MultiPlanMember) getSession().get(
					"imu.pcloud.server.been.MultiPlanMember", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MultiPlanMember instance) {
		log.debug("finding MultiPlanMember instance by example");
		try {
			List results = getSession()
					.createCriteria("imu.pcloud.server.been.MultiPlanMember")
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
		log.debug("finding MultiPlanMember instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MultiPlanMember as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMultiPlanId(Object multiPlanId) {
		return findByProperty(MULTI_PLAN_ID, multiPlanId);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all MultiPlanMember instances");
		try {
			String queryString = "from MultiPlanMember";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MultiPlanMember merge(MultiPlanMember detachedInstance) {
		log.debug("merging MultiPlanMember instance");
		try {
			MultiPlanMember result = (MultiPlanMember) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MultiPlanMember instance) {
		log.debug("attaching dirty MultiPlanMember instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MultiPlanMember instance) {
		log.debug("attaching clean MultiPlanMember instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}