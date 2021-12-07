package by.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.main.GoodsCatalog;

/**
 * Servlet вызывается при заполнении формы входа на стартовой странице. 
 * Если будут введены данные для входа,то перебросит на страницу пользователя indexIn.jsp,
 * где можно будет добавить товар
 */
@WebServlet("/logg")
public class LoggServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsCatalog catalog = new GoodsCatalog();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("loginName") != null) {
			getServletContext().getRequestDispatcher("/indexIn.jsp").forward(request, response);
		} else {
			String name = (String) request.getParameter("name");
			String password = (String) request.getParameter("pass");

			if (!name.isEmpty() && !password.isEmpty()) {
				session = request.getSession();
				session.setAttribute("loginName", name);
				session.setAttribute("loginPass", password);
				session.setAttribute("catalog", catalog);
				getServletContext().getRequestDispatcher("/indexIn.jsp").forward(request, response);
//				response.sendRedirect(request.getContextPath()+"/indexIn.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		}
		
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
