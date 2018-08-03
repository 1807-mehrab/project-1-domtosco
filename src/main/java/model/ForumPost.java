package model;

public class ForumPost {
	
	private int postID;
	private int threadID;
	
	private String userID;
	private String txtCnt;
	private String imgCnt;
	
	private int edited;
	private int flagged;
	
	public ForumPost(int postID, int threadID,
		String userID, String txtCnt, String imgCnt,
		int edited, int flagged) {
			this.postID = postID;
			this.threadID = threadID;
			
			this.userID = userID;
			this.txtCnt = txtCnt;
			this.imgCnt = imgCnt;
			
			this.edited = edited;
			this.flagged = flagged;
	}
	
	//Get unchangeable fields for post layout
	public int getPostID() {
		return postID;
	}
	public int getThreadID() {
		return threadID;
	}
	
	//Get/Set post content
	public String getUserID() {
		return userID;
	}
	/*public void setUserID(String userID) {
		this.userID = userID;
	}*/

	public String getTxtCnt() {
		return txtCnt;
	}
	public void setTxtCnt(String txtCnt) {
		this.txtCnt = txtCnt;
	}
	public String getImgCnt() {
		return imgCnt;
	}
	public void setImgCnt(String imgCnt) {
		this.imgCnt = imgCnt;
	}
	
	//Get/Set secondary info
	public int getEdited() {
		return edited;
	}
	public void setEdited(int edited) {
		this.edited = edited;
	}
	public int getFlagged() {
		return flagged;
	}
	public void setFlagged(int flagged) {
		this.flagged = flagged;
	}
	
}
