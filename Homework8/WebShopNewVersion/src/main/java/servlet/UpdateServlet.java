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
import servlet.util.DecimalValidator;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class);
	private GoodService service;
	private String message = null;

	@Override
	public void init() {
		service = (GoodService) getServletContext().getAttribute("service");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		DecimalValidator validatorDec = new DecimalValidator();
//		HttpSession session = request.getSession(false);
//		service = (GoodService) session.getAttribute("service");
		/*
		 * на странице catalog.jsp при выборе изменить товар передаем в запрос id и
		 * переходим на страницу update.jsp, там вводим новое имя и цену,плюс с помощью
		 * ${param.id} получаем переданное в запросе id товара и все это передаем в
		 * запросе на этот сервлет,здесь все считываем, передаем в метод изменения и
		 * затем переходим обратно на страницу каталога(через сервлет,реагирующий на
		 * адрес /all)
		 */
		String id = request.getParameter("id");
		String nameNew = request.getParameter("update-nameNew");
		String priceNew = request.getParameter("update-priceNew");
		LOGGER.info("The user wants to change the product");
		String updatePrice = validatorDec.valid(priceNew);
		try {
			LOGGER.info("by product id " + id + " change name " + nameNew + " price " + priceNew);
			service.update(id, nameNew, updatePrice);
			message = "Successfully updated";
			LOGGER.info(message);
			request.setAttribute("message_action", message);
			request.getRequestDispatcher("/all").forward(request, response);
		} catch (ServiceException e) {
			LOGGER.error("Problems with changing product: " + e);
			request.setAttribute("message_action", "Problems with changing product.Вызывайте фиксиков");
			request.getRequestDispatcher("/all").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
