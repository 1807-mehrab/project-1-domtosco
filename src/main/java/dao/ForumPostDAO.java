package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ForumPost;
import model.FrmThread;
import util.DAOUtility;

public class ForumPostDAO {
	
	Connection connection = null;
	PreparedStatement stmt = null;
	
	//Get a thread by threadID
	public FrmThread getThread(int threadID) {
		FrmThread frmNeedle = null;
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM Threads WHERE ThreadID=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, threadID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				frmNeedle = new FrmThread(threadID, rs.getString("userID"), rs.getString("threadTopic"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return frmNeedle;
	}
	
	//Get all threads
	public List<FrmThread> getAllThreads() {
		List<FrmThread> frmNeedles = new ArrayList<>();
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM Threads";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FrmThread fthread = new FrmThread(rs.getInt("threadID"), rs.getString("userID"), rs.getString("threadTopic"));
				frmNeedles.add(fthread);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return frmNeedles;
	}
	
	//User can view their posts
	public List<ForumPost> getUserPosts(String userID) {
		List<ForumPost> userPosts = new ArrayList<>();
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM ForumPosts WHERE UserID=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ForumPost fpost = new ForumPost(rs.getInt("postID"), rs.getInt("threadID"),
						userID, rs.getString("txtCnt"), rs.getString("imgCnt"),
						rs.getInt("edited"), rs.getInt("flagged"));
				userPosts.add(fpost);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return userPosts;
	}
	
	//Create a topic view
	public List<ForumPost> getThreadPosts(int threadID) {
		List<ForumPost> thrPosts = new ArrayList<>();
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM ForumPosts WHERE ThreadID=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, threadID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ForumPost fpost = new ForumPost(rs.getInt("postID"), threadID,
						rs.getString("userID"), rs.getString("txtCnt"), rs.getString("imgCnt"),
						rs.getInt("edited"), rs.getInt("flagged"));
				thrPosts.add(fpost);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return thrPosts;
	}
	
	//Admins can check flagged posts
	public List<ForumPost> getFlaggedPosts() {
		List<ForumPost> flgPosts = new ArrayList<>();
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM ForumPosts WHERE Flagged=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, "1");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ForumPost fpost = new ForumPost(rs.getInt("postID"), rs.getInt("threadID"),
						rs.getString("userID"), rs.getString("txtCnt"), rs.getString("imgCnt"),
						rs.getInt("edited"), rs.getInt("flagged"));
				flgPosts.add(fpost);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return flgPosts;
	}
	
	//Retrieve existing post
	public ForumPost getPost(int postID) {
		ForumPost frmPost = null;
		
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM ForumPosts WHERE PostID=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, postID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				frmPost = new ForumPost(postID, rs.getInt("threadID"),
						rs.getString("userID"), rs.getString("txtCnt"), rs.getString("imgCnt"),
						rs.getInt("edited"), rs.getInt("flagged"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return frmPost;
	}
	
	//Get a new postID
	public int getOpenSpot() {
		String newSpot = "0";
		try {
			connection = DAOUtility.getConnection();
			String sql = "SELECT * FROM ForumPosts";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				newSpot = rs.getString("postID");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return Integer.parseInt(newSpot);
	}
	
	//Create new thread
	public boolean addThread(FrmThread ft) {
		try {
			connection = DAOUtility.getConnection();
			String sql = "INSERT INTO Threads VALUES (?,?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, ft.getThreadID());
			stmt.setString(2, ft.getUserID());			
			stmt.setString(3, ft.getThreadTopic());

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
	}
	
	//Create new post
	public boolean addPost(ForumPost fp) {
		try {
			connection = DAOUtility.getConnection();
			String sql = "INSERT INTO ForumPosts VALUES (?,?, ?,?,?, ?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, fp.getPostID());
			stmt.setInt(2, fp.getThreadID());
			
			stmt.setString(3, fp.getUserID());
			stmt.setString(4, fp.getTxtCnt());
			stmt.setString(5, fp.getImgCnt());
			
			stmt.setInt(6, fp.getEdited());
			stmt.setInt(7, fp.getFlagged());
			
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
	}
	
	//Update post info (edit text, editor check (locks if an admin edited), flag/unflag)
	public boolean updatePost(ForumPost fp) {
		try {
			connection = DAOUtility.getConnection();
			String sql = "UPDATE ForumPosts SET txtCnt=?, edited=?, flagged=? WHERE PostID=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, fp.getTxtCnt());
			stmt.setInt(2, fp.getEdited());
			stmt.setInt(3, fp.getFlagged());
			stmt.setInt(4, fp.getPostID());
			
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
	}
	
	//Delete post
	public boolean deletePost(int postID) {
		try {
			connection = DAOUtility.getConnection();
			String sql = "DELETE ForumPosts WHERE PostID=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, postID);

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
	}
	
	//public void deleteChildren?
	
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
