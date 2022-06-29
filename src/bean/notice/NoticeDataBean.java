package bean.notice;

import java.sql.Timestamp;

public class NoticeDataBean {
	private int noticeNo;
	private String nSort;
	private String nTitle;
	private Timestamp nDate;
	private int viewCount;
	private String nFile;
	private String nContents;
	private String nRealpath;

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getnSort() {
		return nSort;
	}

	public void setnSort(String nSort) {
		this.nSort = nSort;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public Timestamp getnDate() {
		return nDate;
	}

	public void setnDate(Timestamp nDate) {
		this.nDate = nDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getnFile() {
		return nFile;
	}

	public void setnFile(String nFile) {
		this.nFile = nFile;
	}

	public String getnContents() {
		return nContents;
	}

	public void setnContents(String nContents) {
		this.nContents = nContents;
	}

	public String getnRealpath() {
		return nRealpath;
	}

	public void setnRealpath(String nRealpath) {
		this.nRealpath = nRealpath;
	}

}
