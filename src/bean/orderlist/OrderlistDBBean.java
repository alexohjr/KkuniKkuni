package bean.orderlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderlistDBBean {

	private static OrderlistDBBean instance = new OrderlistDBBean();

	public static OrderlistDBBean getInstance() {
		return instance;
	}

	private OrderlistDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void insertOrderlist(OrderlistDataBean orderlist) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";

		try {
			conn = getConnection();
			sql = "insert into orderlist(order_no,orderDate,rental_d1,rental_d2,order_amount,"
					+ "pay_total,order_method,order_state,order_Meg,m_id,e_id,item_no) values (ORDER_SEQ.nextval,"
					+ "?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, orderlist.getOrderDate());
			pstmt.setDate(2, orderlist.getRentalD1());
			pstmt.setDate(3, orderlist.getRentalD2());
			pstmt.setInt(4, orderlist.getOrderAmount());
			pstmt.setInt(5, orderlist.getPayTotal());
			pstmt.setString(6, orderlist.getPayMethod());
			pstmt.setString(7, orderlist.getOrderState());
			pstmt.setString(8, orderlist.getOrderMeg());
			pstmt.setString(9, orderlist.getmId());
			pstmt.setString(10, orderlist.geteId());
			pstmt.setString(11, orderlist.getItemNo());

			pstmt.executeUpdate();

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
					conn.close();
				} catch (Exception ex) {
				}
		}

	}

	public List getOrderlists(String mId, int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
							+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 where m_id = ? order by meo1.orderdate desc)"
							+ " meo2 where r >=? and r<=?");

			pstmt.setString(1, mId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setitemName(rs.getString("itemid"));
					orderlist.seteName(rs.getString("e_name"));
					orderlist.setmName(rs.getString("m_name"));

					if (rs.getString("order_state").equals("complete")) {
						orderlist.setOrderState("결제완료");
					}

					if (rs.getString("order_state").equals("cancel")) {
						orderlist.setOrderState("결제취소");
					}
					
					if (rs.getString("order_state").equals("canceling")) {
						orderlist.setOrderState("취소대기");
					}

					if (rs.getString("order_state").equals("delivering")) {
						orderlist.setOrderState("배송중");
					}

					if (rs.getString("order_state").equals("deliveried")) {
						orderlist.setOrderState("대여완료");
					}

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}

	public List getAdminOrderlists(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
							+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 order by meo1.orderdate desc)"
							+ " meo2 where r >=? and r<=?");

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.setmId(rs.getString("m_id"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setParcelNum(rs.getString("parcel_num"));
					orderlist.setitemName(rs.getString("itemid"));
					orderlist.seteName(rs.getString("e_name"));
					orderlist.setmName(rs.getString("m_name"));

					if (rs.getString("order_state").equals("complete")) {
						orderlist.setOrderState("결제완료");
					}

					if (rs.getString("order_state").equals("cancel")) {
						orderlist.setOrderState("결제취소");
					}
					
					if (rs.getString("order_state").equals("canceling")) {
						orderlist.setOrderState("취소대기");
					}

					if (rs.getString("order_state").equals("delivering")) {
						orderlist.setOrderState("배송중");
					}

					if (rs.getString("order_state").equals("deliveried")) {
						orderlist.setOrderState("대여완료");
					}

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}

	public List getEnterpriseOrderlists(String eId, int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
							+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 where e_id = ? order by meo1.orderdate desc)"
							+ " meo2 where r >=? and r<=?");

			pstmt.setString(1, eId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setmId(rs.getString("m_id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setParcelNum(rs.getString("parcel_num"));
					orderlist.setitemName(rs.getString("itemid"));
					orderlist.seteName(rs.getString("e_name"));
					orderlist.setmName(rs.getString("m_name"));

					if (rs.getString("order_state").equals("complete")) {
						orderlist.setOrderState("결제완료");
					}

					if (rs.getString("order_state").equals("cancel")) {
						orderlist.setOrderState("결제취소");
					}
					
					if (rs.getString("order_state").equals("canceling")) {
						orderlist.setOrderState("취소대기");
					}
					
					if (rs.getString("order_state").equals("delivering")) {
						orderlist.setOrderState("배송중");
					}

					if (rs.getString("order_state").equals("deliveried")) {
						orderlist.setOrderState("대여완료");
					}

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}

	public List getOrderlistsState(String mId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select * from orderlist where m_id = ? and (order_state='deliveringorder' or order_state='complete') order by orderdate desc");
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setOrderMeg(rs.getString("order_meg"));

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}

	public OrderlistDataBean getOrderDetail(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderlistDataBean orderlist = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from orderlist where order_no = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlist = new OrderlistDataBean();
				orderlist.setOrderNo(rs.getInt("order_No"));
				orderlist.setOrderAmount(rs.getInt("order_amount"));
				orderlist.setRentalD1(rs.getDate("rental_D1"));
				orderlist.setRentalD2(rs.getDate("rental_D2"));
				orderlist.setPayTotal(rs.getInt("pay_total"));
				orderlist.setOrderMeg(rs.getString("order_meg"));
				orderlist.setmId(rs.getString("m_id"));
				orderlist.setOrderState(rs.getString("order_State"));
				orderlist.setParcelNum(rs.getString("parcel_Num"));
				orderlist.setItemNo(rs.getString("item_No"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return orderlist;
	}

	public int getOrderlistCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 order by meo1.orderdate desc)"
							+ " meo2");

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
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}

	public int getMemOrderlistCount(String memId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 where m_id = ? order by meo1.orderdate desc)"
							+ " meo2");
			pstmt.setString(1, memId);
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
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}

	public int getOrderEnterpriselistCount(String eId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 where e_id = ? order by meo1.orderdate desc)"
							+ " meo2");
			pstmt.setString(1, eId);
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
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}

	public int getOrderlistStateCount(String mId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select count(*) from orderlist where m_id=? and (order_state='deliveringorder' or order_state='complete')");
			pstmt.setString(1, mId);
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
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}

	public void updateParcelNum(String parcelNUm, int orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update orderlist set parcel_num=?, order_state= 'delivering' where order_no=?");
			pstmt.setString(1, parcelNUm);
			pstmt.setInt(2, orderNo);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
	}
	
	public void updateDeliveried(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update orderlist set order_state='deliveried' where order_no=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
	}
	
	
	public void updateCanceling(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update orderlist set order_state='canceling' where order_no=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
	}


	public void deleteOrderlist(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update orderlist set order_state='cancel' where order_no=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
	}
	
	//검색
	
	public List getSearchMemOrderlists(String mId, int start, int end, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
							+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 where m_id = ? and itemid like '%"+search +"%' order by meo1.orderdate desc)"
							+ " meo2 where r >=? and r<=?");

			pstmt.setString(1, mId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setitemName(rs.getString("itemid"));
					orderlist.seteName(rs.getString("e_name"));
					orderlist.setmName(rs.getString("m_name"));

					if (rs.getString("order_state").equals("complete")) {
						orderlist.setOrderState("결제완료");
					}

					if (rs.getString("order_state").equals("cancel")) {
						orderlist.setOrderState("결제취소");
					}
					
					if (rs.getString("order_state").equals("canceling")) {
						orderlist.setOrderState("취소대기");
					}

					if (rs.getString("order_state").equals("delivering")) {
						orderlist.setOrderState("배송중");
					}

					if (rs.getString("order_state").equals("deliveried")) {
						orderlist.setOrderState("대여완료");
					}

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}
	
	public int getSearchMemOrderlistCount(String mId, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1"
							+ " where m_id = ? and itemid like '%"+search +"%' order by meo1.orderdate desc) meo2");

			pstmt.setString(1, mId);
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
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}
	
	public List getSearchAdminOrderlists(int start, int end, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
							+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1"
							+ " where itemid like '%"+search +"%' or m_name like '%"+search +"%' or m_id like '%"+search+"%' order by meo1.orderdate desc)"
							+ " meo2 where r >=? and r<=?");

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.setmId(rs.getString("m_Id"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setitemName(rs.getString("itemid"));
					orderlist.seteName(rs.getString("e_name"));
					orderlist.setmName(rs.getString("m_name"));

					if (rs.getString("order_state").equals("complete")) {
						orderlist.setOrderState("결제완료");
					}

					if (rs.getString("order_state").equals("cancel")) {
						orderlist.setOrderState("결제취소");
					}
					
					if (rs.getString("order_state").equals("canceling")) {
						orderlist.setOrderState("취소대기");
					}

					if (rs.getString("order_state").equals("delivering")) {
						orderlist.setOrderState("배송중");
					}

					if (rs.getString("order_state").equals("deliveried")) {
						orderlist.setOrderState("대여완료");
					}

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}
	
	public int getSearchAdminOrderlistCount(String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
							+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
							+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
							+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1"
							+ " where itemid like '%"+search +"%' or m_name like '%"+search +"%' or m_id like '%"+search+"%' order by meo1.orderdate desc) meo2");

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
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}
	
	public List getSearchEnterpriseOrderlists(String eId, int start, int end, String search, int searchn) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List orderlists = null;

		try {
			conn = getConnection();
			
			if(searchn == 0) {
				pstmt = conn.prepareStatement(
						"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
								+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and order_no like '%"+search + "%' order by meo1.orderdate desc) meo2 where r >=? and r<=?");
			}
				
			else if(searchn == 1) {
				pstmt = conn.prepareStatement(
						"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
								+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and itemid like '%"+search + "%' order by meo1.orderdate desc) meo2 where r >=? and r<=?");
			}
				
			else if(searchn == 2) {
				pstmt = conn.prepareStatement(
						"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
								+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and m_id like '%"+search + "%' order by meo1.orderdate desc) meo2 where r >=? and r<=?");
			}

			else {
				pstmt = conn.prepareStatement(
						"select meo2.order_no, meo2.orderdate, meo2.rental_d1, meo2.rental_d2, meo2.order_amount, meo2.pay_total, meo2.order_state, meo2.parcel_num,"
								+ " meo2.itemid, meo2.m_name, meo2.e_name, e_id,m_id,item_no,r from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and m_name like '%"+search + "%' order by meo1.orderdate desc) meo2 where r >=? and r<=?");
			}
			

			pstmt.setString(1, eId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderlists = new ArrayList();
				do {
					OrderlistDataBean orderlist = new OrderlistDataBean();
					orderlist.setOrderNo(rs.getInt("order_No"));
					orderlist.setOrderDate(rs.getTimestamp("orderDate"));
					orderlist.setItemNo(rs.getString("item_No"));
					orderlist.seteId(rs.getString("e_Id"));
					orderlist.setmId(rs.getString("m_Id"));
					orderlist.setPayTotal(rs.getInt("pay_total"));
					orderlist.setRentalD1(rs.getDate("rental_D1"));
					orderlist.setRentalD2(rs.getDate("rental_D2"));
					orderlist.setOrderAmount(rs.getInt("order_Amount"));
					orderlist.setitemName(rs.getString("itemid"));
					orderlist.seteName(rs.getString("e_name"));
					orderlist.setmName(rs.getString("m_name"));

					if (rs.getString("order_state").equals("complete")) {
						orderlist.setOrderState("결제완료");
					}

					if (rs.getString("order_state").equals("cancel")) {
						orderlist.setOrderState("결제취소");
					}
					
					if (rs.getString("order_state").equals("canceling")) {
						orderlist.setOrderState("취소대기");
					}

					if (rs.getString("order_state").equals("delivering")) {
						orderlist.setOrderState("배송중");
					}

					if (rs.getString("order_state").equals("deliveried")) {
						orderlist.setOrderState("대여완료");
					}

					orderlists.add(orderlist);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}

		return orderlists;
	}
	
	public int getSearchEnterpriseOrderlistCount(String eId, String search, int searchn) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();
			
			if(searchn == 0) {
				pstmt = conn.prepareStatement(
						"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and order_no like '%"+search + "%' order by meo1.orderdate desc) meo2");
			}
				
			else if(searchn == 1) {
				pstmt = conn.prepareStatement(
						"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and itemid like '%"+search + "%' order by meo1.orderdate desc) meo2");
			}
				
			else if(searchn == 2) {
				pstmt = conn.prepareStatement(
						"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and m_id like '%"+search + "%' order by meo1.orderdate desc) meo2");
			}

			else {
				pstmt = conn.prepareStatement(
						"select count(*) from (select meo1.order_no, meo1.orderdate, meo1.rental_d1, meo1.rental_d2, meo1.order_amount, meo1.pay_total, "
								+ "meo1.order_state, meo1.parcel_num, meo1.itemid, meo1.m_name, meo1.e_name, e_id,m_id,item_no, rownum r from (select meo.order_no, meo.orderdate, meo.rental_d1, meo.rental_d2, "
								+ "meo.order_amount, meo.pay_total, meo.order_state, meo.parcel_num, i.itemid, meo.m_name, meo.e_name, meo.e_id, meo.m_id, item_no from item i join (select * from member natural "
								+ "join (select * from ENTERPRISE  natural join orderlist )) meo using(item_no) order by meo.orderdate desc) meo1 "
								+ "where e_id = ? and m_name like '%"+search + "%' order by meo1.orderdate desc) meo2");
			}
			
			pstmt.setString(1, eId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
				System.out.println(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ex) {
				}
		}
		return x;
	}
	
	public int getOrderlistDeliveringCount(String mId) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int x = 0;

	      try {
	         conn = getConnection();

	         pstmt = conn.prepareStatement("select count(*) from orderlist where m_id=? and order_state='delivering'");
	         pstmt.setString(1, mId);
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
	            }
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (Exception ex) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (Exception ex) {
	            }
	      }
	      return x;
	   }
	
	public List getOrderListent(String eId) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List orderlists = null;

	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement("select * from orderlist where e_id=?");
	         pstmt.setString(1, eId);
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            orderlists = new ArrayList();
	            do {
	               OrderlistDataBean orderlist = new OrderlistDataBean();
	               orderlist.setOrderNo(rs.getInt("order_No"));
	               orderlist.setOrderDate(rs.getTimestamp("orderDate"));
	               orderlist.setItemNo(rs.getString("item_No"));
	               orderlist.seteId(rs.getString("e_Id"));
	               orderlist.setPayTotal(rs.getInt("pay_total"));
	               orderlist.setRentalD1(rs.getDate("rental_D1"));
	               orderlist.setRentalD2(rs.getDate("rental_D2"));
	               orderlist.setOrderAmount(rs.getInt("order_Amount"));
	               orderlist.setOrderState(rs.getString("order_State"));
	               orderlist.setOrderMeg(rs.getString("order_meg"));
	               
	               orderlists.add(orderlist);
	            } while (rs.next());
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         if (rs != null)
	            try {
	               rs.close();
	            } catch (Exception ex) {
	            }
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (Exception ex) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (Exception ex) {
	            }
	      }

	      return orderlists;
	   }
	   public int getOrderCountent(String eId) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int x = 0;

	      try {
	         conn = getConnection();

	         pstmt = conn.prepareStatement("select count(*) from orderlist where e_id=?");
	         pstmt.setString(1, eId);
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
	            }
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (Exception ex) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (Exception ex) {
	            }
	      }
	      return x;
	   }
}
	
