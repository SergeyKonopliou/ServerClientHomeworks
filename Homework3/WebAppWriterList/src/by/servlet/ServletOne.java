package by.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletOne
 */
@WebServlet("/writerslist")
public class ServletOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write("<!DOCTYPE html>\r\n"
				+ "	<html>\r\n"
				+ "	<head>\r\n"
				+ "	<meta charset=\"UTF-8\">\r\n"
				+ "	<title>Writers list</title>\r\n"
				+ " <style type=\"text/css\">\r\n"
						+ "    table {\r\n"
						+ "        border-collapse: collapse;\r\n"
						+ "    }\r\n"
						+ "    th, td {\r\n"
						+ "        border: 1px solid #000;\r\n"
						+ "        padding: 5px;\r\n"
						+ "    }\r\n"
						+ "    td:nth-child(2) {\r\n"
						+ "        text-align: right;\r\n"
						+ "    }\r\n"
						+ "</style>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<h1>Таблица №1</h1>\r\n"
				+ "<table>\r\n"
				+ "    <tr>\r\n"
				+ "        <th>Писатель</th>\r\n"
				+ "        <th>Книга</th>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Рэй Брэдбери</td><td>451° по Фаренгейту</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Джордж Оруэлл</td><td>1984</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Михаил Булгаков</td><td>Мастер и Маргарита</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Грегори Дэвид Робертс</td><td>Шантарам</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Эрих Мария Ремарк</td><td>Три товарища</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Дэниел Киз</td><td>Цветы для Элджернона</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Оскар Уайльд</td><td>Портрет Дориана Грея</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Антуан де Сент-Экзюпери</td><td>Маленький принц</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Джером Д. Сэлинджер</td><td>Над пропастью во ржи</td>\r\n"
				+ "    </tr>\r\n"
				+ "    <tr>\r\n"
				+ "        <td>Рэй Брэдбери</td><td>Вино из одуванчиков</td>\r\n"
				+ "    </tr>\r\n"
				+ "</table>\r\n"
				+ "	</body>\r\n"
				+ "	</html>");
	}
}
