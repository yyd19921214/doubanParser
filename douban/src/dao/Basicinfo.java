package dao;

/**
 * Basicinfo entity. @author MyEclipse Persistence Tools
 */
public class Basicinfo extends AbstractBasicinfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Basicinfo() {
	}

	/** minimal constructor */
	public Basicinfo(String nickName, String userName) {
		super(nickName, userName);
	}

	/** full constructor */
	public Basicinfo(String nickName, String userName, String address,
			Integer friendsTag, Integer friendsNum, Integer revLinkTag,
			Integer revLinkNum, Integer groupTag, Integer groupNum,
			Integer douListTag, Integer douListNum, Integer douTag,
			Integer eventNum, Integer likeTag, Integer notesTag,
			Integer statusTag, Integer movieTag, Integer bookTag,
			Integer musicTag, Integer minsiteTag, String remark) {
		super(nickName, userName, address, friendsTag, friendsNum, revLinkTag,
				revLinkNum, groupTag, groupNum, douListTag, douListNum, douTag,
				eventNum, likeTag, notesTag, statusTag, movieTag, bookTag,
				musicTag, minsiteTag, remark);
	}

}
