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
			System.out.println("DB ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static void main(String[] args) {
		makeCon();

	}

}
