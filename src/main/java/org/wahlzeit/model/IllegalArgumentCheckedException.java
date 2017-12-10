package org.wahlzeit.model;

public class IllegalArgumentCheckedException extends Exception {

	private static final long serialVersionUID = -3256399267368358132L;
	
	IllegalArgumentCheckedException() {
		super();
	}
	
	IllegalArgumentCheckedException(String message) {
		super(message);
	}
	
	IllegalArgumentCheckedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	protected IllegalArgumentCheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	IllegalArgumentCheckedException(Throwable cause) {
		super(cause);
	}

}
