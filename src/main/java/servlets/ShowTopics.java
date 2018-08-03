package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ForumPostDAO;
import model.FrmThread;
import util.DAOUtility;

@WebServlet("/ShowTopics")
public class ShowTopics extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ForumPostDAO dao = DAOUtility.getFPostDAO();
		List<FrmThread> frmNeedles = dao.getAllThreads();
		
		request.setAttribute("thrTopics", frmNeedles);
		
		request.getRequestDispatcher("threadList.jsp").forward(request, response);
	}

}
