package dao;

/**
 * AbstractBasicinfo entity provides the base persistence definition of the
 * Basicinfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBasicinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nickName;
	private String userName;
	private String address;
	private Integer friendsTag;
	private Integer friendsNum;
	private Integer revLinkTag;
	private Integer revLinkNum;
	private Integer groupTag;
	private Integer groupNum;
	private Integer douListTag;
	private Integer douListNum;
	private Integer douTag;
	private Integer eventNum;
	private Integer likeTag;
	private Integer notesTag;
	private Integer statusTag;
	private Integer movieTag;
	private Integer bookTag;
	private Integer musicTag;
	private Integer minsiteTag;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractBasicinfo() {
	}

	/** minimal constructor */
	public AbstractBasicinfo(String nickName, String userName) {
		this.nickName = nickName;
		this.userName = userName;
	}

	/** full constructor */
	public AbstractBasicinfo(String nickName, String userName, String address,
			Integer friendsTag, Integer friendsNum, Integer revLinkTag,
			Integer revLinkNum, Integer groupTag, Integer groupNum,
			Integer douListTag, Integer douListNum, Integer douTag,
			Integer eventNum, Integer likeTag, Integer notesTag,
			Integer statusTag, Integer movieTag, Integer bookTag,
			Integer musicTag, Integer minsiteTag, String remark) {
		this.nickName = nickName;
		this.userName = userName;
		this.address = address;
		this.friendsTag = friendsTag;
		this.friendsNum = friendsNum;
		this.revLinkTag = revLinkTag;
		this.revLinkNum = revLinkNum;
		this.groupTag = groupTag;
		this.groupNum = groupNum;
		this.douListTag = douListTag;
		this.douListNum = douListNum;
		this.douTag = douTag;
		this.eventNum = eventNum;
		this.likeTag = likeTag;
		this.notesTag = notesTag;
		this.statusTag = statusTag;
		this.movieTag = movieTag;
		this.bookTag = bookTag;
		this.musicTag = musicTag;
		this.minsiteTag = minsiteTag;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFriendsTag() {
		return this.friendsTag;
	}

	public void setFriendsTag(Integer friendsTag) {
		this.friendsTag = friendsTag;
	}

	public Integer getFriendsNum() {
		return this.friendsNum;
	}

	public void setFriendsNum(Integer friendsNum) {
		this.friendsNum = friendsNum;
	}

	public Integer getRevLinkTag() {
		return this.revLinkTag;
	}

	public void setRevLinkTag(Integer revLinkTag) {
		this.revLinkTag = revLinkTag;
	}

	public Integer getRevLinkNum() {
		return this.revLinkNum;
	}

	public void setRevLinkNum(Integer revLinkNum) {
		this.revLinkNum = revLinkNum;
	}

	public Integer getGroupTag() {
		return this.groupTag;
	}

	public void setGroupTag(Integer groupTag) {
		this.groupTag = groupTag;
	}

	public Integer getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	public Integer getDouListTag() {
		return this.douListTag;
	}

	public void setDouListTag(Integer douListTag) {
		this.douListTag = douListTag;
	}

	public Integer getDouListNum() {
		return this.douListNum;
	}

	public void setDouListNum(Integer douListNum) {
		this.douListNum = douListNum;
	}

	public Integer getDouTag() {
		return this.douTag;
	}

	public void setDouTag(Integer douTag) {
		this.douTag = douTag;
	}

	public Integer getEventNum() {
		return this.eventNum;
	}

	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}

	public Integer getLikeTag() {
		return this.likeTag;
	}

	public void setLikeTag(Integer likeTag) {
		this.likeTag = likeTag;
	}

	public Integer getNotesTag() {
		return this.notesTag;
	}

	public void setNotesTag(Integer notesTag) {
		this.notesTag = notesTag;
	}

	public Integer getStatusTag() {
		return this.statusTag;
	}

	public void setStatusTag(Integer statusTag) {
		this.statusTag = statusTag;
	}

	public Integer getMovieTag() {
		return this.movieTag;
	}

	public void setMovieTag(Integer movieTag) {
		this.movieTag = movieTag;
	}

	public Integer getBookTag() {
		return this.bookTag;
	}

	public void setBookTag(Integer bookTag) {
		this.bookTag = bookTag;
	}

	public Integer getMusicTag() {
		return this.musicTag;
	}

	public void setMusicTag(Integer musicTag) {
		this.musicTag = musicTag;
	}

	public Integer getMinsiteTag() {
		return this.minsiteTag;
	}

	public void setMinsiteTag(Integer minsiteTag) {
		this.minsiteTag = minsiteTag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}