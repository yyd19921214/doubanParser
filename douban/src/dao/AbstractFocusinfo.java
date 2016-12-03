package dao;

/**
 * AbstractFocusinfo entity provides the base persistence definition of the
 * Focusinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFocusinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String srcId;
	private String desId;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractFocusinfo() {
	}

	/** minimal constructor */
	public AbstractFocusinfo(String srcId, String desId) {
		this.srcId = srcId;
		this.desId = desId;
	}

	/** full constructor */
	public AbstractFocusinfo(String srcId, String desId, String remark) {
		this.srcId = srcId;
		this.desId = desId;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSrcId() {
		return this.srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getDesId() {
		return this.desId;
	}

	public void setDesId(String desId) {
		this.desId = desId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}