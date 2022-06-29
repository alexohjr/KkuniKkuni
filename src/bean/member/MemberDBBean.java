package bean.member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import bean.member.ZipcodeBean;


public class MemberDBBean {
	
	private static MemberDBBean instance = new MemberDBBean();
	
	public static MemberDBBean getInstance() {
		return instance;
	}

	private MemberDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// memberInsertPro.jsp
	public void memberInsert(MemberDataBean member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into MEMBER values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmName());
			pstmt.setString(4, member.getBirthday()); 
			pstmt.setString(5, member.getSex());
			pstmt.setString(6, member.getmTel());
			pstmt.setString(7, member.getmConfirm());
			pstmt.setString(8, member.getmZipcode());
			pstmt.setString(9, member.getmAddress());
			pstmt.setString(10, member.getmAccountNo());
			pstmt.setString(11, member.getmBankName());
			pstmt.setString(12, member.getmDepositor());
			pstmt.setString(13, member.getmMail());
			
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if(conn != null)
				try {
					conn.close();
				} catch(SQLException ex) {
				}
		}
	}  
	  
	// memberZipCheck.jsp
	public Vector zipcodeRead(String area4)  {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector<ZipcodeBean> vecList = new Vector<ZipcodeBean>();
        
        try {
            conn = getConnection();  
            String strQuery = "select * from zipcode where area4 like '"+area4+"%'";
            pstmt = conn.prepareStatement(strQuery);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ZipcodeBean tempZipcode = new ZipcodeBean();
                tempZipcode.setZipcode(rs.getString("zipcode"));
                tempZipcode.setArea1(rs.getString("area1"));
                tempZipcode.setArea2(rs.getString("area2"));
                tempZipcode.setArea3(rs.getString("area3"));
                tempZipcode.setArea4(rs.getString("area4"));
                vecList.addElement(tempZipcode);
            }

        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return vecList;
    }

	// memberConfirmId.jsp
	public int confirmId(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1; // 경우의 수

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select m_Id from MEMBER where m_Id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = 1; // 해당 아이디 있음
			else
				x = -1; // 해당 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)  
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
	
	// 로그인
	public int userCheck(String id, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String dlt = "";
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select m_pw, m_confirm from MEMBER where m_id = ?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpasswd = rs.getString("m_pw");
				dlt = rs.getString("m_confirm");
				if (dbpasswd.equals(passwd)) {
					if (!dlt.equals("delete")) {
						x = 1; // 인증 성공
					} else {
						x = -1; // 탈퇴회원
					}
				} else {
					x = 0; // 아이디 or 비밀번호 틀림
				}
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// memberModifyForm.jsp
	public MemberDataBean getMember(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDataBean member = null;
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from MEMBER where m_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberDataBean();
				member.setmId(rs.getString("m_id"));
				member.setmPw(rs.getString("m_pw"));
				member.setmName(rs.getString("m_name"));
				member.setBirthday(rs.getString("birthday"));
				member.setSex(rs.getString("sex"));
				member.setmTel(rs.getString("m_tel"));
				member.setmMail(rs.getString("m_mail"));
				member.setmZipcode(rs.getString("m_zipcode"));
				member.setmAddress(rs.getString("m_address"));
				member.setmAccountNo(rs.getString("m_accountNo"));
				member.setmBankName(rs.getString("m_bankName"));
				member.setmDepositor(rs.getString("m_depositor"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return member;
	}

	// memberModifyPro.jsp
	public void updateMember(MemberDataBean member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			pstmt = conn
					.prepareStatement("update MEMBER set m_pw = ?, m_tel = ?, m_zipcode = ?, m_address = ?, m_accountno = ?, m_bankname = ?, m_depositor = ?" + "where m_id = ?");
			pstmt.setString(1, member.getmPw());
			pstmt.setString(2, member.getmTel());
			pstmt.setString(3, member.getmZipcode());
			
			pstmt.setString(4, member.getmAddress());
			pstmt.setString(5, member.getmAccountNo());
			pstmt.setString(6, member.getmBankName());
			pstmt.setString(7, member.getmDepositor());
			
			pstmt.setString(8, member.getmId());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 이메일 중복 체크
	public int confirmMail(String mail) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dlt = "";
		
		int x = -1; // 경우의 수

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from MEMBER where m_mail = ?");
			pstmt.setString(1, mail);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dlt = rs.getString("m_confirm");
				if (dlt.equals("delete")) {
					x = -1; // 메일 사용가능
				} else {
					x = 1; // 해당 메일 있음
				}
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)  
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// SearchIdPro
	public MemberDataBean searchId(String email, String name) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDataBean member = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("SELECT m_id FROM MEMBER where m_mail = ? and m_name = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberDataBean();
				member.setmId(rs.getString("m_id"));
				
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return member;
	}
	
	// SearchPw
	public int searchPw(String id, String email, int code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDataBean member = null;
		int check = 0;
		
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select m_pw from MEMBER where m_mail = ? and m_id = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberDataBean();
				
				member.setmPw(Integer.toString(code));
				member.setmId(id);
				
				pstmt = conn.prepareStatement("update MEMBER set m_pw = ? where m_id = ?");
				pstmt.setString(1, member.getmPw());
				pstmt.setString(2, member.getmId());
				pstmt.executeUpdate();
				check = 1;
			} else {
				check = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return check; 
	}

	// Delete
	public int deleteMember(String id, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select m_pw from MEMBER where m_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpasswd = rs.getString("m_pw");
				if (dbpasswd.equals(passwd)) {
					pstmt = conn.prepareStatement("update MEMBER set m_confirm = 'delete' where m_id = ?");
					pstmt.setString(1, id); 
					pstmt.executeUpdate();
					x = 1; // 회원탈퇴 성공
				} else
					x = -1; // 비밀번호 틀림
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	// MemberAdminList : 글개수
	public int getMemberListCount() throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select count(*) from member");
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			x = rs.getInt(1);
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
    }
    
	// MemberAdminList : 글목록
    public List getMemberList(int start, int end, String search) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List articleList = null;

    	try {
    		conn = getConnection();
    		if(search != null) {
    			pstmt = conn.prepareStatement(
    				"select m_id, m_name, m_tel, m_mail, m_address, rownum r "
    				+ "from (select m_id, m_name, m_tel, m_mail, m_address, rownum r "
    			    + "from (select * from member where m_id like '%"+search+"%' or m_name like '%"+search+"%' or m_tel like '%"+search+"%' or m_mail like '%"+search+"%' or m_address like '%"+search+"%'"
    			    + "order by m_id) order by m_id) where r >= ? and r <= ?");
    		pstmt.setInt(1, start);
    		pstmt.setInt(2, end);
    		rs = pstmt.executeQuery();
    		
    		} else if(search == null) {
    			pstmt = conn.prepareStatement(
    		    		"select m_id, m_name, m_tel, m_mail, m_address, rownum r "
    		    		+ "from (select m_id, m_name, m_tel, m_mail, m_address, rownum r "
    		    			    + "from (select * from member order by m_id) order by m_id) where r >= ? and r <= ?");
    			pstmt.setInt(1, start);
        		pstmt.setInt(2, end);
        		rs = pstmt.executeQuery();
    		}
    		
    		if(rs.next()) {
    			articleList = new ArrayList(end);
    			do {
    				MemberDataBean article = new MemberDataBean();
					article.setmId(rs.getString("m_id"));
					article.setmName(rs.getString("m_name"));
					article.setmTel(rs.getString("m_tel"));
					article.setmMail(rs.getString("m_mail"));
    				String address = rs.getString("m_address");
    				String address1 = address.replace("/", " ");
					article.setmAddress(address1);
    				articleList.add(article);
    				
    			} while (rs.next());
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		return articleList;
    }
    
    	
    	// MemberAdminList : 글개수
 		public int getMemberListSearchCount(String search) throws Exception {
 	    	Connection conn = null;
 	    	PreparedStatement pstmt = null;
 	    	ResultSet rs = null;
 	    	
 	    	int x = 0;
 	    	
 	    	try {
 	    		conn = getConnection();
 	    		pstmt = conn.prepareStatement("select count(*) from member where m_id like '%"+search+"%' or m_name like '%"+search+"%' or m_tel like '%"+search+"%' or m_mail like '%"+search+"%' or m_address like '%"+search+"%'");
 	    		rs = pstmt.executeQuery();
 	    		
 	    		if(rs.next()) {
 	    			x = rs.getInt(1);
 	    		}
 	    	} catch (Exception ex) {
 	    		ex.printStackTrace();
 	    	} finally {
 	    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
 	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
 	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
 			}
 			return x;
 	    }

}
