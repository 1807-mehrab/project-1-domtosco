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
import util.DAOUtility;

@WebServlet("/ViewThread")
public class ViewThread extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		List<ForumPost> posties = new ArrayList<ForumPost>();
		
		int threadID = Integer.parseInt(request.getParameter("threadID"));
		
		if (threadID != 0) {
			posties = dao.getThreadPosts(threadID);
		}
		
		FrmThread thready = dao.getThread(threadID);
		
		request.setAttribute("postSet", posties);
		request.setAttribute("frmThread", thready);
		request.getRequestDispatcher("threadRead.jsp").forward(request, response);;
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		List<ForumPost> posties = new ArrayList<ForumPost>();
		
		FrmThread checkThread = (FrmThread) request.getAttribute("thready");
		int threadID = checkThread.getThreadID();
		
		if (threadID != 0) {
			posties = dao.getThreadPosts(threadID);
		}
		
		FrmThread thready = dao.getThread(threadID);
		
		request.setAttribute("postSet", posties);
		request.setAttribute("frmThread", thready);
		request.getRequestDispatcher("threadRead.jsp").forward(request, response);;
		
	}

}
