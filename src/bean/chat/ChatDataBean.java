package bean.chat;

import java.sql.Timestamp;

public class ChatDataBean {
	private int chatNo;
	private String receiver;
	private String sender;
	private String contents;
	private Timestamp cTime;
	private String mId;
	private String eId;
	private String eName;

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getcTime() {
		return cTime;
	}

	public void setcTime(Timestamp cTime) {
		this.cTime = cTime;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}
	

}
