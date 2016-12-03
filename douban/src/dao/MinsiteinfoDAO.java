package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Minsiteinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Minsiteinfo
 * @author MyEclipse Persistence Tools
 */
public class MinsiteinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(MinsiteinfoDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String SITE_ID = "siteId";
	public static final String SITE_TITLE = "siteTitle";
	public static final String REMARK = "remark";

	public void save(Minsiteinfo transientInstance) {
		log.debug("saving Minsiteinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Minsiteinfo persistentInstance) {
		log.debug("deleting Minsiteinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Minsiteinfo findById(java.lang.Integer id) {
		log.debug("getting Minsiteinfo instance with id: " + id);
		try {
			Minsiteinfo instance = (Minsiteinfo) getSession().get(
					"dao.Minsiteinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Minsiteinfo instance) {
		log.debug("finding Minsiteinfo instance by example");
		try {
			List results = getSession().createCriteria("dao.Minsiteinfo")
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
		log.debug("finding Minsiteinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Minsiteinfo as model where model."
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

	public List findBySiteId(Object siteId) {
		return findByProperty(SITE_ID, siteId);
	}

	public List findBySiteTitle(Object siteTitle) {
		return findByProperty(SITE_TITLE, siteTitle);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Minsiteinfo instances");
		try {
			String queryString = "from Minsiteinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Minsiteinfo merge(Minsiteinfo detachedInstance) {
		log.debug("merging Minsiteinfo instance");
		try {
			Minsiteinfo result = (Minsiteinfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Minsiteinfo instance) {
		log.debug("attaching dirty Minsiteinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Minsiteinfo instance) {
		log.debug("attaching clean Minsiteinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}