package bean.chat;

import java.sql.*;
import java.util.*;
import jdbc.JdbcUtil;

public class ChatDBBean {
	private static ChatDBBean instance = new ChatDBBean();

	public static ChatDBBean getInstance() {
		return instance;
	}

	private ChatDBBean() {

	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public ArrayList<ChatDataBean> getChatList(String mId, String eId) {
		Connection conn = null;
		ArrayList<ChatDataBean> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chat natural join enterprise where m_id=? and e_id=? order by chat_no desc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, eId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDataBean>();
			while (rs.next()) {
				ChatDataBean chat = new ChatDataBean();
				chat.setContents(rs.getString("contents"));
				chat.setcTime(rs.getTimestamp("c_Time"));
				chat.setReceiver(rs.getString("receiver"));
				chat.setSender(rs.getString("sender"));
				chat.seteName(rs.getString("e_Name"));
				chat.setmId(rs.getString("m_id"));
				chatList.add(chat);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return chatList;
	}

	public int submitMember(ChatDataBean chat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into chat values(sequence_chat.nextval,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chat.getReceiver());
			pstmt.setString(2, chat.getSender());
			pstmt.setString(3, chat.getContents());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(5, chat.getmId());
			pstmt.setString(6, chat.geteId());
			
			return pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return -1;
	}
	public int submitEnterprise(ChatDataBean chat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into chat values(sequence_chat.nextval,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chat.getReceiver());
			pstmt.setString(2, chat.getSender());
			pstmt.setString(3, chat.getContents());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(5, chat.getmId());
			pstmt.setString(6, chat.geteId());
			
			return pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return -1;
	}
	
	public int getMyChatCount(String mId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "select count(distinct e_id)  from chat where m_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);// �÷����� �ƴ϶� �ε��� ��ȣ�� ���� �´�. getInt("count(*)") �ص� �ȴ�.
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return x;
	}
	

	public List memberList(String mId) {
		Connection conn = null;
		ArrayList<ChatDataBean> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "select distinct e_id  from chat where m_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<>();
			while (rs.next()) {
				ChatDataBean chat = new ChatDataBean();
				String eId = rs.getString("e_id");

				
				if(eId!=null) {
					pstmt = conn.prepareStatement("select * from chat natural join enterprise where m_id=? and e_id=? order by chat_no desc");
					pstmt.setString(1, mId);
					pstmt.setString(2, eId);
					rs1 = pstmt.executeQuery();
					if(rs1.next());
					chat.setChatNo(rs1.getInt("chat_no"));
					chat.setContents(rs1.getString("contents"));
					chat.setcTime(rs1.getTimestamp("c_time"));
					chat.seteId(rs1.getString("e_id"));
					chat.seteName(rs1.getString("e_name"));
					chat.setmId(rs1.getString("m_id"));
					chat.setReceiver(rs1.getString("receiver"));
					chat.setSender(rs1.getString("sender"));
					chatList.add(chat);
				}
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs1);
		}
		return chatList;
	}
	public List enterpriseList(String eId) {
		Connection conn = null;
		ArrayList<ChatDataBean> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "select distinct m_id  from chat where e_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList();
			while (rs.next()) {
				ChatDataBean chat = new ChatDataBean();
				String mId = rs.getString("m_id");

				
				if(mId!=null) {
					pstmt = conn.prepareStatement("select * from chat natural join enterprise where m_id=? and e_id=? order by chat_no desc");
					pstmt.setString(1, mId);
					pstmt.setString(2, eId);
					rs1 = pstmt.executeQuery();
					if(rs1.next());
					chat.setChatNo(rs1.getInt("chat_no"));
					chat.setContents(rs1.getString("contents"));
					chat.setcTime(rs1.getTimestamp("c_time"));
					chat.seteId(rs1.getString("e_id"));
					chat.seteName(rs1.getString("e_name"));
					chat.setmId(rs1.getString("m_id"));
					chat.setReceiver(rs1.getString("receiver"));
					chat.setSender(rs1.getString("sender"));
					chatList.add(chat);
				}
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs1);
		}
		return chatList;
	}
	
	public void chatDelete(String mId, String eId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "delete from chat where m_id=? and e_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, eId);
			
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}