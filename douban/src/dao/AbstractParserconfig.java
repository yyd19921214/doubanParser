package dao;

/**
 * AbstractParserconfig entity provides the base persistence definition of the
 * Parserconfig entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractParserconfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer maxPage;
	private Integer finishedPage;
	private Integer memberNum;
	private String configData;
	private Integer limitNum;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractParserconfig() {
	}

	/** minimal constructor */
	public AbstractParserconfig(Integer maxPage, Integer finishedPage) {
		this.maxPage = maxPage;
		this.finishedPage = finishedPage;
	}

	/** full constructor */
	public AbstractParserconfig(Integer maxPage, Integer finishedPage,
			Integer memberNum, String configData, Integer limitNum,
			String remark) {
		this.maxPage = maxPage;
		this.finishedPage = finishedPage;
		this.memberNum = memberNum;
		this.configData = configData;
		this.limitNum = limitNum;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaxPage() {
		return this.maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public Integer getFinishedPage() {
		return this.finishedPage;
	}

	public void setFinishedPage(Integer finishedPage) {
		this.finishedPage = finishedPage;
	}

	public Integer getMemberNum() {
		return this.memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public String getConfigData() {
		return this.configData;
	}

	public void setConfigData(String configData) {
		this.configData = configData;
	}

	public Integer getLimitNum() {
		return this.limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}