package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Category;
import entity.Product;

@WebServlet("/detail")
public class DetailControl extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("pid");
		
		//b1: get data from dao
        DAO dao = new DAO();
        Product p = dao.getProductByID(id);
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
        
        // set data to jsp
        request.setAttribute("p", p);
        request.setAttribute("listC", listC);
        request.setAttribute("last", last);
        request.getRequestDispatcher("Detail.jsp").forward(request, response);

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
