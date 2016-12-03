package dao;

/**
 * Minsiteinfo entity. @author MyEclipse Persistence Tools
 */
public class Minsiteinfo extends AbstractMinsiteinfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Minsiteinfo() {
	}

	/** minimal constructor */
	public Minsiteinfo(String userId, String siteId, String siteTitle) {
		super(userId, siteId, siteTitle);
	}

	/** full constructor */
	public Minsiteinfo(String userId, String siteId, String siteTitle,
			String remark) {
		super(userId, siteId, siteTitle, remark);
	}

}
