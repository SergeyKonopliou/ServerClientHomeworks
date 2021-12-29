package servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthificationFilter
 */
@WebFilter("*.jsp")//если прописать все адреса(/*),то не подключает css на страницах
public class AuthificationFilter implements Filter {

  
    public AuthificationFilter() {

    }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse resp = (HttpServletResponse) response;
		final HttpSession session = req.getSession(false);
		
		String name = (String) req.getParameter("name");
		String password = (String) req.getParameter("pass");
		
		if(session != null && session.getAttribute("loginName") != null && session.getAttribute("loginPass") != null) {
			chain.doFilter(req, resp);
		}
		else if(name != null && password != null) {
			req.getRequestDispatcher("/logg").forward(req, resp);
		}else {
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
