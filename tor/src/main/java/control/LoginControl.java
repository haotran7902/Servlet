package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Account;
import entity.Category;
import entity.Product;

@WebServlet("/login")
public class LoginControl extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		DAO dao = new DAO();
		Account a = dao.login(username, password);
		if(a == null) {
			request.setAttribute("mess", "wrong");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("home").forward(request, response);
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
