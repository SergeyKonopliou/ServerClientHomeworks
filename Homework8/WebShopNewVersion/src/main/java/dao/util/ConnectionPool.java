package dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import exception.DBConnectException;

/**
 * Класс создания пула соединений и получения соединения из этого пула. Класс
 * ConnectonPool делается singletone(это когда нельзя создать больше одного
 * экземпляра данного класса.Для этого делается приватный конструктор и для
 * доступа к экземпляру класса оздается специальный метод, который возвращает
 * только один экземпляр класса).
 *
 */
public class ConnectionPool {
	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

	private ConnectionPool() {

	}

	private static ConnectionPool instance = null;

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
			LOGGER.info("Пул соединений к базе данных создан");
		}
		LOGGER.info("Получен созданный пул соединений");
		return instance;
	}

	public Connection getConnection() throws DBConnectException {
		Context context;
		Connection connection = null;
		try {
			context = new InitialContext();
			// Для получения источника данных (DataSource) используется механизм JNDI
			// Метод lookup() извлекает объект по ссылке.
			DataSource data = (DataSource) context.lookup("java:comp/env/jdbc/myPool");
			connection = data.getConnection();
			LOGGER.info("Получено соединение " + connection.getMetaData().getConnection().toString());
		} catch (NamingException | SQLException e) {
			LOGGER.error("Проблемы с получением соединения к базе данных " + e);
			throw new DBConnectException("Проблемы с получением соединения к базе данных ", e);
		}
		return connection;
	}
}
