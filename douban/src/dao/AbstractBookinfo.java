package dao;

/**
 * AbstractBookinfo entity provides the base persistence definition of the
 * Bookinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBookinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String bookCategory;
	private Integer num;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractBookinfo() {
	}

	/** minimal constructor */
	public AbstractBookinfo(String userId, String bookCategory) {
		this.userId = userId;
		this.bookCategory = bookCategory;
	}

	/** full constructor */
	public AbstractBookinfo(String userId, String bookCategory, Integer num,
			String remark) {
		this.userId = userId;
		this.bookCategory = bookCategory;
		this.num = num;
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

	public String getBookCategory() {
		return this.bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}