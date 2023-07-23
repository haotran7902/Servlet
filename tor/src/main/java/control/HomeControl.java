package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Category;
import entity.Product;
import dao.DAO;

@WebServlet(urlPatterns = {"/home"})
public class HomeControl extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//b1: get data from dao
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
        
        // set data to jsp
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.setAttribute("p", last);
        request.getRequestDispatcher("Home.jsp").forward(request, response);

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
