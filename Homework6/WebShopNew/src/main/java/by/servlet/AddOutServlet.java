package by.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet который срабатывает когда мы хотим в авторизованном режиме выйти со страницы
 * просмотра каталога товаров
 */
@WebServlet("/AddOutServlet")
public class AddOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("loginName") != null) {
			getServletContext().getRequestDispatcher("/indexIn.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
