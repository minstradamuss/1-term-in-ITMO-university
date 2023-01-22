package expression.exceptions;

public class ArgumentRangeError extends EvaluationError {

	public ArgumentRangeError() {
		this("argument out of range");
	}

	public ArgumentRangeError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ArgumentRangeError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ArgumentRangeError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ArgumentRangeError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
