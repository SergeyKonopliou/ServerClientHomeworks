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
	public void init(){
		service = (GoodService) getServletContext().getAttribute("service");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
//		HttpSession session = request.getSession(false);
//		service = (GoodService) session.getAttribute("service");
			String id = request.getParameter("id");
			LOGGER.info("Поступил запрос на удаление товара под id = " + id);
			if (!id.isEmpty() && id!= null) {
				try {
					service.delete(id);
					message = "Успешно удалено";
					LOGGER.info(message);
					request.setAttribute("message_action", message);
					request.getRequestDispatcher("/all").forward(request, response);
				} catch (ServiceException e) {
					LOGGER.error("При удалении товара возникла ошибка" + e);
					request.setAttribute("message_action", "При удалении товара возникла ошибка.Вызывайте фиксиков");
					request.getRequestDispatcher("/all").forward(request, response);
				}
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
