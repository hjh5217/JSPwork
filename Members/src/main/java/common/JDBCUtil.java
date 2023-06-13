package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	
	static String driverClass = "oracle.jdbc.OracleDriver";
	/* 자르에넣은 경로를 뜻함 패키지안에 가보면 해당드라이버가있음 */
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String username = "c##mydb";
	static String password = "mydb";
	
	// DB연결 메서드
	public static Connection getConnection() {
		try {
			Class.forName(driverClass);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}// getconn
	
	// DB 연결 종료 메서드
	public static void close(Connection conn,PreparedStatement psmt) {
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				psmt = null;
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
	}// close
	
	
	// DB 연결 종료 메서드 rs 있는버전
	public static void close(Connection conn,PreparedStatement psmt, ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				rs = null;
			}
		}
		
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				psmt = null;
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}

	}// close
	

	
}
