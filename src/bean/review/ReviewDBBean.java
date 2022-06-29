package bean.review;

import java.sql.*;
import java.util.*;
import javax.sql.*;

import bean.item.ItemDataBean;
import bean.review.ReviewDataBean;
import javax.naming.*;

public class ReviewDBBean {
	public static ReviewDBBean instance = new ReviewDBBean();
	
	public static ReviewDBBean getInstance() {
		return instance;
	}
	
	private ReviewDBBean() {
	}
	
    private Connection getConnection() throws Exception {
    	String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
    	return DriverManager.getConnection(jdbcDriver);
    }
    // 후기글 작성
    public void insertReview(ReviewDataBean article) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		conn = getConnection();
    		String sql = "insert into review values(sequence_review.nextval,?,?,?,?,?,?)";
    		pstmt = conn.prepareStatement(sql);
    		
    		pstmt.setString(1, article.getrContents());
    		pstmt.setTimestamp(2, article.getrDate());
    		pstmt.setString(3, article.getrFile());
    		pstmt.setString(4, article.getGrade());
    		pstmt.setString(5, article.getmId());
    		pstmt.setInt(6, article.getItemNo());
    		
    		pstmt.executeUpdate();
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    }
    //수정 폼에 한 줄의 데이터를 가져온다.
    public ReviewDataBean updateGetArticle(int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ReviewDataBean article = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("select * from review where review_No = ?");
    		pstmt.setInt(1, reviewNo);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			article = new ReviewDataBean();
    			article.setReviewNo(rs.getInt("review_No"));
    			article.setrContents(rs.getString("r_Contents"));
    			article.setrDate(rs.getTimestamp("r_Date"));
    			article.setrFile(rs.getString("r_File"));
    			article.setGrade(rs.getString("grade"));
    			article.setmId(rs.getString("m_Id"));
    			article.setItemNo(rs.getInt("item_No"));
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
    
    //후기글 데이터를 실제 수정하는 메서드
    public void modifyReview(ReviewDataBean article) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"update review set r_contents = ?, r_file = ?, r_date = ? where review_No = ?");
    		pstmt.setString(1, article.getrContents());
    		pstmt.setString(2, article.getrFile());
    		pstmt.setTimestamp(3, article.getrDate());
    		pstmt.setInt(4, article.getReviewNo());
    		pstmt.executeQuery();
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
 			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
 			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
 		}
    }
    
    
    //후기글 삭제 메서드
    public void deleteReview(int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"delete from review where review_No = ?");
    		pstmt.setInt(1, reviewNo);
    		pstmt.executeQuery();
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    }
    
    
/*    //후기글 검색기능 추가
    public int getReviewSearchCount(String search) throws Exception {
    	Connection conn= null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x= 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select count(*) from review where item_no like '%"+search+"%'");
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
    
    public List getReviewSearchArticles(int start, int end, String mId, String search) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select review_No, r_Contents, r_Date, r_File, Grade, m_Id, item_No, r "
    		+" from(select review_No, r_Contents, r_Date, r_File, Grade, m_Id, item_No rownum r "
    		+" from(select review_No, r_Contents, r_Date, r_File, Grade, m_Id, item_No "
    		+" from review where m_Id = '"+mId+"' order by review_No) where item_no like 
    	}
    }*/
     //후기 답글 작성
    public void insertEnterpriseReview(ReviewDataBean article) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		conn = getConnection();
    		String sql = "insert into r_comment(review_No, c_Contents, e_Id) values(sequence_review.nextval,?, ?)";
    		pstmt = conn.prepareStatement(sql);
    		
    		pstmt.setString(1, article.getcContents());
    		pstmt.setString(2, article.geteId());
    		pstmt.executeUpdate();
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    }
    //후기 답글 수정
    public int updateEnterpriseReview(String cContents, int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = -1;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select * from r_comment where review_no = ?");
    		pstmt.setInt(1, reviewNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			String sql = "update r_comment set c_Contents=? where review_No =?";
    			pstmt = conn.prepareStatement(sql);
    			
    			pstmt.setString(1, cContents);
    			pstmt.setInt(2, reviewNo);
    			
    			pstmt.executeUpdate();
    			
    			x = -1;
    		} else {
    			x = 0;
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
 			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
 			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
 		}
 		return x;
    }
    // 후기 답글 삭제
    public int deleteEnterpirseReview(int reviewNo, String eId) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String dbeId = "";
    	
    	int x = -1;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    				"select e_Id from question where review_No =?");
    		pstmt.setInt(1, reviewNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			dbeId = rs.getString("e_Id");
    			if(dbeId.equals(eId)) {
    				pstmt = conn.prepareStatement(
    				"delete from review where review_No =?");
    				pstmt.setInt(1, reviewNo);
    	    		pstmt.executeUpdate();
    	    		
    	    		x = 1;
    			} else
    				x = 0;
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
    		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	}
    	return x;
    }
    //업체 후기(회원의 후기글과 조인)
    public ReviewDataBean getEnterpriseAnswer(int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	ReviewDataBean articleAnswer = new ReviewDataBean();
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select a.review_No, c_contents, e_Id from r_comment a, review b where (a.reviewNo == b.reviewNo) and a.reviewNo =?");
    		pstmt.setInt(1, reviewNo);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			articleAnswer.setrContents(rs.getString("c_Contents"));
    			articleAnswer.setReviewNo(rs.getInt("review_No"));
    			articleAnswer.seteId(rs.getString("e_Id"));
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
    
    public int reviewCount(int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select count(*) from r_Comment where review_No = ?");
    		pstmt.setInt(1, reviewNo);
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
    //관리자 후기 삭제 기능
    public void reviewAdminDelete(int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"delete from review where review_No = ?");
    		pstmt.setInt(1, reviewNo);
    		pstmt.executeUpdate();
    				
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    		if(conn != null) try {conn.close();} catch(SQLException ex) {}
    	}
    }
    
    //후기 상세페이지
    public ReviewDataBean getReview(int reviewNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ReviewDataBean article = null;
    	int itemNo = 0;
    	
    	try {
    		conn = getConnection();
    		
    		pstmt = conn.prepareStatement(
    		"select * from review where review_No=?");
    		pstmt.setInt(1, reviewNo);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			article = new ReviewDataBean();
    			article.setReviewNo(rs.getInt("review_No"));
    			article.setrContents(rs.getString("r_Contents"));
    			article.setrDate(rs.getTimestamp("r_Date"));
    			article.setrFile(rs.getString("r_File"));
    			article.setGrade(rs.getString("grade"));
    			article.setmId(rs.getString("m_Id"));
    			article.setItemNo(rs.getInt("item_No"));
    			
    			itemNo = rs.getInt("item_no");
    			 
    			if(itemNo != 0) {  
    				pstmt = conn.prepareStatement(
    						"select itemid, t_realpath from item where item_no = ?");
    				pstmt.setInt(1, itemNo);
    				rs = pstmt.executeQuery();
    				
    				if(rs.next()) {
    					article.setItemId(rs.getString("itemid"));
    					article.settRealpath(rs.getString("t_realpath"));
    				}
    			}
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

    // itemdetailReview
    public int detailReviewCount(int itemNo) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	int x = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    		"select count(*) from review where item_No = ?");
    		pstmt.setInt(1, itemNo);
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

    // itemDetailReviewList
    public List getReviews(int start, int end, int itemNo) throws Exception {
	     
    	  Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List reviewList = null;
	      
	      try {
	         conn = getConnection();
	         
	         pstmt = conn.prepareStatement("select review_no, r_contents, r_date, r_file, grade, m_id, item_no, r "
	         		+ "from (select review_no, r_contents, r_date, r_file, grade, m_id, item_no, rownum r "
	         		+ "from (select * from review where item_no = ? order by review_no desc) order by review_no desc) where r >= ? and r <= ?");
	       
	         pstmt.setInt(1, itemNo);
	         pstmt.setInt(2, start);
	         pstmt.setInt(3, end);  
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {

	        	 reviewList = new ArrayList();
	            do {
	               ReviewDataBean article = new ReviewDataBean();
	               article.setReviewNo(rs.getInt("review_no"));
	               article.setrContents(rs.getString("r_contents"));
	               article.setrDate(rs.getTimestamp("r_date"));
	               article.setrFile(rs.getString("r_file"));
	               article.setGrade(rs.getString("grade"));
	               article.setmId(rs.getString("m_id"));
	               article.setItemNo(rs.getInt("item_no"));
	               String rContents = rs.getString("r_contents");
	               if(rContents.length() >= 10) {
	            	   article.setrTitle(rContents.substring(0, 10));
	               }
	               
	               reviewList.add(article);
	               
	            } while (rs.next());
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
	      return reviewList;
	   }


	    public int memberReviewCount(String mId) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        int x = 0;
	        
	        try {
	           conn = getConnection();
	           pstmt = conn.prepareStatement(
	           "select count(*) from review where m_id = ?");
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
	    
	    public int getReviewCount(String eId) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        int x = 0;

	        try {
	           conn = getConnection();
	           pstmt = conn.prepareStatement(
	                 "select count(*)from review natural join item where e_id=? order by review_no desc");
	           pstmt.setString(1, eId);
	           rs = pstmt.executeQuery();

	           if (rs.next()) {
	              x = rs.getInt(1);
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

	     public List getReviews(String eId) throws Exception {

	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List reviewList = null;

	        try {
	           conn = getConnection();

	           pstmt = conn.prepareStatement("select * from review natural join item where e_id=?");

	           pstmt.setString(1, eId);
	           rs = pstmt.executeQuery();

	           if (rs.next()) {

	              reviewList = new ArrayList();
	              do {
	                 ReviewDataBean article = new ReviewDataBean();
	                 article.setReviewNo(rs.getInt("review_no"));
	                 article.setrContents(rs.getString("r_contents"));
	                 article.setrDate(rs.getTimestamp("r_date"));
	                 article.setrFile(rs.getString("r_file"));
	                 article.setGrade(rs.getString("grade"));
	                 article.setmId(rs.getString("m_id"));
	                 article.setItemNo(rs.getInt("item_no"));
	                 String rContents = rs.getString("r_contents");
	                 if (rContents.length() >= 10) {
	                    article.setrTitle(rContents.substring(0, 10));
	                 }

	                 reviewList.add(article);

	              } while (rs.next());
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
	        return reviewList;
	     }
}

