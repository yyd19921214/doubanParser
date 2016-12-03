package dao;

/**
 * AbstractMovieinfo entity provides the base persistence definition of the
 * Movieinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMovieinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String movieCategory;
	private Integer num;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractMovieinfo() {
	}

	/** minimal constructor */
	public AbstractMovieinfo(String userId, String movieCategory) {
		this.userId = userId;
		this.movieCategory = movieCategory;
	}

	/** full constructor */
	public AbstractMovieinfo(String userId, String movieCategory, Integer num,
			String remark) {
		this.userId = userId;
		this.movieCategory = movieCategory;
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

	public String getMovieCategory() {
		return this.movieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
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