package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.protobuf.ServiceException;

import entity.Good;
import service.GoodService;
import servlet.util.DecimalValidator;

@WebServlet("/add")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodService service;
	private String message = null;
	private static final Logger LOGGER = Logger.getLogger(AddProductServlet.class);
	
	@Override
	public void init(){
		service = (GoodService) getServletContext().getAttribute("service");
	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
//		HttpSession session = request.getSession(false);
//		service = (GoodService) session.getAttribute("service");
		
		DecimalValidator validatorDec = new DecimalValidator();

		String name = request.getParameter("add-name");
		String price = request.getParameter("add-price");
		LOGGER.info("Запрос на добавление нового товара "+ name + " " + price);
		if (!name.isEmpty() && name != null) {
			try {
				/**
				 * валидатор validatorDec проверяет подходит ли цена под дробное число, если
				 * цену не ввели или ввели некорректные данные,то записываем 0.0
				 */
				service.add(new Good(name, Double.parseDouble(validatorDec.valid(price))));
				message = "Успешно добавлено";
			} catch (NumberFormatException | ServiceException e) {
				LOGGER.error("Проблемы при добавлении нового товара: " + e);
				request.setAttribute("message_action", "Возникли проблемы с добавление нового товара.Вызывайте фиксиков");
				request.getRequestDispatcher("/all").forward(request, response);
			}
		} else {
			message = "Некорректные данные для добавления";
		}
		LOGGER.info(message);
		request.setAttribute("message_action", message);
		request.getRequestDispatcher("/all").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
