package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
	// 필드
		static String driverClass = "oracle.jdbc.OracleDriver";
		/* 자르에넣은 경로를 뜻함 패키지안에 가보면 해당드라이버가있음 */
		static String url = "jdbc:oracle:thin:@localhost:1521:xe";
		static String username = "c##mydb";
		static String password = "mydb";
		/*
		 * "jdbc:oracle:thin:@localhost:1521:XE" 의 의미
		 * 
		 * jdbc - jdbc 라이브러리
		 * 
		 * oracle - 오라클로 연결
		 * 
		 * thin - 자바로 연결 (반대는 OCI - OS로 작동)
		 * 
		 * @localhost - 내 내부 아이피로 접속
		 * 
		 * 1521 - 포트번호
		 * 
		 * XE - 오라클 버전
		 */

		public static void main(String[] args) {
			Connection conn = null;
			try {
				Class.forName(driverClass);
				System.out.println("Orcle 드라이버 로딩");
				conn = DriverManager.getConnection(url,username,password);
				System.out.println("Connection 객체 생성 " + conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

}
