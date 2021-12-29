package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logg")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
//	private GoodService service = new GoodService();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("loginName") != null) {
			getServletContext().getRequestDispatcher("/userPage.jsp").forward(request, response);
		} else {

			String name = (String) request.getParameter("name");
			String password = (String) request.getParameter("pass");

			if (!name.isEmpty() && !password.isEmpty()) {
				session = request.getSession();
				session.setAttribute("loginName", name);
				session.setAttribute("loginPass", password);
//				session.setAttribute("service", service);
				LOGGER.info("Вход в систему под логином: " + name + " и паролем: " + password);
				getServletContext().getRequestDispatcher("/userPage.jsp").forward(request, response);
			} else {
				LOGGER.warn("Некорректно введены данные для входа. Логин: " + name + ",пароль: " + password);
				request.setAttribute("message", "Введите данные для входа");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}
}
