package exception;

public class BDDaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BDDaoException(String msg) {
		super(msg);
	}

	public BDDaoException(String msg, Exception e) {
		super(msg);
	}

}
