package by.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.main.Good;
import by.main.GoodsCatalog;

/**
 * Servlet который срабатывает когда мы хотим посмотреть каталог товаров. Если пользователь уже вошел
 * по имени и паролю,то каталог мы берем из атрибута сессии,иначе берем стандартный каталог.
 */
@WebServlet("/all")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsCatalog catalog = new GoodsCatalog();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("loginName") != null) {
			catalog = (GoodsCatalog) session.getAttribute("catalog");
		}

		req.setAttribute("catalog", processRequest(req, resp));
		getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

	// для поиска товара по названию
	private List<Good> processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String value = req.getParameter("search-good");
		List<Good> goods;
		if (value != null && !value.isEmpty()) {
			goods = catalog.findGoods(value);
		} else {
			goods = catalog.getGoods();
		}
		return goods;
	}

}
