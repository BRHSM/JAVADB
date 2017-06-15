package DataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Connector {
	private String username;
	private String password;
	
	public Connector(String username, String password) {
		this.username=username;
		this.password=password;
	}

	//raspberry IP: 192.168.1.200 DB: MySystemDB
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/mydatabase?autoReconnect=true&useSSL=false";
		Class.forName(driver);
		
		Connection con = (Connection) DriverManager.getConnection(url,username,password);
		return con;
	}
}
