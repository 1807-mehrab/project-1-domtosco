package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserPosterDAO;
import model.UserPoster;
import util.DAOUtility;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**UserPosterDAO dao = DAOUtility.getUPosterDAO();
		
		String userCheck = request.getParameter("userID");
		String passCheck = request.getParameter("userPass");
		
		UserPoster uPoster = dao.getUser(userCheck);
		
		if (uPoster == null) {
			uPoster = new UserPoster(userCheck, passCheck, null, null, 0);
			dao.makeUser(uPoster);
			request.getSession().setAttribute("userPoster", uPoster);
			request.getRequestDispatcher("profileInfo.jsp").forward(request, response);
		}*/
		request.getRequestDispatcher("profileInfo.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserPosterDAO dao = DAOUtility.getUPosterDAO();
		UserPoster uPoster = null;
		
		String userCheck = request.getParameter("userID");
		String passCheck = request.getParameter("userPass");
		
		if ((userCheck != null) && (userCheck instanceof String) && (userCheck.length() > 0)) {
			UserPoster posterCheck = dao.getUser(userCheck);
			if (passCheck.equals(posterCheck.getUserPass())) {
				uPoster = posterCheck;
			}
		}
		if (uPoster != null) {
			request.getSession().setAttribute("userPoster", uPoster);
			request.getRequestDispatcher("profileInfo.jsp").forward(request, response);
		}
	}
	
}
