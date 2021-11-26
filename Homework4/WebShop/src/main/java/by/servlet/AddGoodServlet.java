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
 * Servlet implementation class AddGoodServlet
 */
@WebServlet("/add")
public class AddGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
//		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession(false);
//       if(session==null){
//           response.sendRedirect(request.getContextPath()+"/index.jsp");
//           return;
//       }
       String loginName = (String)session.getAttribute("loginName");
       String loginPass = (String)session.getAttribute("loginPass");
       if(loginName==null || loginPass == null){
           response.sendRedirect(request.getContextPath()+"/index.jsp");
           return;
       }
       
//       String html = "<html> <body> Добро пожаловать," + loginName + ", <a href='"+request.getContextPath()+"/LogoutServlet'> Безопасный выход </a> </ body> </ html>" ;
//       writer.write(html);
       
       response.sendRedirect(request.getContextPath()+"/addgoods.jsp");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
