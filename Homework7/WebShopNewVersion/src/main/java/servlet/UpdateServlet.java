package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.protobuf.ServiceException;

import entity.Good;
import service.GoodService;
import service.MainService;
import util.DecimalValidator;
import util.StringValidator;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class);
	private MainService<Good> service = new GoodService();
	private String message = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		StringValidator validatorStr = new StringValidator();
		DecimalValidator validatorDec = new DecimalValidator();
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("loginName") != null) {
			String name = request.getParameter("update-name");
			String nameNew = request.getParameter("update-nameNew");
			String price = request.getParameter("update-price");
			String priceNew = request.getParameter("update-priceNew");
			switch (request.getParameter("type")) {
			case ("addNew"):
				LOGGER.info("Пользователь хочет добавить новый товар");
				if (validatorStr.valid(name) && validatorDec.valid(price)) {
					try {
						LOGGER.info(name + " " + price);
						service.add(new Good(name, Double.parseDouble(price)));
						message = "Успешно добавлено";
						LOGGER.info("Успешно добавлено");
					} catch (NumberFormatException | ServiceException e) {
						message = e.getMessage();
						LOGGER.error(e.getMessage());
					}
				}
				break;
			case ("delete"):
				LOGGER.info("Пользователь хочет удалить товар");
				if (validatorStr.valid(name)) {
					try {
						LOGGER.info(name);
						service.delete(name);
						message = "Успешно удалено";
						LOGGER.info("Успешно удалено");
					} catch (ServiceException e) {
						message = e.getMessage();
						LOGGER.error(e.getMessage());
					}
				}
				break;
			case ("update"):
				LOGGER.info("Пользователь хочет изменить товар");
				if (validatorStr.valid(name) && validatorStr.valid(nameNew) && validatorDec.valid(priceNew)) {
					try {
						LOGGER.info(name + " изменить на " + nameNew + " " + priceNew);
						service.update(name, nameNew, priceNew);
						message = "Успешно изменено";
						LOGGER.info("Успешно изменено");
					} catch (ServiceException e) {
						message = e.getMessage();
						LOGGER.error(e.getMessage());
					}
				}
				break;
			}

		}

		if (message != null) {
			request.setAttribute("message", message);
			request.getRequestDispatcher("/update.jsp").forward(request, response);
		}

	}

}
