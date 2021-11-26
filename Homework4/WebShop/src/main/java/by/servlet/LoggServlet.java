package by.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoggServlet
 */
@WebServlet("/logg")
public class LoggServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name = (String) request.getParameter("name");
		String password = (String) request.getParameter("pass");

		if (name != "" && password != "") {
			HttpSession session = request.getSession();
			session.setAttribute("loginName", name);
			session.setAttribute("loginPass", password);
			session.setAttribute("in", true);
			getServletContext().getRequestDispatcher("/indexIn.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath()+"/indexIn.jsp");
		} else {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
	  

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
