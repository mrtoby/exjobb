package se.tanke.exjobb.exception;

public class DuplicateItemException extends RuntimeException {

	private static final long serialVersionUID = 4804240135255310360L;

	public DuplicateItemException(final String message) {
		super(message);
	}

}
