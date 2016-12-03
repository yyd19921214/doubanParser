package dao;

/**
 * Focusinfo entity. @author MyEclipse Persistence Tools
 */
public class Focusinfo extends AbstractFocusinfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Focusinfo() {
	}

	/** minimal constructor */
	public Focusinfo(String srcId, String desId) {
		super(srcId, desId);
	}

	/** full constructor */
	public Focusinfo(String srcId, String desId, String remark) {
		super(srcId, desId, remark);
	}

}
