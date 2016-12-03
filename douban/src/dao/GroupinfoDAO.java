package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Groupinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Groupinfo
 * @author MyEclipse Persistence Tools
 */
public class GroupinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(GroupinfoDAO.class);
	// property constants
	public static final String GROUP_NAME = "groupName";
	public static final String GROUP_ID = "groupId";
	public static final String GROUP_SIZE = "groupSize";
	public static final String PERSON_ID = "personId";
	public static final String REMARK = "remark";

	public void save(Groupinfo transientInstance) {
		log.debug("saving Groupinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Groupinfo persistentInstance) {
		log.debug("deleting Groupinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Groupinfo findById(java.lang.Integer id) {
		log.debug("getting Groupinfo instance with id: " + id);
		try {
			Groupinfo instance = (Groupinfo) getSession().get("dao.Groupinfo",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Groupinfo instance) {
		log.debug("finding Groupinfo instance by example");
		try {
			List results = getSession().createCriteria("dao.Groupinfo")
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
		log.debug("finding Groupinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Groupinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List findByGroupId(Object groupId) {
		return findByProperty(GROUP_ID, groupId);
	}

	public List findByGroupSize(Object groupSize) {
		return findByProperty(GROUP_SIZE, groupSize);
	}

	public List findByPersonId(Object personId) {
		return findByProperty(PERSON_ID, personId);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Groupinfo instances");
		try {
			String queryString = "from Groupinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Groupinfo merge(Groupinfo detachedInstance) {
		log.debug("merging Groupinfo instance");
		try {
			Groupinfo result = (Groupinfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Groupinfo instance) {
		log.debug("attaching dirty Groupinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Groupinfo instance) {
		log.debug("attaching clean Groupinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}