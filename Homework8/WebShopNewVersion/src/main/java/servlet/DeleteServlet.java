package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.protobuf.ServiceException;

import service.GoodService;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodService service;
	private String message = null;
	private static final Logger LOGGER = Logger.getLogger(DeleteServlet.class);

	@Override
	public void init() {
		service = (GoodService) getServletContext().getAttribute("service");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
//		HttpSession session = request.getSession(false);
//		service = (GoodService) session.getAttribute("service");
		String id = request.getParameter("id");
		LOGGER.info("There was a request to remove an item with an id = " + id);
		try {
			service.delete(id);
			message = "Successfully deleted";
			LOGGER.info(message);
			request.setAttribute("message_action", message);
			request.getRequestDispatcher("/all").forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("An error occurred while deleting the product " + e);
			request.setAttribute("message_action", "An error occurred while deleting the product.Вызывайте фиксиков");
			request.getRequestDispatcher("/all").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
