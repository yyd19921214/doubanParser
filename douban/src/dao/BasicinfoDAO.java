package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Basicinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Basicinfo
 * @author MyEclipse Persistence Tools
 */
public class BasicinfoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(BasicinfoDAO.class);
	// property constants
	public static final String NICK_NAME = "nickName";
	public static final String USER_NAME = "userName";
	public static final String ADDRESS = "address";
	public static final String FRIENDS_TAG = "friendsTag";
	public static final String FRIENDS_NUM = "friendsNum";
	public static final String REV_LINK_TAG = "revLinkTag";
	public static final String REV_LINK_NUM = "revLinkNum";
	public static final String GROUP_TAG = "groupTag";
	public static final String GROUP_NUM = "groupNum";
	public static final String DOU_LIST_TAG = "douListTag";
	public static final String DOU_LIST_NUM = "douListNum";
	public static final String DOU_TAG = "douTag";
	public static final String EVENT_NUM = "eventNum";
	public static final String LIKE_TAG = "likeTag";
	public static final String NOTES_TAG = "notesTag";
	public static final String STATUS_TAG = "statusTag";
	public static final String MOVIE_TAG = "movieTag";
	public static final String BOOK_TAG = "bookTag";
	public static final String MUSIC_TAG = "musicTag";
	public static final String MINSITE_TAG = "minsiteTag";
	public static final String REMARK = "remark";

	public void save(Basicinfo transientInstance) {
		log.debug("saving Basicinfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Basicinfo persistentInstance) {
		log.debug("deleting Basicinfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Basicinfo findById(java.lang.Integer id) {
		log.debug("getting Basicinfo instance with id: " + id);
		try {
			Basicinfo instance = (Basicinfo) getSession().get("dao.Basicinfo",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Basicinfo instance) {
		log.debug("finding Basicinfo instance by example");
		try {
			List results = getSession().createCriteria("dao.Basicinfo")
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
		int limit=500;
		log.debug("finding Basicinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Basicinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setMaxResults(limit);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNickName(Object nickName) {
		return findByProperty(NICK_NAME, nickName);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByFriendsTag(Object friendsTag) {
		return findByProperty(FRIENDS_TAG, friendsTag);
	}

	public List findByFriendsNum(Object friendsNum) {
		return findByProperty(FRIENDS_NUM, friendsNum);
	}

	public List findByRevLinkTag(Object revLinkTag) {
		return findByProperty(REV_LINK_TAG, revLinkTag);
	}

	public List findByRevLinkNum(Object revLinkNum) {
		return findByProperty(REV_LINK_NUM, revLinkNum);
	}

	public List findByGroupTag(Object groupTag) {
		return findByProperty(GROUP_TAG, groupTag);
	}

	public List findByGroupNum(Object groupNum) {
		return findByProperty(GROUP_NUM, groupNum);
	}

	public List findByDouListTag(Object douListTag) {
		return findByProperty(DOU_LIST_TAG, douListTag);
	}

	public List findByDouListNum(Object douListNum) {
		return findByProperty(DOU_LIST_NUM, douListNum);
	}

	public List findByDouTag(Object douTag) {
		return findByProperty(DOU_TAG, douTag);
	}

	public List findByEventNum(Object eventNum) {
		return findByProperty(EVENT_NUM, eventNum);
	}

	public List findByLikeTag(Object likeTag) {
		return findByProperty(LIKE_TAG, likeTag);
	}

	public List findByNotesTag(Object notesTag) {
		return findByProperty(NOTES_TAG, notesTag);
	}

	public List findByStatusTag(Object statusTag) {
		return findByProperty(STATUS_TAG, statusTag);
	}

	public List findByMovieTag(Object movieTag) {
		return findByProperty(MOVIE_TAG, movieTag);
	}

	public List findByBookTag(Object bookTag) {
		return findByProperty(BOOK_TAG, bookTag);
	}

	public List findByMusicTag(Object musicTag) {
		return findByProperty(MUSIC_TAG, musicTag);
	}

	public List findByMinsiteTag(Object minsiteTag) {
		return findByProperty(MINSITE_TAG, minsiteTag);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Basicinfo instances");
		try {
			String queryString = "from Basicinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Basicinfo merge(Basicinfo detachedInstance) {
		log.debug("merging Basicinfo instance");
		try {
			Basicinfo result = (Basicinfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Basicinfo instance) {
		log.debug("attaching dirty Basicinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Basicinfo instance) {
		log.debug("attaching clean Basicinfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}