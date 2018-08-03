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

@WebServlet("/DeleteReply")
public class DeleteReply extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int postID = Integer.parseInt(request.getParameter("postID"));
		ForumPost editPost = null;
		FrmThread checkThread = null;
		
		if (postID != 0) {
			editPost = dao.getPost(postID);
			checkThread = dao.getThread(editPost.getThreadID());
			dao.deletePost(postID);
		}
		
		request.setAttribute("thready", checkThread);
		request.getRequestDispatcher("ViewThread").forward(request, response);;
		
	}
	
}