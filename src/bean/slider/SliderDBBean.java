package bean.slider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class SliderDBBean {

	private static SliderDBBean instance = new SliderDBBean();

	public static SliderDBBean getInstance() {
		return instance;
	}

	private SliderDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void sliderInsert(SliderDataBean slider) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into main_img values(?,?,?,?,?)");
			pstmt.setString(1, slider.getIntro1());
			pstmt.setString(2, slider.getIntro2());
			pstmt.setString(3, slider.getIntro3());
			pstmt.setString(4, slider.getIntro4());
			pstmt.setString(5, slider.getIntro5());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	public void sliderUpdate(SliderDataBean slider) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update main_img set intro1=?, intro2=?, intro3=?, intro4=?, intro5=?");
			pstmt.setString(1, slider.getIntro1());
			pstmt.setString(2, slider.getIntro2());
			pstmt.setString(3, slider.getIntro3());
			pstmt.setString(4, slider.getIntro4());
			pstmt.setString(5, slider.getIntro5());
			System.out.println("업데이트 성공");
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	public SliderDataBean sliderSelect() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SliderDataBean slider = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from main_img");
			rs =pstmt.executeQuery();
			if(rs.next()) {
				slider = new SliderDataBean();
				slider.setIntro1(rs.getString("intro1"));
				slider.setIntro2(rs.getString("intro2"));
				slider.setIntro3(rs.getString("intro3"));
				slider.setIntro4(rs.getString("intro4"));
				slider.setIntro5(rs.getString("intro5"));
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
			
		}
		return slider;
	}

}
