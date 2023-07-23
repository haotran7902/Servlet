package context;

import java.sql.*;

public class DBContext {
	public static Connection getConnection() {
		Connection conn=null;
		String url="jdbc:mysql://localhost:3306/tor";
		String name="root";
		String pass="12345";
		
		//load driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//create connection
		try {
			conn=DriverManager.getConnection(url, name, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
				
	}
}
