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

@WebServlet("/search")
public class SearchControl extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String txtSearch = request.getParameter("txt");
		DAO dao = new DAO();
		List<Product> list =  dao.searchByName(txtSearch);
		List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
		
		request.setAttribute("listP", list);
		request.setAttribute("listC", listC);
        request.setAttribute("p", last);
        request.setAttribute("txts", txtSearch);
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
