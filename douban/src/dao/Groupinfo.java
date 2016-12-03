package dao;

/**
 * Groupinfo entity. @author MyEclipse Persistence Tools
 */
public class Groupinfo extends AbstractGroupinfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Groupinfo() {
	}

	/** minimal constructor */
	public Groupinfo(String groupName, String groupId, String personId) {
		super(groupName, groupId, personId);
	}

	/** full constructor */
	public Groupinfo(String groupName, String groupId, Integer groupSize,
			String personId, String remark) {
		super(groupName, groupId, groupSize, personId, remark);
	}

}
