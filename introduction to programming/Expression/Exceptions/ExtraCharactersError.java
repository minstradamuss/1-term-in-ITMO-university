package expression.exceptions;

public class ExtraCharactersError extends ParserError {

	public ExtraCharactersError() {
		this("extra characters");
	}

	public ExtraCharactersError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExtraCharactersError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ExtraCharactersError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExtraCharactersError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
