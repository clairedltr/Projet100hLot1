package exceptions;

public class ArticleRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 9025730660535871559L;
	
	
	public ArticleRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
