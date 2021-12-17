package by.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.main.Good;
import by.main.GoodsCatalog;

/**
 * Servlet вызываемы при вошедшем пользовате при переходе на страницу добавления
 * нового товара.При добавлении товара перезаписываем в атрибут сессии catalog
 * новое значение,чтобы можно было в каталоге вывести обновленные данные
 */
@WebServlet("/add")
public class AddGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsCatalog catalog;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		catalog = (GoodsCatalog) session.getAttribute("catalog");

		String loginName = (String) session.getAttribute("loginName");
		String loginPass = (String) session.getAttribute("loginPass");
		if (loginName == null || loginPass == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}

		session.setAttribute("catalog", processRequest(request, response));
		response.sendRedirect(request.getContextPath() + "/addgoods.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// для добавления товара
	private GoodsCatalog processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("add-name");
		String price = req.getParameter("add-price");
		if (name != null && !name.isEmpty() && price != null) {
			Good good = new Good();
			good.setName(name);
			good.setPrice(Double.parseDouble(price));
			catalog.setGoods(good);
		}
		return catalog;
	}
}
