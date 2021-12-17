package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.protobuf.ServiceException;

import entity.Good;
import service.GoodService;
import service.MainService;

/**
 * Servlet который срабатывает когда мы хотим посмотреть каталог товаров. Если
 * пользователь уже вошел по имени и паролю,то каталог мы берем из атрибута
 * сессии,иначе берем стандартный каталог.
 */
@WebServlet("/all")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainService<Good> service = new GoodService();
	private List<Good> catalog = new ArrayList<>();
	String message;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		String name = req.getParameter("search-good");
		if (session != null && session.getAttribute("loginName") != null) {
			try {
				if (name != null && !name.isEmpty()) {
					catalog = service.load(name);
				} else {
					catalog = service.loadAll();
				}
				message = "Найдено " + catalog.size() + " товар(ов)";
			} catch (ServiceException e) {
				message = "Проблемы с поиском";
				req.getRequestDispatcher("/catalog.jsp").forward(req, resp);
				System.out.println(e);
			}
		}

		req.setAttribute("catalog", catalog);
		req.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

}
