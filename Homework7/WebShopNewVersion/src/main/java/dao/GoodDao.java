package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Good;
import exception.BDDaoException;
import exception.DaoException;

public class GoodDao implements MainDao<Good> {

	private static final String SQL_DELETE_GOOD_QUERY = "DELETE FROM goods WHERE name = ?";
	private static final String SQL_UPDATE_GOOD_QUERY_WITH_PRICE = "UPDATE goods SET name = ?,price = ? WHERE name = ?";
	private static final String SQL_UPDATE_GOOD_QUERY_WITHOUT_PRICE = "UPDATE goods SET name = ? WHERE name = ?";
	private static final String SQL_ADD_GOOD_QUERY = "INSERT INTO goods(name, price) VALUES (?,?)";
	private static final String SQL_FIND_GOOD_ABSOLUTELY_QUERY = "SELECT name,price FROM goods WHERE name = ?";
	private static final String SQL_FIND_GOOD_LIKE_QUERY = "SELECT name,price FROM goods WHERE name LIKE ?";
	private static final String SQL_SELECT_ALL_GOOD_QUERY = "SELECT * FROM goods";

	private BDConnect bd;
	private boolean isFind = false;// для методов изменения и удаления товара,чтобы знать есть ли в базе искомый
									// товар
	private boolean isUpdateOrDelete = false;// если поиск выполняется для метода изменить или удалить,то выставляем в
												// true

	public GoodDao() {
		try {
			bd = new BDConnect();
			bd.createTable();
		} catch (BDDaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Good> getAll() throws DaoException {
		List<Good> newGoods = new ArrayList<>();
		Connection connection = bd.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(SQL_SELECT_ALL_GOOD_QUERY);
			ResultSet resultSet = null;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString(2);
				double price = resultSet.getDouble(3);
				newGoods.add(new Good(name, price));
			}
		} catch (SQLException e) {
			throw new DaoException("Ошибка в методе получения всех товаров", e);
		}

		return newGoods;
	}

	@Override
	public List<Good> find(String nameGood) throws DaoException {
		List<Good> foundGoods = new ArrayList<>();
		Connection connection = bd.getConnection();
		PreparedStatement statement;
		try {
			if (isUpdateOrDelete) {
				statement = connection.prepareStatement(SQL_FIND_GOOD_ABSOLUTELY_QUERY);
				statement.setString(1, nameGood);
			} else {
				statement = connection.prepareStatement(SQL_FIND_GOOD_LIKE_QUERY);
				statement.setString(1, nameGood);
			}

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				foundGoods.add(new Good(name, price));
				isFind = true;
			}
		} catch (SQLException e) {
			throw new DaoException("Возникла ошибка при поиске товара по наименованию", e);
		}
		return foundGoods;
	}

	@Override
	public void setGoods(Good good) throws DaoException {
		Connection connection = bd.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(SQL_ADD_GOOD_QUERY);
			statement.setString(1, good.getName());
			statement.setDouble(2, good.getPrice());
			statement.executeUpdate();// executeQuery() здесь не работает!!!
		} catch (SQLException e) {
			throw new DaoException("Возникла ошибка при добавлении нового товара в БД", e);
		}

	}

	@Override
	public void update(String oldName, String newName, String newPrice) throws DaoException {
		Connection connection = bd.getConnection();
		PreparedStatement statement;
		try {
			isUpdateOrDelete = true;
			find(oldName);
			isUpdateOrDelete = false;
			if (isFind) {
				if (!newPrice.isEmpty()) {
					statement = connection.prepareStatement(SQL_UPDATE_GOOD_QUERY_WITH_PRICE);
					statement.setString(1, newName);
					statement.setDouble(2, Double.parseDouble(newPrice));
					statement.setString(3, oldName);
				} else {
					statement = connection.prepareStatement(SQL_UPDATE_GOOD_QUERY_WITHOUT_PRICE);
					statement.setString(1, newName);
					statement.setString(2, oldName);
				}
				statement.executeUpdate();
				isFind = false;
			}
		} catch (SQLException e) {
			throw new DaoException("Возникла ошибка в методе updateGood()", e);
		}
	}

	@Override
	public void delete(String name) throws DaoException {
		Connection connection = bd.getConnection();
		PreparedStatement statement;
		try {
			isUpdateOrDelete = true;
			find(name);
			isUpdateOrDelete = false;
			if (isFind) {
				statement = connection.prepareStatement(SQL_DELETE_GOOD_QUERY);
				statement.setString(1, name);
				statement.executeUpdate();
				isFind = false;
			}
		} catch (SQLException e) {
			throw new DaoException("Возникла ошибка при удалении товара", e);
		}
	}

}
