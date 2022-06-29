package bean.item;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import bean.item.ItemDataBean;
import jdbc.JdbcUtil;
import bean.member.MemberDataBean;
import bean.member.ZipcodeBean;

public class ItemDBBean {// DB와 관련된 일을 하는 클래스: DBBean, DAO

	private static ItemDBBean instance = new ItemDBBean();

	public static ItemDBBean getInstance() {
		return instance;
	}

	private ItemDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// ItemAdminlist.jsp : 페이징을 위해서 전체 DB에 입력된 행의수가 필요하다...!!!
	public int getAdminItemCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from item");
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

	// ItemAdminlist.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
	public List getAdminItems(int start, int end, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List itemLists = null;
		
		try {
			conn = getConnection();
			if(search != null) {
				pstmt = conn.prepareStatement(
				"select item_no, itemId, price, amount, item_state, e_id, rownum r "
				+"from (select item_no, itemId, price, amount, item_state, e_id, rownum r "
				+"from (select * from item where itemId like '%"+search+"%' order by item_no) order by item_no) where r >= ? and r <= ?");
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
			
			} else if(search == null) {
				pstmt = conn.prepareStatement(
				"select item_no, itemId, price, amount, item_state, e_id, rownum r "
				+ "from (select item_no, itemId, price, amount, item_state, e_id, rownum r "
				+ "from (select * from item order by item_no) order by item_no) where r >= ? and r <= ?");
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
			}
			
			if (rs.next()) {
				itemLists = new ArrayList();
				do {
					ItemDataBean article = new ItemDataBean();
					article.setItemNo(rs.getString("item_No"));
					article.setItemId(rs.getString("itemId"));
					article.setPrice(rs.getInt("price"));
					article.setAmount(rs.getInt("amount"));
					article.setItemstate(rs.getString("item_state"));
					article.seteId(rs.getString("e_id"));
					

					itemLists.add(article);
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
		return itemLists;
	}

	//ItemEnterpriseList :: 아이템개수
	public int getEntArticleCount(String eId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from item where e_Id = ?");
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
	
	
	//itemEnterPriseList :: 아이템 리스트
	public List getEntArticles(int start, int end, String eId, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List itemLists = null;
		
		try {
			conn = getConnection();
			if(search != null) {
				pstmt = conn.prepareStatement(
				"select item_no, itemId, category_no, price, amount, item_state, rownum r "
				+"from (select item_no, itemId, category_no, price, amount, item_state, rownum r "
				+"from (select * from item where itemId like '%"+search+"%' and e_id = ? order by item_no desc) order by item_no desc) where r >= ? and r <= ?");
				
				pstmt.setString(1, eId);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
			
			} else if(search == null) {
				pstmt = conn.prepareStatement(
				"select item_no, itemId, category_no, price, amount, item_state, rownum r "
				+ "from (select item_no, itemId, category_no, price, amount, item_state, rownum r "
				+ "from (select * from item where e_id = ? order by item_no desc) order by item_no desc) where r >= ? and r <= ?");
				pstmt.setString(1, eId);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
			}
			
			if (rs.next()) {
				itemLists = new ArrayList();
				do {
					ItemDataBean article = new ItemDataBean();
					article.setItemNo(rs.getString("item_No"));
					article.setItemId(rs.getString("itemId"));
					article.setCategoryNo(rs.getInt("category_No"));
					article.setPrice(rs.getInt("price"));
					article.setAmount(rs.getInt("amount"));
					article.setItemstate(rs.getString("item_state"));
					

					itemLists.add(article);
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
		return itemLists;
	}

	// iteminsertPro.jsp
	public void iteminsert(ItemDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
			pstmt = conn.prepareStatement(
					"insert into item(item_no, itemid, price, amount, item_state, rental_p, thumbnail, detail1, detail2, detail3, r_zipcode, r_address, e_id, t_realpath, d1_realpath, d2_realpath, d3_realpath, category_no) "
							+ "values(sequence_item.nextVal,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setString(1, article.getItemId());
			pstmt.setInt(2, article.getPrice());
			pstmt.setInt(3, article.getAmount());
			pstmt.setString(4, article.getItemstate());
			pstmt.setInt(5, article.getRentalP());
			pstmt.setString(6, article.getThumbnail());
			pstmt.setString(7, article.getDetail1());
			pstmt.setString(8, article.getDetail2());
			pstmt.setString(9, article.getDetail3());
			pstmt.setString(10, article.getRzipcode());
			pstmt.setString(11, article.getRaddress());
			pstmt.setString(12, article.geteId());
			pstmt.setString(13, article.gettRealpath());
			pstmt.setString(14, article.getD1Realpath());
			pstmt.setString(15, article.getD2Realpath());
			pstmt.setString(16, article.getD3Realpath());
			pstmt.setInt(17, article.getCategoryNo());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}

	// itemUpdateForm.jsp 수정폼
	public ItemDataBean getItem(int itemNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemDataBean item = null;
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from ITEM where item_No = ?");
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				item = new ItemDataBean();
				item.setItemId(rs.getString("itemId"));
				item.setPrice(rs.getInt("price"));
				item.setAmount(rs.getInt("amount"));
				item.setItemstate(rs.getString("item_state"));
				item.setRentalP(rs.getInt("rental_P"));
				item.setThumbnail(rs.getString("thumbnail"));
				item.settRealpath(rs.getString("t_realpath"));
				item.setDetail1(rs.getString("detail1"));
				item.setDetail2(rs.getString("detail2"));
				item.setDetail3(rs.getString("detail3"));
				item.setRzipcode(rs.getString("r_zipcode"));
				item.setRaddress(rs.getString("r_address"));
				item.settRealpath(rs.getString("t_Realpath"));
				item.setD1Realpath(rs.getString("D1_Realpath"));
				item.setD2Realpath(rs.getString("D2_Realpath"));
				item.setD3Realpath(rs.getString("D3_Realpath"));
				item.setCategoryNo(rs.getInt("Category_No"));
				item.seteId(rs.getString("e_id"));
				item.setItemNo(rs.getString("item_no"));
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
		return item;
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
						"select count(*) from item where item_no like '%" + search + "%' or item_id '%"  + search + "%' or e_id'%" + search + "%'");
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
		public List getArticlesSearch(String search) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List itemLists = null;
			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select * from item where item_no like '%" + search + "%' or itemid like '%"  + search + "%' or e_id like '%" + search + "%'");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					itemLists = new ArrayList();
					do {
						ItemDataBean article = new ItemDataBean();
						article.setItemNo(rs.getString("item_No"));
						article.setItemId(rs.getString("itemId"));
						article.setPrice(rs.getInt("price"));
						article.setAmount(rs.getInt("amount"));
						article.setItemstate(rs.getString("item_state"));
						article.setRentalP(rs.getInt("rental_P"));
						article.setThumbnail(rs.getString("thumbnail"));
						article.setDetail1(rs.getString("detail1"));
						article.setDetail2(rs.getString("detail2"));
						article.setDetail3(rs.getString("detail3"));
						article.setRzipcode(rs.getString("r_zipcode"));
						article.setRaddress(rs.getString("r_address"));
						article.seteId(rs.getString("e_id"));

						itemLists.add(article);
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
			return itemLists;
		}

		
	
	
	/*
	 * // itemUpdatePro.jsp public void itemUpdatePro(ItemDataBean item) throws
	 * Exception { Connection conn = null; PreparedStatement pstmt = null;
	 * 
	 * try { conn = getConnection();
	 * 
	 * pstmt = conn.prepareStatement(
	 * "update ITEM set itemId=?,price=?,amount=?,item_state=?,rental_P=?,thumbnail=?,detail1=?,detail2=?,detail3=?,r_zipcode=?,r_address=? where item_No=?"
	 * ); pstmt.setString(1, item.getItemId()); pstmt.setInt(2, item.getPrice());
	 * pstmt.setInt(3, item.getAmount()); pstmt.setString(4, item.getItemstate());
	 * pstmt.setInt(5, item.getRentalP()); pstmt.setString(6, item.getThumbnail());
	 * pstmt.setString(7, item.getDetail1()); pstmt.setString(8, item.getDetail2());
	 * pstmt.setString(9, item.getDetail3()); pstmt.setString(10,
	 * item.getRzipcode()); pstmt.setString(11, item.getRaddress());
	 * 
	 * pstmt.executeUpdate(); } catch (Exception ex) { ex.printStackTrace(); }
	 * finally { if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) {
	 * } if (conn != null) try { conn.close(); } catch (SQLException ex) { } } }
	 * 
	 * // itemDelete public int itemDelete(String item_No) throws Exception {
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * int x = -1;
	 * 
	 * try { conn = getConnection(); pstmt =
	 * conn.prepareStatement("select * from ITEM where item_No = ?");
	 * pstmt.setString(1, item_No); rs = pstmt.executeQuery(); if (rs.next()) {
	 * pstmt = conn.prepareStatement("delete from ITEM where item_No = ?"); // !!!!!
	 * pstmt.setString(1, item_No); // rs = pstmt.executeUpdate(); } } catch
	 * (Exception ex) { ex.printStackTrace(); } finally { if (rs != null) try {
	 * rs.close(); } catch (SQLException ex) { } if (pstmt != null) try {
	 * pstmt.close(); } catch (SQLException ex) { } if (conn != null) try {
	 * conn.close(); } catch (SQLException ex) { } } return x; }
	 */

	public Vector zipcodeRead(String area4) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector vecList = new Vector();

		try {
			con = getConnection();
			String strQuery = "select * from zipcode where area4 like '" + area4 + "%'";
			pstmt = con.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ZipcodeBean tempZipcode = new ZipcodeBean();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setArea1(rs.getString("area1"));
				tempZipcode.setArea2(rs.getString("area2"));
				tempZipcode.setArea3(rs.getString("area3"));
				tempZipcode.setArea4(rs.getString("area4"));
				vecList.addElement(tempZipcode);
			}

		} catch (Exception ex) {
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
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return vecList;
	}
	
	// itemUpdateProAction
	public void itemUpdate(ItemDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"update item set itemid = ?, price = ?, amount = ?, item_state = ?, rental_p = ?, thumbnail = ?, detail1 = ?, detail2 = ?, detail3 = ?, r_zipcode = ?, r_address = ?, t_realpath = ?, d1_realpath = ?, d2_realpath = ?, d3_realpath = ?, category_no = ? where item_no = ?");

			
			pstmt.setString(1, article.getItemId());
			pstmt.setInt(2, article.getPrice());
			pstmt.setInt(3, article.getAmount());
			pstmt.setString(4, article.getItemstate());
			pstmt.setInt(5, article.getRentalP());
			pstmt.setString(6, article.getThumbnail());
			
			pstmt.setString(7, article.getDetail1());
			pstmt.setString(8, article.getDetail2());
			pstmt.setString(9, article.getDetail3());
			pstmt.setString(10, article.getRzipcode());
			pstmt.setString(11, article.getRaddress());
			pstmt.setString(12, article.gettRealpath());
			pstmt.setString(13, article.getD1Realpath());
			pstmt.setString(14, article.getD2Realpath());
			pstmt.setString(15, article.getD3Realpath());
			pstmt.setInt(16, article.getCategoryNo());
			pstmt.setString(17, article.getItemNo());

			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}

	// ItemEnterpriseDelete
	public void entItemDelete(int itemNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("delete from item where item_no = ?");
			pstmt.setInt(1, itemNo);
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
	
	// ItemAdminDelete
	public void adminItemDelete(int itemNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("delete from item where item_no = ?");
			pstmt.setInt(1, itemNo);
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

	public List getArticles() throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List itemLists = null;
	      try {
	         conn = getConnection();

	         pstmt = conn.prepareStatement("select * from Item where item_state='yes' order by item_no desc");
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            itemLists = new ArrayList();
	            do {
	               ItemDataBean article = new ItemDataBean();
	               article.setItemNo(rs.getString("item_No"));
	               article.setItemId(rs.getString("itemId"));
	               article.setPrice(rs.getInt("price"));
	               article.settRealpath(rs.getString("t_realpath"));
	               itemLists.add(article);
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
	      return itemLists;
	   }
	public List getArticlesBest() throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List itemLists = null;
	      try {
	         conn = getConnection();

	         pstmt = conn.prepareStatement("select *  from item i, (select item_no,count(item_no) from orderlist group by item_no order by count(item_no) desc) r where i.item_no=r.item_no  and item_state='yes'");
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            itemLists = new ArrayList();
	            do {
	               ItemDataBean article = new ItemDataBean();
	               article.setItemNo(rs.getString("item_No"));
	               article.setItemId(rs.getString("itemId"));
	               article.setPrice(rs.getInt("price"));
	               article.settRealpath(rs.getString("t_realpath"));
	            
	               itemLists.add(article);
	            
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
	      return itemLists;
	   }

		
		// list.jsp : 페이징을 위해서 전체 DB에 입력된 행의수가 필요하다...!!!
		public int getArticleCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;

			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select count(*) from item");
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
	
	
	
		// Itemlist.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
		public List  getItemList(int start, int end, int cNo) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List itemLists = null;
			
			try {
				conn = getConnection();
				if(cNo == 0) { // 전체리스트
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end); 
					rs = pstmt.executeQuery();
				} else if(cNo == 100) { // 유아용품
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 1 or category_no = 2 or category_no = 3 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 200) { // 스포츠
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 4 or category_no = 5 or category_no = 6 or category_no = 7 or category_no = 8 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 300) { // 음향기기
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 9 or category_no = 10 or category_no = 11 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 400) { // 디지털가전
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 12 or category_no = 13 or category_no = 14 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 500) { // 공간대여
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 15 or category_no = 16 or category_no = 17 or category_no = 18 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 1) { // 장난감 :: 1
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 1 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 2) { // 책 :: 2
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 2 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 3) { // 모빌리티 :: 3
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 3 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 4) { // 캠핑 :: 4
	 				pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 4 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 5) { // 레저 :: 5
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 5 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				
				} else if(cNo == 6) { // 등산 :: 6
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 6 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery(); 
				
				} else if(cNo == 7) { // 헬스 :: 7
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 7 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 8) { // 탈것 :: 8
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 8 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 9) { // 마이크 :: 9
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 9 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 10) { // 스피커 :: 10
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 10 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 11) { // 우퍼 :: 11
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 11 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 12) { // 카메라 :: 12
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 12 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 13) { // 노트북 :: 13
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 13 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 14) { // 빔프로젝터 :: 14
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 14 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 15) { // 이벤트홀 :: 15
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 15 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 16) { // 파티룸 :: 16
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 16 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 17) { // 공연/강연 :: 17
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 17 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				} else if(cNo == 18) { // 스튜디오 :: 18
					pstmt = conn.prepareStatement("select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
							+ "from (select * from item where category_no = 18 order by item_no) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
				}
				
				if (rs.next()) {
					itemLists = new ArrayList();
					 
					do {	
						ItemDataBean article = new ItemDataBean();
						article.setItemNo(rs.getString("item_No"));
						article.setItemId(rs.getString("itemId"));
						article.setPrice(rs.getInt("price"));
						article.settRealpath(rs.getString("t_realpath"));
						article.seteId(rs.getString("e_id"));   

						itemLists.add(article);
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
			return itemLists; 
		}
		
		
		// 해당카테고리 상품개수
		public int getCatCount(int cNo) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;

			try {
				conn = getConnection();

				if(cNo == 100) { //유아용품
					pstmt = conn.prepareStatement("select count(*) from item where category_no = 1 or category_no = 2 or category_no = 3");
					rs = pstmt.executeQuery();
					
				} else if(cNo == 200) { // 스포츠레저
					pstmt = conn.prepareStatement("select count(*) from item where category_no = 4 or category_no = 5 or category_no = 6 or category_no = 7 or category_no = 8");
					rs = pstmt.executeQuery();
				} else if(cNo == 300) { // 음향기기
					pstmt = conn.prepareStatement("select count(*) from item where category_no = 9 or category_no = 10 or category_no = 11");
					rs = pstmt.executeQuery();
				} else if(cNo == 400) { // 디지털가전
					pstmt = conn.prepareStatement("select count(*) from item where category_no = 12 or category_no = 13 or category_no = 14");
					rs = pstmt.executeQuery();
				} else if(cNo == 500) { //공간대여
					pstmt = conn.prepareStatement("select count(*) from item where category_no = 15 or category_no = 16 or category_no = 17 or category_no = 18");
					rs = pstmt.executeQuery();
				} else {
					pstmt = conn.prepareStatement("select count(*) from item where category_no = ?");
					pstmt.setInt(1, cNo);
					rs = pstmt.executeQuery();
				}

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

		
		// itemdetail.jsp
		public ItemDataBean getItemlist(int itemNo) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ItemDataBean article = null;
			String address = "";
			String address1 = "";
			String address2 = "";
			String raddress = "";
			
			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select * from Item where item_no = ?");
				pstmt.setInt(1, itemNo);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					article = new ItemDataBean();
					article.setItemNo(rs.getString("item_No"));
					article.setItemId(rs.getString("itemId"));
					article.setPrice(rs.getInt("price"));
					article.setAmount(rs.getInt("amount"));
					article.setItemstate(rs.getString("item_state"));
					article.setRentalP(rs.getInt("rental_P"));
					article.setThumbnail(rs.getString("t_realpath"));
					article.setDetail1(rs.getString("d1_realpath"));
					article.setDetail2(rs.getString("d2_realpath"));
					article.setDetail3(rs.getString("d3_realpath"));
					article.setRzipcode(rs.getString("r_zipcode"));
					address = rs.getString("r_address");
					int idx = address.indexOf("/");
					address1 = address.substring(0, idx);
					address2 = address.substring(idx+1);
					raddress = address1 + address2;
					article.setRaddress(raddress);
					article.seteId(rs.getString("e_id"));
					
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
			return article;
		}

		
		// headersearch
		public int getHeaderCount(String search) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			try {
				conn = getConnection();
					pstmt = conn.prepareStatement("select count(*) from item where itemid like '%"+search+"%'");
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

		// Itemlist.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
			public List  getHeaderItemList(int start, int end, String search) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List itemLists = null;
					
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(
					"select item_no, itemid, price, t_realpath, e_id, rownum r "
					+ "from (select item_no, itemid, price, t_realpath, e_id, rownum r "
					+ "from (select * from item where itemid like '%"+search+"%' order by item_no ) order by item_no ) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
					 
					if (rs.next()) {
						itemLists = new ArrayList();
							 
						do {	
							ItemDataBean article = new ItemDataBean();
							article.setItemNo(rs.getString("item_No"));
							article.setItemId(rs.getString("itemId"));
							article.setPrice(rs.getInt("price"));
							article.settRealpath(rs.getString("t_realpath"));
							article.seteId(rs.getString("e_id"));   

							itemLists.add(article);
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
				return itemLists; 
				}
			
			public int getAdminItemSearchCount(String search) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int x = 0;

				try {
					conn = getConnection();

					pstmt = conn.prepareStatement("select count(*) from item where itemid like '%"+search+"%'");
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
			

			//ItemEnterpriseList :: 아이템개수
					public int getEntArticleSearchCount(String eId, String search) throws Exception {
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						int x = 0;

						try {
							conn = getConnection();

							pstmt = conn.prepareStatement("select count(*) from item where e_Id = ? and itemid like '%"+search+"%'");
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


}
