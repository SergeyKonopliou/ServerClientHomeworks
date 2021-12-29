package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dao.util.ConnectionPool;
import dao.util.OriginalDatabaseAndTableCreater;
import entity.Good;
import exception.DBConnectException;
import exception.DaoException;


public class GoodDao {

	private static final String SQL_DELETE_GOOD_QUERY = "DELETE FROM goods WHERE id = ?";
	private static final String SQL_UPDATE_GOOD_QUERY = "UPDATE goods SET name = ?,price = ? WHERE id = ?";
	private static final String SQL_UPDATE_GOOD_QUERY_WITHOUT_PRICE = "UPDATE goods SET name = ? WHERE id = ?";
	private static final String SQL_UPDATE_GOOD_QUERY_WITHOUT_NAME = "UPDATE goods SET price = ? WHERE id = ?";
	private static final String SQL_ADD_GOOD_QUERY = "INSERT INTO goods(name, price) VALUES (?,?)";
	private static final String SQL_FIND_GOOD_LIKE_QUERY = "SELECT name,price FROM goods WHERE name LIKE ?";
	private static final String SQL_SELECT_ALL_GOOD_QUERY = "SELECT * FROM goods";
	private static final Logger LOGGER = Logger.getLogger(GoodDao.class);
	

	private OriginalDatabaseAndTableCreater bd;

	public GoodDao() {

		bd = new OriginalDatabaseAndTableCreater();
		try {
			bd.createDatabaseAndTable();
		} catch (DBConnectException e) {
			e.printStackTrace();
		}

	}

	public List<Good> getAll() throws DaoException {
		
		List<Good> newGoods = new ArrayList<>();
		try(Connection connection = ConnectionPool.getInstance().getConnection();
			Statement statement = connection.createStatement();){
			ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_GOOD_QUERY);
			LOGGER.info("Запрос в базу данных: " + SQL_SELECT_ALL_GOOD_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				newGoods.add(new Good(id, name, price));
			}
		} catch (SQLException | DBConnectException e) {
			throw new DaoException("Ошибка в методе получения всех товаров " + e.getMessage(), e);
		}

		return newGoods;
	}

	public List<Good> find(String nameGood) throws DaoException {
		
		List<Good> foundGoods = new ArrayList<>();
		try(Connection connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_GOOD_LIKE_QUERY);){
			statement.setString(1, "%" + nameGood + "%");
			LOGGER.info("Запрос в базу данных: " + SQL_FIND_GOOD_LIKE_QUERY);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				foundGoods.add(new Good(id, name, price));
			}
		} catch (SQLException | DBConnectException e) {
			throw new DaoException("Возникла ошибка при поиске товара по наименованию " + e.getMessage(), e);
		}
		return foundGoods;
	}

	public void add(Good good) throws DaoException {
		
		try(Connection connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_GOOD_QUERY);){
			statement.setString(1, good.getName());
			statement.setDouble(2, good.getPrice());
			LOGGER.info("Запрос в базу данных: " + SQL_ADD_GOOD_QUERY);
			statement.executeUpdate();// executeQuery() здесь не работает!!!
		} catch (SQLException | DBConnectException e) {
			throw new DaoException("Возникла ошибка при добавлении нового товара в БД " + e.getMessage(), e);
		}

	}

	public void update(String id, String newName, String newPrice) throws DaoException {
		
		try (Connection connection = ConnectionPool.getInstance().getConnection();){
			PreparedStatement statement;
		
			if (!newPrice.isEmpty() && !newName.isEmpty()) {
				statement = connection.prepareStatement(SQL_UPDATE_GOOD_QUERY);
				statement.setString(1, newName);
				statement.setDouble(2, Double.parseDouble(newPrice));
				statement.setInt(3, Integer.parseInt(id));
				LOGGER.info("Запрос в базу данных: " + SQL_UPDATE_GOOD_QUERY);
			} else if (!newName.isEmpty()) {
				statement = connection.prepareStatement(SQL_UPDATE_GOOD_QUERY_WITHOUT_PRICE);
				statement.setString(1, newName);
				statement.setInt(2, Integer.parseInt(id));
				LOGGER.info("Запрос в базу данных: " + SQL_UPDATE_GOOD_QUERY_WITHOUT_PRICE);
			} else {
				statement = connection.prepareStatement(SQL_UPDATE_GOOD_QUERY_WITHOUT_NAME);
				statement.setDouble(1, Double.parseDouble(newPrice));
				statement.setInt(2, Integer.parseInt(id));
				LOGGER.info("Запрос в базу данных: " + SQL_UPDATE_GOOD_QUERY_WITHOUT_NAME);
			}
			statement.executeUpdate();
			statement.close();
		} catch (SQLException | DBConnectException e) {
			throw new DaoException("Возникла ошибка в методе updateGood() " + e.getMessage(), e);
		}
	}

	public void delete(String id) throws DaoException {
		
		try(Connection connection = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE_GOOD_QUERY);){
			statement.setInt(1, Integer.parseInt(id));
			LOGGER.info("Запрос в базу данных: " + SQL_DELETE_GOOD_QUERY);
			statement.executeUpdate();
		} catch (SQLException | DBConnectException e) {
			throw new DaoException("Возникла ошибка при удалении товара " + e.getMessage(), e);
		}
	}

}
