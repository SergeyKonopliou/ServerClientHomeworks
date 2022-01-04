package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.protobuf.ServiceException;

import entity.Good;
import service.GoodService;

/**
 * Servlet который срабатывает когда мы хотим посмотреть каталог товаров. Если
 * пользователь уже вошел по имени и паролю,то каталог мы берем из атрибута
 * сессии,иначе берем стандартный каталог.
 */
@WebServlet("/all")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodService service;
	private List<Good> catalog = new ArrayList<>();
	private String message;
	private String message_action;
	private static final Logger LOGGER = Logger.getLogger(CatalogServlet.class);

	@Override
	public void init() {
		service = (GoodService) getServletContext().getAttribute("service");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		HttpSession session = req.getSession(false);
//		service = (GoodService) session.getAttribute("service");
		String name = req.getParameter("search-good");
		try {
			if (name != null && !name.isEmpty()) {
				catalog = service.load(name);
				message_action = (catalog.isEmpty()) ? "Not found anything" : "Successfully searched";
				LOGGER.info("Search product by name " + name);
				LOGGER.info("Search results: " + message_action);
			} else {
				catalog = service.loadAll();
				message_action = (String) req.getAttribute("message_action");
				LOGGER.info("Retrieving all data from the database");
			}
			message = "Found " + catalog.size() + " product(s)";
			LOGGER.info(message);
			req.setAttribute("catalog", catalog);
			req.setAttribute("message", message);
			req.setAttribute("message_action", message_action);
			getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);
		} catch (ServiceException e) {
			LOGGER.error("Problems with the formation of the catalog of products: " + e);
			req.setAttribute("message_action", "Problems with search. Вызывайте фиксиков");
			req.getRequestDispatcher("/catalog.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

}
