package expression.exceptions;

public class ValueMissingError extends ParserError {

	public ValueMissingError() {
		this("value missing");
	}

	public ValueMissingError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValueMissingError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ValueMissingError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValueMissingError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
