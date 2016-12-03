package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Likeinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Likeinfo
 * @author MyEclipse Persistence Tools
 */
public class LikeinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(LikeinfoDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String TITLE = "title";
	public static final String LIKE_ID = "likeId";
	public static final String REMARK = "remark";

	public void save(Likeinfo transientInstance) {
		log.debug("saving Likeinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Likeinfo persistentInstance) {
		log.debug("deleting Likeinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Likeinfo findById(java.lang.Integer id) {
		log.debug("getting Likeinfo instance with id: " + id);
		try {
			Likeinfo instance = (Likeinfo) getSession().get("dao.Likeinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Likeinfo instance) {
		log.debug("finding Likeinfo instance by example");
		try {
			List results = getSession().createCriteria("dao.Likeinfo")
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
		log.debug("finding Likeinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Likeinfo as model where model."
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

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByLikeId(Object likeId) {
		return findByProperty(LIKE_ID, likeId);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Likeinfo instances");
		try {
			String queryString = "from Likeinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Likeinfo merge(Likeinfo detachedInstance) {
		log.debug("merging Likeinfo instance");
		try {
			Likeinfo result = (Likeinfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Likeinfo instance) {
		log.debug("attaching dirty Likeinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Likeinfo instance) {
		log.debug("attaching clean Likeinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}