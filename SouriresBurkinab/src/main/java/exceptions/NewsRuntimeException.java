package exceptions;

public class NewsRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 9025730660535871559L;
	
	
	public NewsRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}

