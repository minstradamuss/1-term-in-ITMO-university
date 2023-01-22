package expression.exceptions;

public class OverflowError extends EvaluationError {

	public OverflowError() {
		this("overflow");
	}

	public OverflowError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OverflowError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public OverflowError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OverflowError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
