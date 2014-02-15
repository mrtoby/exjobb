package se.tanke.exjobb.util;

public class ISBN extends AbstractUnaryKey<Long> {

	private static final long serialVersionUID = -1810382824278539835L;

	private ISBN(final Long number) {
		super(number);
	}
	
	public Long getNumber() {
		return getKeyValue();
	}
	
	public static ISBN valueOf(final String number) {
		// TODO Could add validation and conversion between 10-digits and 13-digits
		return new ISBN(toNumber(number));
	}
	
	private static long toNumber(String number) {
		return Long.parseLong(number.replaceAll("^[0-9]", ""));
	}
}
