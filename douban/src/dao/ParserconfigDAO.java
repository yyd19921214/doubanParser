package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Parserconfig entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Parserconfig
 * @author MyEclipse Persistence Tools
 */
public class ParserconfigDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ParserconfigDAO.class);
	// property constants
	public static final String MAX_PAGE = "maxPage";
	public static final String FINISHED_PAGE = "finishedPage";
	public static final String MEMBER_NUM = "memberNum";
	public static final String CONFIG_DATA = "configData";
	public static final String LIMIT_NUM = "limitNum";
	public static final String REMARK = "remark";

	public void save(Parserconfig transientInstance) {
		log.debug("saving Parserconfig instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Parserconfig persistentInstance) {
		log.debug("deleting Parserconfig instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Parserconfig findById(java.lang.Integer id) {
		log.debug("getting Parserconfig instance with id: " + id);
		try {
			Parserconfig instance = (Parserconfig) getSession().get(
					"dao.Parserconfig", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Parserconfig instance) {
		log.debug("finding Parserconfig instance by example");
		try {
			List results = getSession().createCriteria("dao.Parserconfig")
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
		log.debug("finding Parserconfig instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Parserconfig as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMaxPage(Object maxPage) {
		return findByProperty(MAX_PAGE, maxPage);
	}

	public List findByFinishedPage(Object finishedPage) {
		return findByProperty(FINISHED_PAGE, finishedPage);
	}

	public List findByMemberNum(Object memberNum) {
		return findByProperty(MEMBER_NUM, memberNum);
	}

	public List findByConfigData(Object configData) {
		return findByProperty(CONFIG_DATA, configData);
	}

	public List findByLimitNum(Object limitNum) {
		return findByProperty(LIMIT_NUM, limitNum);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Parserconfig instances");
		try {
			String queryString = "from Parserconfig";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Parserconfig merge(Parserconfig detachedInstance) {
		log.debug("merging Parserconfig instance");
		try {
			Parserconfig result = (Parserconfig) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Parserconfig instance) {
		log.debug("attaching dirty Parserconfig instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Parserconfig instance) {
		log.debug("attaching clean Parserconfig instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}