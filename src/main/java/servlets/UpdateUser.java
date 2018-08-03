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

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserPosterDAO dao = DAOUtility.getUPosterDAO();
		
		String userCheck = request.getParameter("userID");
		String passCheck = request.getParameter("userPass");
		String emailCheck = request.getParameter("userEmail");
		
		UserPoster uPoster = dao.getUser(userCheck);
		
		if (uPoster != null) {
			uPoster.setUserEmail(emailCheck);
			uPoster.setUserPass(passCheck);
			dao.updateUser(uPoster);
			
			request.getSession().setAttribute("userPoster", uPoster);
			request.getRequestDispatcher("profileInfo.jsp").forward(request, response);
		}
	}
	
}
