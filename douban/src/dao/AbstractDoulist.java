package dao;

/**
 * AbstractDoulist entity provides the base persistence definition of the
 * Doulist entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDoulist implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String douListName;
	private String douListId;
	private Integer douListSize;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractDoulist() {
	}

	/** minimal constructor */
	public AbstractDoulist(String userId, String douListName, String douListId) {
		this.userId = userId;
		this.douListName = douListName;
		this.douListId = douListId;
	}

	/** full constructor */
	public AbstractDoulist(String userId, String douListName, String douListId,
			Integer douListSize, String remark) {
		this.userId = userId;
		this.douListName = douListName;
		this.douListId = douListId;
		this.douListSize = douListSize;
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

	public String getDouListName() {
		return this.douListName;
	}

	public void setDouListName(String douListName) {
		this.douListName = douListName;
	}

	public String getDouListId() {
		return this.douListId;
	}

	public void setDouListId(String douListId) {
		this.douListId = douListId;
	}

	public Integer getDouListSize() {
		return this.douListSize;
	}

	public void setDouListSize(Integer douListSize) {
		this.douListSize = douListSize;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}