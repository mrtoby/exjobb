package se.tanke.exjobb.util;

public class NoSuchItemException extends RuntimeException {

	private static final long serialVersionUID = 4804240135255310360L;

	public NoSuchItemException(final String message) {
		super(message);
	}
	
}
