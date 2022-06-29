package bean.question;

import java.sql.Timestamp;

public class QuestionDataBean {
	//회원
	private int questionNo;
	private String qSort;
	private String qTitle;
	private String qContents;
	private Timestamp qDate;
	private String qState;
	private String mId;
	//업체(id만 다름)
	private String eId;
	//관리자
	private String aContents;
	private Timestamp aDate;
	
	
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	
	public String getqSort() {
		return qSort;
	}
	public void setqSort(String qSort) {
		this.qSort = qSort;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContents() {
		return qContents;
	}
	public void setqContents(String qContents) {
		this.qContents = qContents;
	}
	public Timestamp getqDate() {
		return qDate;
	}
	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public String getqState() {
		return qState;
	}
	public void setqState(String qState) {
		this.qState = qState;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getaContents() {
		return aContents;
	}
	public void setaContents(String aContents) {
		this.aContents = aContents;
	}
	public Timestamp getaDate() {
		return aDate;
	}
	public void setaDate(Timestamp aDate) {
		this.aDate = aDate;
	}
	
}
