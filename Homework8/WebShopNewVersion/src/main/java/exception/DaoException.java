package exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = -8701662673949824535L;
	private Exception except;

	public DaoException(String msg) {
        super(msg);
    }
	
	public DaoException(String msg, Exception e) {
		super(msg);
		except = e;
	}

	public Exception getExcept() {
		return except;
	}
}
