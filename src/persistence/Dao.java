package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	protected static final String URL = "jdbc:mysql://localhost/computer-database-db";
	protected static final String LOGIN = "admincdb";
	protected static final String PASSWORD = "qwerty1234";
	protected static Connection cn;
	protected static Statement st;
	
	private Dao() {
		try {
		cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		st = cn.createStatement();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
