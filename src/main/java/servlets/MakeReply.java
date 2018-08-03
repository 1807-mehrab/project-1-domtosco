package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ForumPostDAO;
import model.FrmThread;
import model.ForumPost;
import model.UserPoster;
import util.DAOUtility;

@WebServlet("/MakeReply")
public class MakeReply extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int threadID = Integer.parseInt(request.getParameter("threadCode"));
		String threadTopic = new String();
		
		if (threadID != 0) {
			FrmThread thready = dao.getThread(threadID);
			threadTopic = thready.getThreadTopic();
		}
		
		request.setAttribute("threadCode", threadID);
		request.setAttribute("threadTop", threadTopic);
		request.getRequestDispatcher("MakeReply.jsp").forward(request, response);;
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int threadID = Integer.parseInt(request.getParameter("threadCode"));
		FrmThread checkThread = null;
		
		String postMeat = request.getParameter("txtContent");
		UserPoster userName = (UserPoster) request.getSession().getAttribute("userPoster");
		String userID = userName.getUserID();
		
		int postID = (dao.getOpenSpot() + 1);
		
		if (threadID == 0) {
			threadID = postID;
			String threadTopic = request.getParameter("threadTop");
			checkThread = new FrmThread(threadID, userID, threadTopic);
			dao.addThread(checkThread);
		}else {
			checkThread = dao.getThread(threadID);
		}
		
		ForumPost newPost = new ForumPost(postID, threadID, userID, postMeat, "", 0, 0);
		dao.addPost(newPost);
		
		request.setAttribute("thready", checkThread);
		request.getRequestDispatcher("ViewThread").forward(request, response);;
		
	}
	
}
