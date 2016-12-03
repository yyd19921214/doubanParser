package dao;

/**
 * Musicinfo entity. @author MyEclipse Persistence Tools
 */
public class Musicinfo extends AbstractMusicinfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Musicinfo() {
	}

	/** minimal constructor */
	public Musicinfo(String userId, String musicCategory) {
		super(userId, musicCategory);
	}

	/** full constructor */
	public Musicinfo(String userId, String musicCategory, Integer num,
			String remark) {
		super(userId, musicCategory, num, remark);
	}

}
