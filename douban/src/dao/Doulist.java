package dao;

/**
 * Doulist entity. @author MyEclipse Persistence Tools
 */
public class Doulist extends AbstractDoulist implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Doulist() {
	}

	/** minimal constructor */
	public Doulist(String userId, String douListName, String douListId) {
		super(userId, douListName, douListId);
	}

	/** full constructor */
	public Doulist(String userId, String douListName, String douListId,
			Integer douListSize, String remark) {
		super(userId, douListName, douListId, douListSize, remark);
	}

}
