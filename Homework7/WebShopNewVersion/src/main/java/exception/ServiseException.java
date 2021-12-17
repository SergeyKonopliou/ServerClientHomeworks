package exception;

public class ServiseException extends Exception {

	private static final long serialVersionUID = -5566944608394995655L;


	private Exception except;

	public ServiseException(String msg) {
        super(msg);
    }
	
	public ServiseException(String msg, Exception e) {
		super(msg);
		except = e;
	}

	public Exception getExcept() {
		return except;
	}
}
