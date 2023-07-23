package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Account;

@WebServlet("/signup")
public class SignUpControl extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		
		if(pass.equals(repass)) {
			DAO dao = new DAO();
			Account a = dao.chechAccountExist(user);
			if(a == null) {
				dao.signUp(user, pass);
				response.sendRedirect("home");
			} else {
				request.setAttribute("messSignUp", "wrong");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("messSignUp", "wrong");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
