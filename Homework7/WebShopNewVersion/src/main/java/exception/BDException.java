package exception;

public class BDException extends RuntimeException {

	private static final long serialVersionUID = -3475368840911469763L;

	public BDException(String msg) {
        super(msg);
    }
}
