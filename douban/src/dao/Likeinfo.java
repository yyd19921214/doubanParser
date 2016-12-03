package dao;

/**
 * Likeinfo entity. @author MyEclipse Persistence Tools
 */
public class Likeinfo extends AbstractLikeinfo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Likeinfo() {
	}

	/** minimal constructor */
	public Likeinfo(String userId, String title, String likeId) {
		super(userId, title, likeId);
	}

	/** full constructor */
	public Likeinfo(String userId, String title, String likeId, String remark) {
		super(userId, title, likeId, remark);
	}

}
