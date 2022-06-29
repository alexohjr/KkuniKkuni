package bean.question;

import java.sql.*;
import java.util.*;
import javax.sql.*;

import bean.question.QuestionDataBean;
import javax.naming.*;

public class QuestionDBBean {   
    private static QuestionDBBean instance = new QuestionDBBean();
   
    public static QuestionDBBean getInstance() {
        return instance;
    }
   
    private QuestionDBBean() {
    }
   
    private Connection getConnection() throws Exception {
    	String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
    	return DriverManager.getConnection(jdbcDriver);
    }
    //글 작성
    public void insertMemberArticle(QuestionDataBean article) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
     	
    	try {
    		conn = getConnection();
    		String sql = "insert into question(question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id) values(sequence_question.nextval,?,?,?,?,?,?)";
    		pstmt = conn.prepareStatement(sql);
    		
    		pstmt.setString(1, article.getqSort());
    		pstmt.setString(2, article.getqTitle());
    		pstmt.setString(3, article.getqContents());
    		pstmt.setTimestamp(4, article.getqDate());
    		pstmt.setString(5, article.getqState());
    		pstmt.setString(6, article.getmId());
    		
    		pstmt.executeUpdate();
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    }
    //글의 개수
    public int getMemberArticleCount(String mId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select count(*) from question where m_id = ?");
    		pstmt.setString(1, mId);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			x = rs.getInt(1);
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
    	}
    	return x;
    }
    //글 목록
    public List getMemberArticles(int start, int end, String mId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List articleList = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, r "
    		+ "from (select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, rownum r "
    		+ "from (select * from question where m_id = '"+mId+"' order by question_no desc) order by question_No desc) where r>= ? and r<=?");
    		pstmt.setInt(1, start);
    		pstmt.setInt(2, end);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			articleList = new ArrayList(end);
    			do {
    				QuestionDataBean article = new QuestionDataBean();
    				article.setQuestionNo(rs.getInt("question_No"));
    				article.setqSort(rs.getString("q_Sort"));
    				article.setqTitle(rs.getString("q_Title"));
    				article.setqContents(rs.getString("q_Contents"));
    				article.setqDate(rs.getTimestamp("q_Date"));
    				article.setqState(rs.getString("q_State"));
    				article.setmId(rs.getString("m_Id"));
    				
    				articleList.add(article);
    				
    			} while(rs.next());
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
    //글 내용보기
    public QuestionDataBean getMemberArticle(int questionNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	QuestionDataBean article = null;
    	try {
    		conn = getConnection();
    		
    		pstmt = conn.prepareStatement(
    		"select * from question where question_No = ?");
    		pstmt.setInt(1, questionNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			article = new QuestionDataBean();
    			article.setQuestionNo(rs.getInt("question_No"));
				article.setqSort(rs.getString("q_Sort"));
				article.setqTitle(rs.getString("q_Title"));
				article.setqContents(rs.getString("q_Contents"));
				article.setqDate(rs.getTimestamp("q_Date"));
				article.setqState(rs.getString("q_State"));
				article.setmId(rs.getString("m_Id"));

    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    	return article;
    }
    
  //글 내용보기(해당하는 아이디의 글 출력)
    public QuestionDataBean getMemberArticle(String mId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	QuestionDataBean article = null;
    	try {
    		conn = getConnection();
    		
    		pstmt = conn.prepareStatement(
    		"select * from question where m_Id = ?");
    		pstmt.setString(1, mId);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			article = new QuestionDataBean();
    			article.setQuestionNo(rs.getInt("question_No"));
				article.setqSort(rs.getString("q_Sort"));
				article.setqTitle(rs.getString("q_Title"));
				article.setqContents(rs.getString("q_Contents"));
				article.setqDate(rs.getTimestamp("q_Date"));
				article.setqState(rs.getString("q_State"));
				article.setmId(rs.getString("m_Id"));
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    	return article;
    }
    
    // 글 삭제하는 메서드
    public int deleteMemberArticle(int questionNo, String mId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String dbmId = "";
    	int x = -1;
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select m_Id from question where question_No = ?");
    		pstmt.setInt(1, questionNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			dbmId = rs.getString("m_Id");
    			if(dbmId.equals(mId)) {
    				pstmt = conn.prepareStatement(
    				"delete from question where question_No = ?");
    				pstmt.setInt(1, questionNo);
    				pstmt.executeUpdate();
    				
    				x = 1; //글 삭제 성공
    			} else
    				x = 0; // 아이디 일치 x
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
    //검색 기능 추가
    public int getMemberSearchCount(String search) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	  	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select count(*) from question where q_Title like '%"+search+"%' or q_Contents like '%"+ search +"%'");
    		rs = pstmt.executeQuery();
    		
    		if(rs.next())
    			x = rs.getInt(1);
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
    	}
    	return x;
    }
    
  //글의 개수(서브쿼리)
    public int getMemberQuestionSearchCount(String mId, String search) throws Exception {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       
       int x = 0;
       
       try {
          conn = getConnection();
          pstmt = conn.prepareStatement("select count(*) from (select q_title, q_contents from question where m_id = ?) where q_title like '%"+search+"%' or q_contents like '%"+search+"%'");
          pstmt.setString(1, mId);
          rs = pstmt.executeQuery();
          
          if(rs.next()) {
             x = rs.getInt(1);
          }
       } catch(Exception ex) {
          ex.printStackTrace();
       } finally {
          if(rs != null) try {rs.close();} catch(SQLException ex) {}
          if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
          if(conn != null) try {conn.close();} catch(SQLException ex) {}
       }
       return x;
    }
    
    public List getMemberSearchArticles(int start, int end, String mId, String search) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List articleList = null;
    	
    	try {
    		conn = getConnection();
    		if(search != null) {
    			String sql = "select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, r " 
    			+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, rownum r "
    			+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id "
    			+" from question where m_id = '"+mId+"' order by question_No desc) where q_Title like '%"+search+"%' or q_Contents like '%"+search+"%'"
    			+ "order by question_No desc) where r >=? and r <=?";
    			
    			pstmt = conn.prepareStatement(sql);
    			pstmt.setInt(1, start);
    			pstmt.setInt(2, end);
    			rs = pstmt.executeQuery();
    			
    		} else if(search == null) {
    			pstmt = conn.prepareStatement(
    			"select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, r " 
    	    	+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, rownum r "
    	    	+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id "
    	    	+" from question where m_id = '"+mId+"' order by question_No desc) "
    	    	+ "order by question_No desc) where r >=? and r <=?");
    			
    			pstmt.setInt(1, start);
    			pstmt.setInt(2, end);
    			rs = pstmt.executeQuery();
    					
    		}
    	
    		if(rs.next())
    		{
    			articleList = new ArrayList(end);
    			
    			do {
    				QuestionDataBean article = new QuestionDataBean();
    				article.setQuestionNo(rs.getInt("question_No"));
    				article.setqSort(rs.getString("q_Sort"));
    				article.setqTitle(rs.getString("q_Title"));
    				article.setqContents(rs.getString("q_Contents"));
    				article.setqDate(rs.getTimestamp("q_Date"));
    				article.setqState(rs.getString("q_State"));
    				article.setmId(rs.getString("m_Id"));
    				
    				articleList.add(article);
    			} while (rs.next());
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
    		if(conn != null) try {conn.close();} catch(SQLException ex){}
    	}
    	return articleList;
    }
    //업체
    //글 작성
    public void insertEnterpriseArticle(QuestionDataBean article) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "insert into question(question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id) values(sequence_question.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getqSort());
    		pstmt.setString(2, article.getqTitle());
    		pstmt.setString(3, article.getqContents());
    		pstmt.setTimestamp(4, article.getqDate());
    		pstmt.setString(5, article.getqState());
			pstmt.setString(6, article.geteId());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    }
    //글의 개수
    public int getEnterpriseArticleCount(String eId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select count(*) from question where e_id = ?");
    		pstmt.setString(1, eId);
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
    //글 목록
    public List getEnterpriseArticles(int start, int end, String eId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List articleList = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    				"select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id, r "
    			    + "from (select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id, rownum r "
    			    + "from (select * from question where e_id = '"+eId+"' order by question_No desc) order by question_No desc) where r>= ? and r <=?");
    				pstmt.setInt(1, start);
    				pstmt.setInt(2, end);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			articleList = new ArrayList(end);
    			do {
    				QuestionDataBean article = new QuestionDataBean();
					article.setQuestionNo(rs.getInt("question_No"));
					article.setqSort(rs.getString("q_Sort"));
					article.setqTitle(rs.getString("q_Title"));
					article.setqContents(rs.getString("q_Contents"));
    				article.setqDate(rs.getTimestamp("q_Date"));
    				article.setqState(rs.getString("q_State"));
    				article.seteId(rs.getString("e_Id"));
    				
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
    //글 내용 보기
    public QuestionDataBean getEnterpriseArticle(int questionNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	QuestionDataBean article = null;
    	try {
    		conn = getConnection();
    		
    		pstmt = conn.prepareStatement(
    		"select * from question where question_No = ?");
    		pstmt.setInt(1, questionNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			article = new QuestionDataBean();
    			article.setQuestionNo(rs.getInt("question_No"));
				article.setqSort(rs.getString("q_Sort"));
				article.setqTitle(rs.getString("q_Title"));
				article.setqContents(rs.getString("q_Contents"));
				article.setqDate(rs.getTimestamp("q_Date"));
				article.setqState(rs.getString("q_State"));
				article.seteId(rs.getString("e_Id"));
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    	return article;
    }
    
    public QuestionDataBean getEnterpriseArticle(String eId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionDataBean article = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select * from question where e_Id = ?");
			pstmt.setString(1, eId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new QuestionDataBean();
				article.setQuestionNo(rs.getInt("question_No"));
				article.setqSort(rs.getString("q_Sort"));
				article.setqTitle(rs.getString("q_Title"));
				article.setqContents(rs.getString("q_Contents"));
				article.setqDate(rs.getTimestamp("q_Date"));
				article.setqState(rs.getString("q_State"));
				article.seteId(rs.getString("e_Id"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return article;
	}
    //글 삭제
    public int deleteEnterpriseArticle(int questionNo, String eId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbeId = "";
		int x = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select e_Id from question where question_No = ?");
			pstmt.setInt(1, questionNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbeId = rs.getString("e_Id");
				if(dbeId.equals(eId)) {
					pstmt = conn.prepareStatement(
					"delete from question where question_No =?");
					pstmt.setInt(1, questionNo);
					pstmt.executeUpdate();
					
					x = 1; //삭제 성공
				} else
					x = 0; // 아이디 일치 x
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
    // 검색 기능 추가
 	public int getEnterpriseSearchCount(String search) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		int x = 0;
 		
 		try {
 			conn = getConnection();
 			pstmt = conn.prepareStatement("select count(*) from question where q_Title like '%"+search+"%' or q_Contents like '%"+ search +"%'");
 			rs = pstmt.executeQuery();
 			
 			if(rs.next())
 				x = rs.getInt(1);
 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			if(rs != null) try {rs.close();} catch(SQLException ex) {}
     		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
     		if(conn != null) try {conn.close();} catch(SQLException ex) {}
     	}
     	return x;
 	}
 	
 	//글의 개수(서브쿼리)
    public int getEnterpriseQuestionSearchCount(String eId, String search) throws Exception {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       
       int x = 0;
       
       try {
          conn = getConnection();
          pstmt = conn.prepareStatement("select count(*) from (select q_title, q_contents from question where e_id = ?) where q_title like '%"+search+"%' or q_contents like '%"+search+"%'");
          pstmt.setString(1, eId);
          rs = pstmt.executeQuery();
          
          if(rs.next()) {
             x = rs.getInt(1);
          }
       } catch(Exception ex) {
          ex.printStackTrace();
       } finally {
          if(rs != null) try {rs.close();} catch(SQLException ex) {}
          if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
          if(conn != null) try {conn.close();} catch(SQLException ex) {}
       }
       return x;
    }
 	
 	public List getEnterpriseSearchArticles(int start, int end, String eId, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		try {
			conn = getConnection();
			if(search != null) {
				
				String sql = "select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id, r " 
    				+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id, rownum r "
    				+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id "
    				+" from question where e_id = '"+eId+"' order by question_No desc) where q_Title like '%"+search+"%' or q_Contents like '%"+search+"%'"
    				+ "order by question_No desc) where r >=? and r <=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
				
			} else if(search == null) {
				pstmt = conn.prepareStatement(
				"select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id, r " 
			    +" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id, rownum r "
			    +" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, e_Id "
			    +" from question where e_id = '"+eId+"' order by question_No desc) "
			    + "order by question_No desc) where r >=? and r <=?");		
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
			}
			
			
			
			if(rs.next())
			{
				articleList = new ArrayList(end);
				
				do {
					QuestionDataBean article = new QuestionDataBean();
					article.setQuestionNo(rs.getInt("question_No"));
    				article.setqSort(rs.getString("q_Sort"));
    				article.setqTitle(rs.getString("q_Title"));
    				article.setqContents(rs.getString("q_Contents"));
    				article.setqDate(rs.getTimestamp("q_Date"));
    				article.setqState(rs.getString("q_State"));
    				article.seteId(rs.getString("e_Id"));
    				
    				articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
    		if(conn != null) try {conn.close();} catch(SQLException ex){}
		}
		return articleList;
	}
 	//관리자
 	//답글 작성
 	public void insertAdminArticle(String aContents, int questionNo)  throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		
 		try {
 			conn = getConnection();
 			String sql = "insert into answer values(?,?,?)";
 			pstmt = conn.prepareStatement(sql);

 			pstmt.setString(1, aContents);
 			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
 			pstmt.setInt(3, questionNo);
 			pstmt.executeUpdate();		
 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
 		
 		try {
 			conn = getConnection();	
 			String sql = "update question set q_state='T' where question_No=?";
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setInt(1, questionNo);
 			
 			pstmt.executeUpdate();
 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
 	}
 	//글의 개수
 	public int getAdminArticleCount() throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select count(*) from question");
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			x = rs.getInt(1);
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
    	}
    	return x;
    }
 	//문의글 목록(회원과 업체의 글이 모두 모인다.)
 	public List getAdminArticles(int start, int end) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		List articleList = null;
 		
 		try {
 			conn = getConnection();
 			pstmt = conn.prepareStatement(
 			"select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, r "
 			+"from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, rownum r "
 			+"from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_id "
 			+"from question order by question_No desc) order by question_No desc) where r >=? and r <= ?");
 			
 			pstmt.setInt(1, start);
 			pstmt.setInt(2, end);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				articleList = new ArrayList(end);
 				do {
 					QuestionDataBean article = new QuestionDataBean();
 					article.setQuestionNo(rs.getInt("question_No"));
 					article.setqSort(rs.getString("q_Sort"));
 					article.setqTitle(rs.getString("q_Title"));
 					article.setqContents(rs.getString("q_Contents"));
 					article.setqDate(rs.getTimestamp("q_Date"));
 					article.setqState(rs.getString("q_State"));
 					article.setmId(rs.getString("m_Id"));
 					article.seteId(rs.getString("e_Id"));
 					
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
 	//(회원과 업체의)글 내용 보기
 	public QuestionDataBean getAdminArticle(int questionNo) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		QuestionDataBean article = null;
 		try {
 			conn = getConnection();
 			
 			pstmt = conn.prepareStatement(
 			"select * from question where question_No = ?");
 			pstmt.setInt(1, questionNo);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				article = new QuestionDataBean();
 				article.setQuestionNo(rs.getInt("question_No"));
				article.setqSort(rs.getString("q_Sort"));
				article.setqTitle(rs.getString("q_Title"));
				article.setqContents(rs.getString("q_Contents"));
				article.setqDate(rs.getTimestamp("q_Date"));
				article.setqState(rs.getString("q_State"));
				article.setmId(rs.getString("m_Id"));
				article.seteId(rs.getString("e_Id"));

 			}
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
 		}
 		return article;
 	}
	//실제 답글을 수정하는 메서드
 	public int updateAdminArticle(String aContents, int questionNo) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		String sql = "";
 		
 		int x = -1;
 		try {
 			conn = getConnection();
 			
 			pstmt = conn.prepareStatement(
 			"select * from answer where question_No = ?");
 			pstmt.setInt(1, questionNo);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				sql = "update answer set a_Contents=?, a_Date=? where question_No = ?";
 				pstmt = conn.prepareStatement(sql);
 				
 				pstmt.setString(1, aContents);
 				pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
 				pstmt.setInt(3, questionNo);
 				
 				pstmt.executeUpdate();
 				
 				x = 1;
 			} else {
 				x = 0;
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
 	//답글 삭제하는 메서드
 	public void deleteAdminArticle(int questionNo) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		String sql = "";
 		
 		try {
 			conn = getConnection();
 			sql = "delete from answer where question_No = ?";
 			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionNo);
			pstmt.executeUpdate();
			
			sql = "update question set q_state='F' where question_No = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionNo);
			pstmt.executeUpdate();
 	 				
 			} catch (Exception ex) {
 				ex.printStackTrace();
 				} finally {
 					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
 					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
 					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
 					}
 	}
 	//관리자 검색 기능 추가(회원과 업체의 글제목, 내용)
 	public int getAdminSearchCount(String search) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		int x = 0;
 		
 		try {
 			conn = getConnection();
 			pstmt = conn.prepareStatement(
 			"select count(*) from question where q_Title like '%"+search+"%' or q_Contents like '%"+search+"%' or q_Contents like '%"+ search +"%'");
 			rs = pstmt.executeQuery();
 			if(rs.next())
 				x = rs.getInt(1);
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
 		}
 		return x;
 	}
 	
 	public List getAdminSearchArticles(int start, int end, String search) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		List articleList = null;
 		
 		try {
 			conn = getConnection();
 			if(search != null) {
 				String sql = "select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, r " 
 	    		+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, rownum r "
 	    		+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id "
 	    		+" from question order by question_No desc) where q_Title like '%"+search+"%' or q_Contents like '%"+search+"%'"
 	    		+ "order by question_No desc) where r >=? and r <=?";
 	    		
 	    		pstmt = conn.prepareStatement(sql);
 	    		pstmt.setInt(1, start);
 	    		pstmt.setInt(2, end);
 	    		
 	    		rs = pstmt.executeQuery();
 				
 			} else if (search == null) {
 				pstmt = conn.prepareStatement("select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, r " 
 		 	    +" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, rownum r "
 		 	    +" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id "
 		 	    +" from question order by question_No desc) "
 		 	    + "order by question_No desc) where r >=? and r <=?");
 				
 				pstmt.setInt(1, start);
 				pstmt.setInt(2, end);
 				rs = pstmt.executeQuery();
 						
 			}
    		
    		if(rs.next())
    		{
    			articleList = new ArrayList(end);
    			
    			do {
    				QuestionDataBean article = new QuestionDataBean();
    				article.setQuestionNo(rs.getInt("question_No"));
    				article.setqSort(rs.getString("q_Sort"));
    				article.setqTitle(rs.getString("q_Title"));
    				article.setqContents(rs.getString("q_Contents"));
    				article.setqDate(rs.getTimestamp("q_Date"));
    				article.setqState(rs.getString("q_State"));
    				article.setmId(rs.getString("m_Id"));
    				article.seteId(rs.getString("e_Id"));
    				
    				articleList.add(article);
    			} while (rs.next());
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
    		if(conn != null) try {conn.close();} catch(SQLException ex){}
    	}
    	return articleList;
    }
 	//답글 조인
 	public QuestionDataBean getAdminAnswer(int questionNo) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		QuestionDataBean articleAnswer = new QuestionDataBean();
 		try {
 			conn = getConnection();
 			pstmt = conn.prepareStatement(
 			"select a_contents, a_date, a.question_no from answer a, question q where (a.question_no = q.question_no) and a.question_No=?");
 			pstmt.setInt(1, questionNo);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next()) {
 				articleAnswer.setaContents(rs.getString("a_Contents"));
 				articleAnswer.setaDate(rs.getTimestamp("a_Date"));
 				articleAnswer.setQuestionNo(rs.getInt("question_No"));
 			}
 			 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
 		}
 		return articleAnswer;
 	} 
 	
 	public int AnswerCount(int questionNo) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		int x = 0;
 		
 		try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select count(*) from answer where question_no = ?");
    		pstmt.setInt(1, questionNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			x = rs.getInt(1);
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
    	}
    	return x;
    }
 	// 문의글 삭제 메서드
 	public void AnswerArticleDelete(int questionNo) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		
 		try {
 			conn = getConnection();
 			pstmt = conn.prepareStatement(
 			"delete from question where question_No = ?");
 			pstmt.setInt(1, questionNo);
 			pstmt.executeUpdate();
 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
 			if(conn != null) try {conn.close();} catch(SQLException ex) {}
 		}
 	}
 	
 	public List getAdminBoardArticles(int start, int end, String search) throws Exception {
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		List articleList = null;
 		
 		try {
 			conn = getConnection();
 			if(search != null) {
 				String sql = "select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, r " 
 		 	    		+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id, rownum r "
 		 	    		+" from(select question_No, q_Sort, q_Title, q_Contents, q_Date, q_State, m_Id, e_Id "
 		 	    		+" from question order by question_No desc) where q_Title like '%"+search+"%' or q_Contents like '%"+search+"%'"
 		 	    		+ "order by question_No desc) where r >=? and r <=?";
 	    		
 	    		pstmt = conn.prepareStatement(sql);
 	    		pstmt.setInt(1, start);
 	    		pstmt.setInt(2, end);
 	    		
 	    		rs = pstmt.executeQuery();
 				
 			} else if (search == null) {
 				pstmt = conn.prepareStatement("select question_No, q_Title, m_Id, e_Id q_Date, r " 
 		 	    +" from(select question_No, q_Title, m_Id, e_Id, q_Date, rownum r "
 		 	    +" from(select question_No, q_Title, m_Id, e_Id, q_Date  "
 		 	    +" from question order by q_date desc) "
 		 	    + "order by q_date desc) where r >=? and r <=?");
 				
 				pstmt.setInt(1, start);
 				pstmt.setInt(2, end);
 				rs = pstmt.executeQuery();
 						
 			}
    		
    		if(rs.next())
    		{
    			articleList = new ArrayList(end);
    			
    			do {
    				QuestionDataBean article = new QuestionDataBean();
    	 				article.setQuestionNo(rs.getInt("question_No"));
    					article.setqSort(rs.getString("q_Sort"));
    					article.setqTitle(rs.getString("q_Title"));
    					article.setqContents(rs.getString("q_Contents"));
    					article.setqDate(rs.getTimestamp("q_Date"));
    					article.setqState(rs.getString("q_State"));
    					article.setmId(rs.getString("m_Id"));
    					article.seteId(rs.getString("e_Id"));
    				
    				articleList.add(article);
    			} while (rs.next());
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(rs != null) try {rs.close();} catch(SQLException ex) {}
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
    		if(conn != null) try {conn.close();} catch(SQLException ex){}
    	}
    	return articleList;
    }

}
