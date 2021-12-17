package db;

public interface DBConnection<T> {
	
	public T getConnection(String URL,String user,String password);
}
