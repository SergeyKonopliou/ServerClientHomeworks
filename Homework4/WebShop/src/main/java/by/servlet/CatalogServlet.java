package by.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.main.Good;
import by.main.GoodsCatalog;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/all")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GoodsCatalog catalog = new GoodsCatalog();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
//		RequestDispatcher dispatcher = req.getRequestDispatcher("catalog.jsp");
//		dispatcher.forward(req, resp);

	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String value = req.getParameter("name");
		List<Good> goods;
		if (value != null && !value.isEmpty()) {
			String name = value;
			goods = catalog.getGoods(name);
		} else {
			goods = catalog.getGoods();
		}
		resp.getWriter().println("<table>");
		resp.getWriter().println("<th>Name</th>");
		resp.getWriter().println("<th>Price</th>");

		for (Good good : goods) {
			resp.getWriter().println("<tr>");
			resp.getWriter().println("<td>" + good.getName() + "</td>");
			resp.getWriter().println("<td>" + good.getPrice() + "</td>");
			resp.getWriter().println("</tr>");
		}
		resp.getWriter().println("</table>");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
//		RequestDispatcher dispatcher = req.getRequestDispatcher("catalog.jsp");
//		dispatcher.forward(req, resp);
	}

}
