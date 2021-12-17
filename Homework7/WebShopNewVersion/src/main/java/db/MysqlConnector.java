package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.BDException;

public class MysqlConnector implements DBConnection<Connection> {
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	@Override
	public Connection getConnection(String URL, String user, String password) {
		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new BDException("Проблемы с драйвером " + e.getMessage());
		}
		
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(URL, user, password);
			System.out.println(connect.getMetaData().getURL());
		} catch (SQLException e) {
			throw new BDException("Проблемы с подключением к базе данных " + e.getMessage());
		}
		return connect;
	}

}
