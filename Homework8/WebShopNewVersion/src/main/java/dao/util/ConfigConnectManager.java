package dao.util;

import java.sql.Connection;

import exception.DBConnectException;

public interface ConfigConnectManager {
	
	public Connection connection() throws DBConnectException;
	
}
