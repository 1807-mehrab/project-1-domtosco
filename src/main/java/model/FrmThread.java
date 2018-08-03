package model;

public class FrmThread {
	
	private int threadID;
	private String userID;
	private String threadTopic;
	
	public FrmThread(int threadID, String userID, String threadTopic) {
		this.threadID = threadID;
		this.userID = userID;
		this.threadTopic = threadTopic;
	}
	
	public int getThreadID() {
		return threadID;
	}
	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getThreadTopic() {
		return threadTopic;
	}
	public void setThreadTopic(String threadTopic) {
		this.threadTopic = threadTopic;
	}
}
