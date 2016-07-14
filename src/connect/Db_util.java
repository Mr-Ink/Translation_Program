package connect;

import java.sql.Connection;
import java.sql.DriverManager;


public class Db_util {
	private String URL = "jdbc:mysql://localhost:3306/dictionary";
	private String USER = "root";
	private String PASSWORD = "root";
	private String jdbcName = "com.mysql.jdbc.Driver";
	
	public Connection getcon() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
		return con;
	}
	
}
