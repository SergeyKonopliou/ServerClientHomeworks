package by.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.databasework.ConnectManager;

public class GoodsCatalog {
	private List<Good> goods;
	private Connection connection;
	private Statement statement = null;

	public GoodsCatalog() {
		goods = new ArrayList<>();
		connection = ConnectManager.getConnection("root", "321334q");
		createTable();
	}

	private void createTable() {
		try {
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
		} catch (SQLException e) {
			System.out.println("Ошибка при создании таблицы");
			e.printStackTrace();
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
				String query = String.format("INSERT INTO goods(name, price) VALUES ('%s'," + good.getPrice() + ")", good.getName());
				statement.execute(query); // добавление в пакет
			}
//			statement.executeBatch(); // добавление данных из пакета в базу данных
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Проблемы при начальном создании таблицы с начальными данными");
		}
	}

	public List<Good> findGoods(String nameGood) {
		List<Good> foundGoods = new ArrayList<>();
		String query = "SELECT name,price FROM goods WHERE name LIKE ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, nameGood);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				foundGoods.add(new Good(name, price));
			}
		} catch (SQLException e) {
			System.out.println("Возникла ошибка при поиске товара по наименованию");
		}
		return foundGoods;
	}

	public List<Good> getGoods() {
		List<Good> newGoods = new ArrayList<>();
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM goods");
			while (resultSet.next()) {
				String name = resultSet.getString(2);
				double price = resultSet.getDouble(3);
				newGoods.add(new Good(name, price));
			}
			goods = newGoods;
		} catch (SQLException e) {
			System.out.println("Ошибка метода getGoods()");
		}

		return goods;
	}

	public void setGoods(Good good) {
		String query = String.format("INSERT INTO goods(name, price) VALUES ('%s'," + good.getPrice() + ")", good.getName());
		System.out.println(query);
		try {
			statement.execute(query);
		} catch (SQLException e) {
			System.out.println("Ошибка при добавлении нового товара в БД");
		}
	}
}
