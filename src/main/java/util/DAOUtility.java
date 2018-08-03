package util;

import java.sql.*;
import dao.ForumPostDAO;
import dao.UserPosterDAO;

public class DAOUtility {
	
	private static final String uName = "project1";
	private static final String uPass = "project1";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static Connection myConnection = null;
	
	public static synchronized Connection getConnection() throws SQLException {
		if (myConnection == null) {
			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch(SQLException e) {
				System.out.println("Driver not found");
				e.printStackTrace();
			}
			myConnection = DriverManager.getConnection(URL, uName, uPass);
		}
		if (myConnection.isClosed()) {
			//System.out.println("Establishing new connection...");
			myConnection = DriverManager.getConnection(URL, uName, uPass);
		}
		return myConnection;
	}
	
	public static ForumPostDAO getFPostDAO() {
		return new ForumPostDAO();
	}
	
	public static UserPosterDAO getUPosterDAO() {
		return new UserPosterDAO();
	}
}
