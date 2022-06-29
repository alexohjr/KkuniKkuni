package bean.review;

import java.sql.Timestamp;

public class ReviewDataBean {
	private int reviewNo;
	private String rContents;
	private Timestamp rDate;
	private String rFile;
	private String grade;
	private String mId;
	private int itemNo;
	private String rTitle;
	//답변 작성
	private String cContents;
	private String eId;
	//리뷰 아이템정보
	private String itemId;
	private String tRealpath;
	
	 
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String gettRealpath() {
		return tRealpath;
	}
	public void settRealpath(String tRealpath) {
		this.tRealpath = tRealpath;
	}
	
	
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getrContents() {
		return rContents;
	}
	public void setrContents(String rContents) {
		this.rContents = rContents;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
	public String getrFile() {
		return rFile;
	}
	public void setrFile(String rFile) {
		this.rFile = rFile;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getcContents() {
		return cContents;
	}
	public void setcContents(String cContents) {
		this.cContents = cContents;
	}
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	
	
}
