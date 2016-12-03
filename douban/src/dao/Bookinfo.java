package dao;

/**
 * Bookinfo entity. @author MyEclipse Persistence Tools
 */
public class Bookinfo extends AbstractBookinfo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Bookinfo() {
	}

	/** minimal constructor */
	public Bookinfo(String userId, String bookCategory) {
		super(userId, bookCategory);
	}

	/** full constructor */
	public Bookinfo(String userId, String bookCategory, Integer num,
			String remark) {
		super(userId, bookCategory, num, remark);
	}

}
