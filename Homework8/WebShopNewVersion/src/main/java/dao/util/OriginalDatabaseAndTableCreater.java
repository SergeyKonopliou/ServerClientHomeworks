package dao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import exception.DBConnectException;
import exception.DaoCreateTableException;

/**
 * Класс,где создается наша бд и таблица(если этого нет),без пула соединений.
 * Затем уже подключаемся к созданной бд через пул соединений и заполняем
 * таблицу первоначальными данными,если таблица пустая
 */
public class OriginalDatabaseAndTableCreater {

	private ConfigConnectManager manager = new MysqlConfigConnectManager();
	private static final Logger LOGGER = Logger.getLogger(OriginalDatabaseAndTableCreater.class);
	private static final String SQL_FILL_QUERY = "SELECT name FROM goods WHERE id IS NOT NULL";

	public OriginalDatabaseAndTableCreater() {

	}

	public void createDatabaseAndTable() throws DBConnectException {
		try (Connection connection = manager.connection();// подключаемся к бд world,так как наша еще не создана
			Statement statement = connection.createStatement();){
			// создание бд и таблицы если их нет
			write("createBaseTable.sql", statement);
		} catch (DBConnectException | SQLException e) {
			LOGGER.error("Проблемы при подключении к базе данных " + e.getMessage());
			throw new DBConnectException("Проблемы при подключении к базе данных " + e.getMessage(), e);
		}
		try(Connection connection = ConnectionPool.getInstance().getConnection();// подключаемся к теперь уже созданной нашей бд
			Statement statement = connection.createStatement();){
			// заполнение таблицы первоначальными данными,если она пустая
			ResultSet resultSet = statement.executeQuery(SQL_FILL_QUERY);
			if (!resultSet.next()) {
				write("fillBaseTable.sql", statement);
			}
			LOGGER.info("Таблица создана");
		} catch (SQLException e) {
			LOGGER.error("Проблемы при создании таблицы с начальными данными " + e.getMessage());
			throw new DaoCreateTableException(
					"Проблемы при начальном создании таблицы с начальными данными " + e.getMessage(), e);
		}

	}

	private void write(String file, Statement statement) {
		String[] strArr;
		String strRequest;
		InputStream is = OriginalDatabaseAndTableCreater.class.getClassLoader().getResourceAsStream(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		try {
			while ((strRequest = in.readLine()) != null) {
				strArr = strRequest.split(";");
				for (int i = 0; i < strArr.length; i++) {
					statement.executeUpdate(strArr[i]);
				}
			}
			in.close();
			is.close();
		} catch (IOException | SQLException e) {
			LOGGER.error("Проблемы при создании и первоначальном заполнении таблицы данных " + e.getMessage());
			throw new DaoCreateTableException(
					"Проблемы при создании и первоначальном заполнении таблицы данных " + e.getMessage(), e);
		}
	}

}
