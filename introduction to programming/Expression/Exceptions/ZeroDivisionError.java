package expression.exceptions;

public class ZeroDivisionError extends EvaluationError {

	public ZeroDivisionError() {
		this("division by zero");
	}

	public ZeroDivisionError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ZeroDivisionError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ZeroDivisionError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ZeroDivisionError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
