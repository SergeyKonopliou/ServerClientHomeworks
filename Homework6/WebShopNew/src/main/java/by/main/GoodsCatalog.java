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
	private boolean isFind = false;//для методов изменения и удаления товара,чтобы знать есть ли в базе искомый товар
	private boolean isUpdateOrDelete = false;//если поиск выполняется для метода изменить или удалить,то выставляем в true

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
	
	/**
	 * метод получения всех товаров для формирования каталога товаров
	 */
	public List<Good> getGoods() {
		List<Good> newGoods = new ArrayList<>();
		try {
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

	/**
	 * метод поиска товара по названию
	 */
	public List<Good> findGoods(String nameGood) {
		List<Good> foundGoods = new ArrayList<>();
		String query;
		if(isUpdateOrDelete) {
			 query = "SELECT name,price FROM goods WHERE name = ?";
		}else {
			query = "SELECT name,price FROM goods WHERE name LIKE ?";
			nameGood = "%" + nameGood + "%";
		}
 
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, nameGood);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				foundGoods.add(new Good(name, price));
				isFind = true;
			}
		} catch (SQLException e) {
			System.out.println("Возникла ошибка при поиске товара по наименованию");
		}
		return foundGoods;
	}

	/**
	 * метод добавления нового товара
	 */
	public void setGoods(Good good) {
		String query = String.format("INSERT INTO goods(name, price) VALUES ('%s'," + good.getPrice() + ")", good.getName());
		System.out.println(query);
		try {
			statement.execute(query);
		} catch (SQLException e) {
			System.out.println("Ошибка при добавлении нового товара в БД");
		}
	}
	
	/**
	 * метод изменения существующего товара
	 */
	public String updateGood(String oldName,String newName,String newPrice) {
		isUpdateOrDelete = true;
		findGoods(oldName);
		isUpdateOrDelete = false;
		String query;
		if(isFind) {
			if(!newPrice.isEmpty()) {
				query = String.format("UPDATE goods SET name = '%s',price = " + Double.parseDouble(newPrice) + " WHERE name = '%s'",
						newName,oldName);
				System.out.println(query);
			}else {
				query = String.format("UPDATE goods SET name = '%s' WHERE name = '%s'",
						newName,oldName);
				System.out.println(query);
			}
			
			try {
				statement.execute(query);
				isFind = false;
				return "Изменено";
			} catch (SQLException e) {
				System.out.println("Возникла ошибка в методе updateGood()");
				e.printStackTrace();
			}
		}
		return "Товар не найден";
	}
	
	/**
	 * метод удаления существующего товара
	 */
	public String deleteGood(String name) {
		isUpdateOrDelete = true;
		findGoods(name);
		isUpdateOrDelete = false;
		if(isFind) {
			String query = String.format("DELETE FROM goods WHERE name = '%s'",name);
			System.out.println(query);
			try {
				statement.execute(query);
				isFind = false;
				return "Удалено";
			} catch (SQLException e) {
				System.out.println("Возникла ошибка в методе deleteGood()");
				e.printStackTrace();
			}
		}
		return "Товар не найден";
	}
}
