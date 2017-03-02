package exceptions;

public class AbonneRuntimeException extends RuntimeException {
private static final long serialVersionUID = 9025730660535871559L;
	
	
	public AbonneRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
