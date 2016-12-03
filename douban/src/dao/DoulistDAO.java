package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Doulist entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Doulist
 * @author MyEclipse Persistence Tools
 */
public class DoulistDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DoulistDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String DOU_LIST_NAME = "douListName";
	public static final String DOU_LIST_ID = "douListId";
	public static final String DOU_LIST_SIZE = "douListSize";
	public static final String REMARK = "remark";

	public void save(Doulist transientInstance) {
		log.debug("saving Doulist instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Doulist persistentInstance) {
		log.debug("deleting Doulist instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Doulist findById(java.lang.Integer id) {
		log.debug("getting Doulist instance with id: " + id);
		try {
			Doulist instance = (Doulist) getSession().get("dao.Doulist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Doulist instance) {
		log.debug("finding Doulist instance by example");
		try {
			List results = getSession().createCriteria("dao.Doulist")
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
		log.debug("finding Doulist instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Doulist as model where model."
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

	public List findByDouListName(Object douListName) {
		return findByProperty(DOU_LIST_NAME, douListName);
	}

	public List findByDouListId(Object douListId) {
		return findByProperty(DOU_LIST_ID, douListId);
	}

	public List findByDouListSize(Object douListSize) {
		return findByProperty(DOU_LIST_SIZE, douListSize);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Doulist instances");
		try {
			String queryString = "from Doulist";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Doulist merge(Doulist detachedInstance) {
		log.debug("merging Doulist instance");
		try {
			Doulist result = (Doulist) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Doulist instance) {
		log.debug("attaching dirty Doulist instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Doulist instance) {
		log.debug("attaching clean Doulist instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}