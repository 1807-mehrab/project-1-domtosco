package servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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

@WebServlet("/ShowPosts")
public class ShowPosts extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		List<ForumPost> posties = new ArrayList<ForumPost>();
		List<FrmThread> threadies = new ArrayList<FrmThread>();
		
		String getType = request.getParameter("flagged");
		String pageHead = new String();
		
		if ((getType == null) || (getType.equals("false"))){
			getType = "false";
			String userID = request.getParameter("userName");
			if (userID.equals("none")) {
				UserPoster urpr = (UserPoster) request.getSession().getAttribute("userPoster");
				posties = dao.getUserPosts(urpr.getUserID());
				pageHead = "Posts from " + urpr.getUserID();
			} else {
				posties = dao.getUserPosts(userID);
				pageHead = "Posts from " + userID;
			}
		}else if (getType.equals("true")) {
			posties = dao.getFlaggedPosts();
			pageHead = "Flagged Posts";
		}
		
		for (ForumPost posty : posties) {
			FrmThread thready = dao.getThread(posty.getThreadID());
			threadies.add(thready);
		}
		
		request.setAttribute("postSet", posties);
		request.setAttribute("threadSet", threadies);
		request.setAttribute("pageHead", pageHead);
		request.setAttribute("flagged", getType);
		request.getRequestDispatcher("postList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
