package model;

public class UserPoster {
	
	private String userID;
	private String userPass;
	private String pfp;
	private String userEmail;
	private int isAdmin;
	
	public UserPoster(String userID, String userPass, String pfp, String userEmail, int isAdmin) {
		this.userID = userID;
		this.userPass = userPass;
		this.pfp = pfp;
		this.userEmail = userEmail;
		this.isAdmin = isAdmin;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getPfp() {
		return pfp;
	}
	public void setPfp(String pfp) {
		this.pfp = pfp;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
