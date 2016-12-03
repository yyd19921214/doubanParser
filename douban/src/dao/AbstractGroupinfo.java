package dao;

/**
 * AbstractGroupinfo entity provides the base persistence definition of the
 * Groupinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGroupinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String groupName;
	private String groupId;
	private Integer groupSize;
	private String personId;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractGroupinfo() {
	}

	/** minimal constructor */
	public AbstractGroupinfo(String groupName, String groupId, String personId) {
		this.groupName = groupName;
		this.groupId = groupId;
		this.personId = personId;
	}

	/** full constructor */
	public AbstractGroupinfo(String groupName, String groupId,
			Integer groupSize, String personId, String remark) {
		this.groupName = groupName;
		this.groupId = groupId;
		this.groupSize = groupSize;
		this.personId = personId;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupSize() {
		return this.groupSize;
	}

	public void setGroupSize(Integer groupSize) {
		this.groupSize = groupSize;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}