package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserPoster;
import util.DAOUtility;

public class UserPosterDAO {
	
	Connection connection = null;
	PreparedStatement stmt = null;
	
	//Retrieve existing user
	public UserPoster getUser(String userID) {
		UserPoster urpr = null;
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM UserPosters WHERE UserID=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, userID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				urpr = new UserPoster(rs.getString("userID"), rs.getString("userPass"), rs.getString("pfp"), rs.getString("userEmail"), rs.getInt("isAdmin"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return urpr;
	}
	
	//Retrieve all users
	public List<UserPoster> getAllUsers() {
		List<UserPoster> urprs = new ArrayList<>();
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM UserPosters";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UserPoster urpr = new UserPoster(rs.getString("userID"), rs.getString("userPass"), rs.getString("pfp"), rs.getString("userEmail"), rs.getInt("isAdmin"));
				urprs.add(urpr);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return urprs;
	}
	
	//Update user pass and email
	public boolean updateUser(UserPoster urpr) {
		UserPoster oldUser = getUser(urpr.getUserID());
		if (oldUser != null) {
			try {
				connection = DAOUtility.getConnection();
				String sql = "UPDATE UserPosters SET userPass=?, userEmail=? WHERE userID=?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, urpr.getUserPass());
				stmt.setString(2, urpr.getUserEmail());
				stmt.setString(3, urpr.getUserID());
				
				if (stmt.executeUpdate() != 0)
					return true;
				else
					return false;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}finally {
				closeResources();
			}
		}else {
			return false;
		}
	}
	
	//Create new user
	public boolean makeUser(UserPoster urpr) {
		if (getUser(urpr.getUserID()) == null) {
			try {
				connection = DAOUtility.getConnection();
				String sql = "INSERT INTO UserPosters VALUES (?,?,?,?,?)";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, urpr.getUserID());
				stmt.setString(2, urpr.getUserPass());
				stmt.setString(3, urpr.getPfp());
				stmt.setString(4, urpr.getUserEmail());
				stmt.setInt(5, urpr.getIsAdmin());
				
				if (stmt.executeUpdate() != 0)
					return true;
				else
					return false;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}finally {
				closeResources();
			}
		}else {
			return false;
		}
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
}
