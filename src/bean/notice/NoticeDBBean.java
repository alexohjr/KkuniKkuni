package bean.notice;

import java.sql.*;
import java.util.*;

import jdbc.JdbcUtil;

public class NoticeDBBean {
	private static NoticeDBBean instance = new NoticeDBBean();

	public static NoticeDBBean getInstance() {
		return instance;
	}

	private NoticeDBBean() {

	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void insertArticle(NoticeDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(article.getnTitle());
		try {
			conn = getConnection();
			String sql = "insert into notice values(sequence_notice.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, article.getnSort());
			pstmt.setString(2, article.getnTitle());
			pstmt.setTimestamp(3, article.getnDate());
			pstmt.setInt(4, article.getViewCount());
			pstmt.setString(5, article.getnFile());
			pstmt.setString(6, article.getnContents());
			pstmt.setString(7,article.getnRealpath());
			
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// list.jsp : 페이징을 위해서 전체 DB에 입력된 행의수가 필요하다 !??
	public int getArttcleCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);// 컬럼명이 아니라 인덱스 번호로 갖고 온다. getInt("count(*)") 해도 된다.
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
	
	public int getArttcleSearchCount(String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from (select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath,rownum r from "
					+   "(select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath from notice order by notice_no desc) "
					+   "where n_contents  like '%"+ search+ "%' or n_title like '%"+search+"%' order by notice_no desc)");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);// 컬럼명이 아니라 인덱스 번호로 갖고 온다. getInt("count(*)") 해도 된다.
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

	public NoticeDataBean getArcticle(int noticeNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean article = null;
		System.out.println("쿼리");
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("update notice set view_count=view_count+1 where notice_no=?");
			pstmt.setInt(1, noticeNo);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("select * from notice where notice_no=?");
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new NoticeDataBean();
				article.setNoticeNo(rs.getInt("notice_no"));
				article.setnSort(rs.getString("n_sort"));
				article.setnTitle(rs.getString("n_title"));
				article.setnDate(rs.getTimestamp("n_date"));
				article.setViewCount(rs.getInt("view_count"));
				article.setnFile(rs.getString("n_file"));
				article.setnContents(rs.getString("n_contents"));
				article.setnRealpath(rs.getString("n_realpath"));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return article;
	}

	// list.jsp -- > paging!! DB로부터 여러행을 결과로 받는다.
	public List getArcticle(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;

		try {
			conn = getConnection();
			String sql = "select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath,r from "
				+   "(select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath, rownum r from "
				+   "(select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath from notice order by notice_no desc) order by notice_no desc) where r >=? and r <=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					NoticeDataBean article = new NoticeDataBean();
					article.setNoticeNo(rs.getInt("notice_no"));
					article.setnSort(rs.getString("n_sort"));
					article.setnTitle(rs.getString("n_title"));
					article.setnDate(rs.getTimestamp("n_date"));
					article.setViewCount(rs.getInt("view_count"));
					article.setnFile(rs.getString("n_file"));
					article.setnContents(rs.getString("n_contents"));
					article.setnRealpath(rs.getString("n_realpath"));
					
					articleList.add(article);
				} while (rs.next());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return articleList;
	}

	// updateForm.jsp
	public NoticeDataBean updateArticle(int noticeNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean article = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from notice where notice_No = ?");
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new NoticeDataBean();
				article.setNoticeNo(rs.getInt("notice_no"));
				article.setnSort(rs.getString("n_sort"));
				article.setnTitle(rs.getString("n_title"));
				article.setnDate(rs.getTimestamp("n_date"));
				article.setViewCount(rs.getInt("view_count"));
				article.setnFile(rs.getString("n_file"));
				article.setnContents(rs.getString("n_contents"));
				article.setnRealpath(rs.getString("n_realpath"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return article;
	}

	public int updateArticle(NoticeDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from notice where notice_No = ?");
			pstmt.setInt(1, article.getNoticeNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt = conn.prepareStatement("update notice set n_sort=?,n_title=?,n_date=?,view_count=?,n_file=?,n_contents=?,n_realpath=? where notice_no=?");

				pstmt.setString(1, article.getnSort());
				pstmt.setString(2, article.getnTitle());
				pstmt.setTimestamp(3, article.getnDate());
				pstmt.setInt(4, article.getViewCount());
				pstmt.setString(5, article.getnFile());
				pstmt.setString(6, article.getnContents());
				pstmt.setString(7,article.getnRealpath());
				pstmt.setInt(8, article.getNoticeNo());

				pstmt.executeUpdate();
				x = 1;
			} else {
				x = 0;
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

	public void deleteArticle(int noticeNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = getConnection();

			
				pstmt = conn.prepareStatement("delete from notice where notice_no=?");
				pstmt.setInt(1, noticeNo);
				pstmt.executeUpdate();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// search 결과 수
	public int getArticleCount(String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select count(*) from notice where n_contens like '%" + search + "%' or n_title '%" + search + "%'");
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return x;
	}

	public List getArcticle(int start, int end, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;

		try {
			conn = getConnection();
			String sql = "select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath,r from "
					+   "(select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath,rownum r from "
					+   "(select notice_no,n_sort,n_title,n_date,view_count,n_file,n_contents,n_realpath from notice order by notice_no desc) "
					+   "where n_contents  like '%"+ search+ "%' or n_title like '%"+search+"%' order by notice_no desc) where r >=? and r <=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList();
				
				do {
					NoticeDataBean article = new NoticeDataBean();
					article.setNoticeNo(rs.getInt("notice_no"));
					article.setnSort(rs.getString("n_sort"));
					article.setnTitle(rs.getString("n_title"));
					article.setnDate(rs.getTimestamp("n_date"));
					article.setViewCount(rs.getInt("view_count"));
					article.setnFile(rs.getString("n_file"));
					article.setnContents(rs.getString("n_contents"));
					System.out.println(article.getNoticeNo());
					articleList.add(article);
				} while (rs.next());
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return articleList;
	}
	
	public List getNotice() throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List articleList = null;

	      try {
	         conn = getConnection();
	         String sql = "select * from notice order by notice_no desc";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            articleList = new ArrayList();
	            do {
	               NoticeDataBean article = new NoticeDataBean();
	               article.setNoticeNo(rs.getInt("notice_no"));
	               article.setnSort(rs.getString("n_sort"));
	               article.setnTitle(rs.getString("n_title"));
	               article.setnDate(rs.getTimestamp("n_date"));
	               article.setViewCount(rs.getInt("view_count"));
	               article.setnFile(rs.getString("n_file"));
	               article.setnContents(rs.getString("n_contents"));
	               article.setnRealpath(rs.getString("n_realpath"));
	               
	               articleList.add(article);
	            } while (rs.next());
	         }

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         JdbcUtil.close(conn);
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }

	      return articleList;
	   }
	   public int getNoticeCount() throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int x = 0;

	      try {
	         conn = getConnection();

	         pstmt = conn.prepareStatement("select count(*) from notice");
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            x = rs.getInt(1);// 컬럼명이 아니라 인덱스 번호로 갖고 온다. getInt("count(*)") 해도 된다.
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
	   
	   //관리자 게시판 목록 추가
		// list.jsp -- > paging!! DB로부터 여러행을 결과로 받는다.
			public List getBoardArcticle(int start, int end) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;

				try {
					conn = getConnection();
					String sql =  "select notice_no, n_title, n_date, r from "
							+" (select notice_no, n_title, n_date, rownum r from "
							+"(select notice_no, n_title,n_date from notice order by n_date desc) order by n_date desc) where r >=? and r <=?"; 
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end);
						do {
							NoticeDataBean article = new NoticeDataBean();
							article.setNoticeNo(rs.getInt("notice_no"));
							article.setnTitle(rs.getString("n_title"));
							article.setnDate(rs.getTimestamp("n_date"));
							
							articleList.add(article);
						} while (rs.next());
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					JdbcUtil.close(conn);
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}

				return articleList;
			}


}
