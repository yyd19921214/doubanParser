package dao;

/**
 * Movieinfo entity. @author MyEclipse Persistence Tools
 */
public class Movieinfo extends AbstractMovieinfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Movieinfo() {
	}

	/** minimal constructor */
	public Movieinfo(String userId, String movieCategory) {
		super(userId, movieCategory);
	}

	/** full constructor */
	public Movieinfo(String userId, String movieCategory, Integer num,
			String remark) {
		super(userId, movieCategory, num, remark);
	}

}
