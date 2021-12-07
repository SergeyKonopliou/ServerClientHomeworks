package by.databasework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {
	
public static final String URL = "jdbc:mysql://localhost:3306/webshop?serverTimezone=Europe/Minsk"; 
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Ошибка драйвера: " + e.getLocalizedMessage());
		}
	}
	
	public static Connection getConnection(String user,String password) {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(URL, user, password);
			System.out.println(connect.getMetaData().getURL());
		} catch (SQLException e) {
			System.out.println("Ошибка подключения к базе данных,проверьте url,login и password");
		}
		return connect;
	}

}
