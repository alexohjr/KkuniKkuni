package bean.cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.item.ItemDataBean;
import bean.member.MemberDataBean;

public class CartDBBean {
	private static CartDBBean instance = new CartDBBean();

	public static CartDBBean getInstance() {
		return instance;
	}

	private CartDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// ��Ʈ�� ������?
	public int getCartlist(String mId, int itemNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from cart where m_id = ? and item_no = ?");
			pstmt.setString(1, mId);
			pstmt.setInt(2, itemNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}

	// list.jsp : ����¡�� ���ؼ� ��ü DB�� �Էµ� ���Ǽ��� �ʿ��ϴ�...!!!
	public int getCartCount(String mId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from cart where m_id = ?");
			pstmt.setString(1, mId);
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

	// list.jsp ==> Paging!!! DB�κ��� �������� ����� �޴´�.
	public List getCartlist(int start, int end, String mId) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List cartLists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select item_no, itemid, price, t_realpath ,r from (select item_no, itemid, price, t_realpath, rownum r  from\r\n" + 
					"(select item_no, itemid, price, t_realpath from cart natural join item where m_id= ? order by item_no desc) order by item_no desc) where r >= ? and r <= ?"
					);
			pstmt.setString(1, mId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cartLists = new ArrayList<>();
				
				do {
					
					ItemDataBean article = new ItemDataBean();
					article.setItemNo(rs.getString("item_No"));
					article.setItemId(rs.getString("itemId"));
					article.setPrice(rs.getInt("price"));
					article.setThumbnail(rs.getString("t_realpath"));
					cartLists.add(article);
					
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
		return cartLists;
	}

	// ������Ʈ�����
	public void insertCartlist(String mId, int itemNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into cart values (?,?)");
			pstmt.setString(1, mId);
			pstmt.setInt(2, itemNo);
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
		}
	}

	// ȸ����Ʈ�����
	public void deleteCartlist(String mId, int itemNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from cart where m_id = ? and item_no = ?");
			pstmt.setString(1, mId);
			pstmt.setInt(2, itemNo);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
		}
	}
}
