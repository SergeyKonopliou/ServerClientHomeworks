package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.protobuf.ServiceException;

import entity.Good;
import service.GoodService;
import service.MainService;
import util.DecimalValidator;
import util.StringValidator;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MainService<Good> service = new GoodService();
	private boolean isAdd = false;
	private boolean isDelete = false;
	private boolean isUpdate = false;
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
				if (validatorStr.valid(name) && validatorDec.valid(price)) {
					try {
						isAdd = service.add(new Good(name, Double.parseDouble(price)));
						message = "Успешно добавлено";
					} catch (NumberFormatException | ServiceException e) {
						message = e.getMessage();
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
				break;
			case ("delete"):
				if (validatorStr.valid(name)) {
					try {
						isDelete = service.delete(name);
						message = "Успешно удалено";
					} catch (ServiceException e) {
						message = e.getMessage();
						System.out.println(e.getMessage());
					}
				}
				break;
			case ("update"):
				if (validatorStr.valid(name) && validatorStr.valid(nameNew) && validatorDec.valid(priceNew)) {
					try {
						isUpdate = service.update(name, nameNew, priceNew);
						message = "Успешно изменено";
					} catch (ServiceException e) {
						message = e.getMessage();
						System.out.println(e.getMessage());
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
