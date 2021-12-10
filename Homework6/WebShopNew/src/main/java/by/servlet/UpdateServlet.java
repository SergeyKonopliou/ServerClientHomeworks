package by.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.main.GoodsCatalog;

@WebServlet({"/delete","/update"})
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GoodsCatalog catalog;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		catalog = (GoodsCatalog) session.getAttribute("catalog");
		String name = request.getParameter("update-name");
		switch(request.getParameter("type")) {
			case ("delete"):
				catalog.deleteGood(name);
				break;
			case ("update"):
				catalog.updateGood(name, request.getParameter("update-nameNew"),request.getParameter("update-priceNew"));
				break;
		}
		
		response.sendRedirect(request.getContextPath() + "/addgoods.jsp");
	}

}
