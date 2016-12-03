package dao;

/**
 * AbstractLikeinfo entity provides the base persistence definition of the
 * Likeinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLikeinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String title;
	private String likeId;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractLikeinfo() {
	}

	/** minimal constructor */
	public AbstractLikeinfo(String userId, String title, String likeId) {
		this.userId = userId;
		this.title = title;
		this.likeId = likeId;
	}

	/** full constructor */
	public AbstractLikeinfo(String userId, String title, String likeId,
			String remark) {
		this.userId = userId;
		this.title = title;
		this.likeId = likeId;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLikeId() {
		return this.likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}