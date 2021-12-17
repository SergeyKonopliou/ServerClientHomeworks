package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConnector;
import entity.Good;
import exception.BDDaoException;
import exception.BDException;

public class BDConnect {
	private List<Good> goods;
	private Connection connection;
	private Statement statement = null;
	public static final String URL = "jdbc:mysql://localhost:3306/webshop?serverTimezone=Europe/Minsk";
	
	public BDConnect() {
		
	}
	
	public void createTable() {
		try {
			goods = new ArrayList<>();
			connection = new MysqlConnector().getConnection(URL,"root", "321334q");
			statement = connection.createStatement();
			statement.execute("CREATE TABLE if not exists " + "goods (id BIGINT PRIMARY KEY "
					+ "AUTO_INCREMENT, name varchar(100), " + "price DOUBLE(5,2));");
			// проверка,что в таблице есть первоначальные данные,тогда записывать снова их
			// не нужно
			ResultSet resultSet = statement.executeQuery("SELECT * FROM goods");
			if (!resultSet.next()) {
				initialFillingTable();
				initialWriteTable();
			}
		} catch (SQLException | BDException e) {
			throw new BDDaoException("Ошибка при создании таблицы " + e.getMessage(),e);
		}
		System.out.println("Таблица создана");
	}

	// создание начальных данных для пакетной записи в БД
	private void initialFillingTable() {
		goods = new ArrayList<>();
		Good good = new Good();
		good.setName("Велосипед");
		good.setPrice(599.99);
		Good good2 = new Good();
		good2.setName("Балалайка");
		good2.setPrice(37.80);
		Good good3 = new Good();
		good3.setName("Молоток");
		good3.setPrice(19.99);
		goods.add(good);
		goods.add(good2);
		goods.add(good3);
	}

	// запись начальных данных в БД
	private void initialWriteTable() {
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			for (Good good : goods) {
				String query = String.format("INSERT INTO goods(name, price) VALUES"
						+ " ('%s'," + good.getPrice() + ")", good.getName());
				statement.execute(query); // добавление в пакет
			}
			connection.commit();

		} catch (SQLException | BDException e) {
			throw new BDDaoException("Проблемы при начальном создании таблицы с начальными данными "
		+ e.getMessage(),e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
