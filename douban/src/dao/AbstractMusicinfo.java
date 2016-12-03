package dao;

/**
 * AbstractMusicinfo entity provides the base persistence definition of the
 * Musicinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMusicinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String musicCategory;
	private Integer num;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractMusicinfo() {
	}

	/** minimal constructor */
	public AbstractMusicinfo(String userId, String musicCategory) {
		this.userId = userId;
		this.musicCategory = musicCategory;
	}

	/** full constructor */
	public AbstractMusicinfo(String userId, String musicCategory, Integer num,
			String remark) {
		this.userId = userId;
		this.musicCategory = musicCategory;
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

	public String getMusicCategory() {
		return this.musicCategory;
	}

	public void setMusicCategory(String musicCategory) {
		this.musicCategory = musicCategory;
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