package expression.exceptions;

public class ConstOutOfRangeError extends ParserError {

	public ConstOutOfRangeError() {
		this("const out of range");
	}
	
	public ConstOutOfRangeError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConstOutOfRangeError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConstOutOfRangeError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConstOutOfRangeError(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
