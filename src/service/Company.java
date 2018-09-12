package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Company {
	public static final String URL = "jdbc:mysql://localhost/computer-database-db";
	public static final String LOGIN = "admincdb";
	public static final String PASSWORD = "qwerty1234";
	public static final Scanner READER = new Scanner(System.in);
	public static Connection cn = null;
	public static Statement st = null;
	

	public static String showCompanies(){
		try {
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String sql = "SELECT * FROM company";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("***Liste des entreprises***\n");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getString(2));
			}
			rs.close();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
