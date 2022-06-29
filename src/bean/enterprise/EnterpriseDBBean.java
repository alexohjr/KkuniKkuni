package bean.enterprise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import bean.member.MemberDBBean;
import bean.member.MemberDataBean;
import bean.member.ZipcodeBean;

public class EnterpriseDBBean {
	
	private static EnterpriseDBBean instance = new EnterpriseDBBean();
	
	public static EnterpriseDBBean getInstance() {
		return instance;
	}

	private EnterpriseDBBean() {
	}
	
	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	
	// EnterpriseLoginPro
		public int userCheck(String id, String passwd) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String dbpasswd = "";
			String confirm = "";
			int x = 0;

			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select e_pw, e_confirm from ENTERPRISE where e_id = ?");
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					dbpasswd = rs.getString("e_pw"); // 실제 패스워드
					confirm = rs.getString("e_confirm"); // 업체인증 여부
					if (dbpasswd.equals(passwd)) { // 입력패스워드 , 실제패스워드 비교
						if(!confirm.equals("wait")) {
							x = 1; // 인증 업체 : 로그인가능
						} else {
							x = -1; // 미인증 업체 : 로그인 불가능
						}
					} 
				} else {
					x = 0; // 아이디 or 비밀번호 틀림
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

		// EnterpriseConfirmId
		public int confirmId(String id) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = -1; // 경우의 수

			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select e_Id from ENTERPRISE where e_Id = ?");
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

		// EnterpriseMailCheck
		public int confirmMail(String mail) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = -1; // 경우의 수

			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select * from ENTERPRISE where e_mail = ?");
				pstmt.setString(1, mail);
				rs = pstmt.executeQuery();

				if (rs.next())
					x = 1; // 해당 메일 있음
				else
					x = -1; // 해당 메일 없음
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

		public void enterpriseInsert(EnterpriseDataBean ent) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"insert into ENTERPRISE(e_id, e_pw, e_name, register_no, tmkno, e_type, e_mail, e_confirm, e_zipcode, e_address, tptb_name, tptb_tel, tptb_mail, e_file, e_logo, e_accountno, e_bankname, e_depositor) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, ent.geteId());
				pstmt.setString(2, ent.getePw());
				pstmt.setString(3, ent.geteName());
				pstmt.setString(4, ent.getRegisterNo()); 
				pstmt.setString(5, ent.getTmkno());
				pstmt.setString(6, ent.geteType());
				pstmt.setString(7, ent.geteMail());
				pstmt.setString(8, ent.geteConfirm());
				
				pstmt.setString(9, ent.geteZipcode());
				pstmt.setString(10, ent.geteAddress());
				pstmt.setString(11, ent.getTptbName());
				pstmt.setString(12, ent.getTptbTel());
				pstmt.setString(13, ent.getTptbMail());
				pstmt.setString(14, ent.geteFile());
				pstmt.setString(15, ent.geteLogo());
				pstmt.setString(16, ent.geteAccountNo());
				pstmt.setString(17, ent.geteBankName());
				pstmt.setString(18, ent.geteDepositor());
				
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

		// SearchIdPro
		public EnterpriseDataBean searchId(String email, String name) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			EnterpriseDataBean ent = null;
			
			try {
				conn = getConnection();
				
				pstmt = conn.prepareStatement("SELECT e_id FROM ENTERPRISE where e_mail = ? and e_name = ?");
				pstmt.setString(1, email);
				pstmt.setString(2, name);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					ent = new EnterpriseDataBean();
					ent.seteId(rs.getString("e_id"));
					
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
			return ent;
		}

		
		// SearchPw
		public int searchPw(String id, String email, int code) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			EnterpriseDataBean ent = null;
			int check = 0;
			
			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select e_pw from ENTERPRISE where e_mail = ? and e_id = ?");
				pstmt.setString(1, email);
				pstmt.setString(2, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					ent = new EnterpriseDataBean();
					
					ent.setePw(Integer.toString(code));
					ent.seteId(id);
					
					pstmt = conn.prepareStatement("update ENTERPRISE set e_pw = ? where e_id = ?");
					pstmt.setString(1, ent.getePw());
					pstmt.setString(2, ent.geteId());
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
		
		// enterpriseModifyForm.jsp
		public EnterpriseDataBean getMember(String id) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			EnterpriseDataBean ent = null;
			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select * from ENTERPRISE where e_id = ?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					ent = new EnterpriseDataBean();
					
					ent.seteId(rs.getString("e_id"));
					ent.setePw(rs.getString("e_pw"));
					ent.seteName(rs.getString("e_name"));
					ent.setRegisterNo(rs.getString("register_no"));
					ent.setTmkno(rs.getString("tmkno"));
					ent.seteMail(rs.getString("e_mail"));
					ent.seteType(rs.getString("e_type"));
					ent.seteConfirm(rs.getString("e_confirm"));
					ent.seteZipcode(rs.getString("e_zipcode"));
					ent.seteAddress(rs.getString("e_address"));
					ent.setTptbName(rs.getString("tptb_name"));
					ent.setTptbTel(rs.getString("tptb_tel"));
					ent.seteFile(rs.getString("e_file"));
					ent.seteLogo(rs.getString("e_logo"));
					ent.seteAccountNo(rs.getString("e_accountno"));
					ent.seteBankName(rs.getString("e_bankname"));
					ent.seteDepositor(rs.getString("e_depositor"));
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
			return ent;
		}

		
		// enterpriseModifyPro.jsp
		public void updateMember(EnterpriseDataBean ent) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = getConnection();

				pstmt = conn
						.prepareStatement(
								"update ENTERPRISE set e_pw = ?, e_zipcode = ?, e_address = ?, e_type = ?, tptb_name = ?, tptb_tel = ?,  e_accountno = ?, e_bankname = ?, e_depositor = ?, e_logo = ? where e_id = ?");
				pstmt.setString(1, ent.getePw());
				pstmt.setString(2, ent.geteZipcode());
				pstmt.setString(3, ent.geteAddress());
				pstmt.setString(4, ent.geteType());
				pstmt.setString(5, ent.getTptbName());
				pstmt.setString(6, ent.getTptbTel());
				pstmt.setString(7, ent.geteAccountNo());
				pstmt.setString(8, ent.geteBankName());
				pstmt.setString(9, ent.geteDepositor());
				pstmt.setString(10, ent.geteLogo());
				
				pstmt.setString(11, ent.geteId());

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

		
		// EnterpriseAdminList : 글 개수
	    public int getEnterpriseListCount() throws Exception {
	    	Connection conn = null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	int x = 0;
	    	
	    	try {
	    		conn = getConnection();
	    		pstmt = conn.prepareStatement("select count(*) from enterprise");
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
	    
	    // EnterpriseAdminList : 글 목록
	    public List getEnterpriseList(int start, int end, String search) throws Exception {
	    	Connection conn = null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	List articleList = null;

	    	try {
	    		conn = getConnection();
	    		if(search != null) {
	    			pstmt = conn.prepareStatement(
	    				"select e_id, e_name, register_no, e_type, e_mail, e_confirm, e_address, tptb_tel, rownum r "
	    				+ "from (select e_id, e_name, register_no, e_type, e_mail, e_confirm, e_address, tptb_tel, rownum r "
	    			    + "from (select * from enterprise where e_id like '%"+search+"%' or e_name like '%"+search+"%' or register_no like '%"+search+"%' or e_type like '%"+search+"%' or e_mail like '%"+search+"%'"
	    			    + "or e_confirm like '%"+search+"%' or e_address like '%"+search+"%' or tptb_tel like '%"+search+"%' order by e_id) order by e_id) where r >= ? and r <= ?");
	    		pstmt.setInt(1, start);
	    		pstmt.setInt(2, end);
	    		rs = pstmt.executeQuery();
	    		
	    		} else if(search == null) {
	    			pstmt = conn.prepareStatement(
	    		    		"select e_id, e_name, register_no, e_type, e_mail, e_confirm, e_address, tptb_tel, rownum r "
	    		    		+ "from (select e_id, e_name, register_no, e_type, e_mail, e_confirm, e_address, tptb_tel, rownum r "
	    		    			    + "from (select * from enterprise order by e_id) order by e_id) where r >= ? and r <= ?");
	    			pstmt.setInt(1, start);
	        		pstmt.setInt(2, end);
	        		rs = pstmt.executeQuery();
	    		}
	    		
	    		if(rs.next()) {
	    			articleList = new ArrayList(end);
	    			do {
	    				EnterpriseDataBean article = new EnterpriseDataBean();
						article.seteId(rs.getString("e_id"));
						article.seteName(rs.getString("e_name"));
						article.setRegisterNo(rs.getString("register_no"));
						article.seteType(rs.getString("e_type"));
						article.seteMail(rs.getString("e_mail"));
						article.seteConfirm(rs.getString("e_confirm"));
						String address = rs.getString("e_address");
	    				String address1 = address.replace("/", " ");
						article.seteAddress(address1);
						article.setTptbTel(rs.getString("tptb_tel"));
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

	    // EnterpriseConfirm
	 	public void confirmEnterprise(String eId) throws Exception {
	 		Connection conn = null;
	 		PreparedStatement pstmt = null;

	 		try {
	 			conn = getConnection();
 				pstmt = conn.prepareStatement("update ENTERPRISE set e_confirm = ? where e_id = ?");
 				pstmt.setString(1, "confirm");
 				pstmt.setString(2, eId);
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
	 	
	 	// EnterpriseAdminList : 글 개수
	    public int getEnterpriseListSearchCount(String search) throws Exception {
	    	Connection conn = null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	int x = 0;
	    	
	    	try {
	    		conn = getConnection();
	    		pstmt = conn.prepareStatement("select count(*) from enterprise where e_id like '%"+search+"%' or e_name like '%"+search+"%' or register_no like '%"+search+"%' or e_type like '%"+search+"%' or e_mail like '%"+search+"%'"
	    				+ "or e_confirm like '%"+search+"%' or e_address like '%"+search+"%' or tptb_tel like '%"+search+"%'");
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
	    
	  //관리자 게시판 목록 출력
	    // EnterpriseAdminList : 글 목록
	    public List getEnterpriseBoardList(int start, int end, String search) throws Exception {
	    	Connection conn = null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	List articleList = null;

	    	try {
	    		conn = getConnection();
	    		if(search != null) {
	    			pstmt = conn.prepareStatement(
	    					"select e_id, e_name, register_no, e_type, e_mail, e_confirm, e_address, tptb_tel, rownum r "
	    		    				+ "from (select e_id, e_name, register_no, e_type, e_mail, e_confirm, e_address, tptb_tel, rownum r "
	    		    			    + "from (select * from enterprise where e_id like '%"+search+"%' or e_name like '%"+search+"%' or register_no like '%"+search+"%' or e_type like '%"+search+"%' or e_mail like '%"+search+"%'"
	    		    			    + "or e_confirm like '%"+search+"%' or e_address like '%"+search+"%' or tptb_tel like '%"+search+"%' order by e_id) order by e_id) where r >= ? and r <= ?");
	    		pstmt.setInt(1, start);
	    		pstmt.setInt(2, end);
	    		rs = pstmt.executeQuery();
	    		
	    		} else if(search == null) {
	    			pstmt = conn.prepareStatement(
	    					"select e_name, e_type, e_mail, e_confirm, rownum r "
	    		    				+ "from (select e_name, e_type, e_mail, e_confirm, rownum r "
	    		    			    + "from (select * from enterprise where e_confirm = 'wait' order by e_id) order by e_id) where r >= ? and r <= ?");
	    			pstmt.setInt(1, start);
	        		pstmt.setInt(2, end);
	        		rs = pstmt.executeQuery();
	    		}
	    		
	    		if(rs.next()) {
	    			articleList = new ArrayList(end);
	    			do {
	    				EnterpriseDataBean article = new EnterpriseDataBean();
						article.seteId(rs.getString("e_id"));
						article.seteName(rs.getString("e_name"));
						article.setRegisterNo(rs.getString("register_no"));
						article.seteType(rs.getString("e_type"));
						article.seteMail(rs.getString("e_mail"));
						article.seteConfirm(rs.getString("e_confirm"));
						String address = rs.getString("e_address");
	    				String address1 = address.replace("/", " ");
						article.seteAddress(address1);
						article.setTptbTel(rs.getString("tptb_tel"));
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

}

