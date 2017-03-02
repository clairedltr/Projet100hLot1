package exceptions;

public class MediaRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 9025730660535871559L;
	
	
	public MediaRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
