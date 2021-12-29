package exception;

public class DaoCreateTableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Exception except;

	public DaoCreateTableException(String msg) {
		super(msg);
	}

	public DaoCreateTableException(String msg, Exception e) {
		super(msg);
		except = e;
	}

	public Exception getExcept() {
		return except;
	}
}
