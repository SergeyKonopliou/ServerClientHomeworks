package exception;

public class DBConnectException extends Exception{

	private static final long serialVersionUID = -3475368840911469763L;
	private Exception except;

	public DBConnectException(String msg) {
        super(msg);
    }
	
	public DBConnectException(String msg, Exception e) {
		super(msg);
		except = e;
	}

	public Exception getExcept() {
		return except;
	}
}
