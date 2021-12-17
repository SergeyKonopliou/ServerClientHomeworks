package exception;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = -4224008960726699770L;

	public ValidateException(String msg) {
        super(msg);
    }

}
