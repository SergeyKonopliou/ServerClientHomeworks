package dao.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import exception.DBConnectException;

/**
 * Класс,реализующий первоначальное подключение к базе данных
 */
public class MysqlConfigConnectManager implements ConfigConnectManager {

	@Override
	public Connection connection() throws DBConnectException {
		Connection connect = null;
		try {
			Properties property = new Properties();
			// 1вариант
			InputStream is = MysqlConfigConnectManager.class.getClassLoader()
					.getResourceAsStream("mysqldatabase.properties");
			property.load(is);
			is.close();
			// 2вариант
			// property.load(new FileReader("/resources/mysqldatabase.properties")); //не
			// верный путь

			String driver = property.getProperty("jdbc.driver");
			String connectionURL = property.getProperty("jdbc.url");
			String username = property.getProperty("jdbc.username");
			String password = property.getProperty("jdbc.password");

			Class.forName(driver);
			connect = DriverManager.getConnection(connectionURL, username, password);

			return connect;

		} catch (Exception e) {
			throw new DBConnectException("Проблемы с подключением к базе данных " + e.getMessage(), e);
		}
	}

}
