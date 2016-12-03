package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Musicinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Musicinfo
 * @author MyEclipse Persistence Tools
 */
public class MusicinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(MusicinfoDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String MUSIC_CATEGORY = "musicCategory";
	public static final String NUM = "num";
	public static final String REMARK = "remark";

	public void save(Musicinfo transientInstance) {
		log.debug("saving Musicinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Musicinfo persistentInstance) {
		log.debug("deleting Musicinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Musicinfo findById(java.lang.Integer id) {
		log.debug("getting Musicinfo instance with id: " + id);
		try {
			Musicinfo instance = (Musicinfo) getSession().get("dao.Musicinfo",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Musicinfo instance) {
		log.debug("finding Musicinfo instance by example");
		try {
			List results = getSession().createCriteria("dao.Musicinfo")
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
		log.debug("finding Musicinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Musicinfo as model where model."
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

	public List findByMusicCategory(Object musicCategory) {
		return findByProperty(MUSIC_CATEGORY, musicCategory);
	}

	public List findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Musicinfo instances");
		try {
			String queryString = "from Musicinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Musicinfo merge(Musicinfo detachedInstance) {
		log.debug("merging Musicinfo instance");
		try {
			Musicinfo result = (Musicinfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Musicinfo instance) {
		log.debug("attaching dirty Musicinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Musicinfo instance) {
		log.debug("attaching clean Musicinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}