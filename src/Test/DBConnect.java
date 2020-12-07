package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	
	public static Connection makeCon() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@10.120.73.173:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, "BOSO", "12345");
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾지 못함");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static void main(String[] args) {
		makeCon();

	}

}
