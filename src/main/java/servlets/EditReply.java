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
import util.DAOUtility;

@WebServlet("/EditReply")
public class EditReply extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int postID = Integer.parseInt(request.getParameter("postID"));
		ForumPost editPost = null;
		String threadTopic = new String();
		
		if (postID != 0) {
			editPost = dao.getPost(postID);
		}
		
		request.setAttribute("editPost", editPost);
		request.setAttribute("threadTop", threadTopic);
		request.getRequestDispatcher("EditReply.jsp").forward(request, response);;
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int postID = Integer.parseInt(request.getParameter("postID"));
		ForumPost editPost = null;
		
		if (postID != 0) {
			editPost = dao.getPost(postID);
			editPost.setTxtCnt(request.getParameter("txtContent"));
			editPost.setEdited(1);
			dao.updatePost(editPost);
		}
		
		FrmThread checkThread = dao.getThread(editPost.getThreadID());
		
		request.setAttribute("thready", checkThread);
		request.getRequestDispatcher("ViewThread").forward(request, response);;
		
	}
	
}