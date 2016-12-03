package dao;

/**
 * AbstractMinsiteinfo entity provides the base persistence definition of the
 * Minsiteinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMinsiteinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String siteId;
	private String siteTitle;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractMinsiteinfo() {
	}

	/** minimal constructor */
	public AbstractMinsiteinfo(String userId, String siteId, String siteTitle) {
		this.userId = userId;
		this.siteId = siteId;
		this.siteTitle = siteTitle;
	}

	/** full constructor */
	public AbstractMinsiteinfo(String userId, String siteId, String siteTitle,
			String remark) {
		this.userId = userId;
		this.siteId = siteId;
		this.siteTitle = siteTitle;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSiteId() {
		return this.siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteTitle() {
		return this.siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}