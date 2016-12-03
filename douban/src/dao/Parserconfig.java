package dao;

/**
 * Parserconfig entity. @author MyEclipse Persistence Tools
 */
public class Parserconfig extends AbstractParserconfig implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Parserconfig() {
	}

	/** minimal constructor */
	public Parserconfig(Integer maxPage, Integer finishedPage) {
		super(maxPage, finishedPage);
	}

	/** full constructor */
	public Parserconfig(Integer maxPage, Integer finishedPage,
			Integer memberNum, String configData, Integer limitNum,
			String remark) {
		super(maxPage, finishedPage, memberNum, configData, limitNum, remark);
	}

}
