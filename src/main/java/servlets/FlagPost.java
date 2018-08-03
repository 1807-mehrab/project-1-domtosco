package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ForumPostDAO;
import model.ForumPost;
import util.DAOUtility;

@WebServlet("/FlagPost")
public class FlagPost extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//Flag the post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int postID = Integer.parseInt(request.getParameter("postID"));
		
		if (postID != 0) {
			ForumPost frmPost = dao.getPost(postID);
			frmPost.setFlagged(1);
			dao.updatePost(frmPost);
		}
		
		request.getRequestDispatcher("ShowPosts").forward(request,  response);
	}
	
	//Unflag the post
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		int postID = Integer.parseInt(request.getParameter("postID"));
		
		if (postID != 0) {
			ForumPost frmPost = dao.getPost(postID);
			frmPost.setFlagged(1);
			dao.updatePost(frmPost);
		}
		
		request.getRequestDispatcher("ViewThread").forward(request, response);
	}
	
}
