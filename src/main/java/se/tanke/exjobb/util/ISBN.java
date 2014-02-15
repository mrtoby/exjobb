package se.tanke.exjobb.util;

/**
 * This class take care of verifying and parsing ISBN-numbers.
 * Both 10-digit and 13-digit numbers are supported. 10-digit numbers
 * are always converted to 13-digit numbers.
 *
 * @see http://en.wikipedia.org/wiki/International_Standard_Book_Number
 * @see http://www.hahnlibrary.net/libraries/isbncalc.html
 * @author tobias
 */
public class ISBN extends AbstractUnaryKey<String> {

	private static final long serialVersionUID = -1810382824278539835L;

	private ISBN(final String number) {
		super(number);
	}
	
	public String getNumber() {
		return getKeyValue();
	}
	
	public static ISBN valueOf(final String number) {
		final String tidyNumber = normalizeNumber(number);
		if (tidyNumber.length() == 10) {
			return valueOf10DigitISBN(tidyNumber);
		} else if (tidyNumber.length() == 13) {
			return valueOf13DigitISBN(tidyNumber);
		} else {
			throw new IllegalArgumentException("An ISBN number should be 10 or 13 digits: " + tidyNumber);
		}
	}
	
	private static ISBN valueOf10DigitISBN(final String isbn10) {
		validate10DigitISBN(isbn10);
		final String isbn13 = "978" + isbn10;
		final int checksum = calculate13DigitChecksum(isbn13);
		return new ISBN(isbn13.substring(0, 12) + Integer.toString(checksum));
	}

	private static void validate10DigitISBN(final String isbn10) {
		final int checksum = calculate10DigitChecksum(isbn10);
		final int checkdigit = checksum == 10 ? 'x' : '0' + checksum;
		if (isbn10.charAt(9) != checkdigit) {
			throw new IllegalArgumentException("Expected checksum to be " + checksum + ": " + isbn10);
		}
	}

	private static ISBN valueOf13DigitISBN(final String isbn13) {
		validate13DigitISBN(isbn13);
		return new ISBN(isbn13);
	}

	private static void validate13DigitISBN(final String isbn13) {
		final int checksum = calculate13DigitChecksum(isbn13);
		if (isbn13.charAt(12) != ('0' + checksum)) {
			throw new IllegalArgumentException("Expected checksum to be " + checksum + ": " + isbn13);
		}
	}

	private static String normalizeNumber(final String number) {
		return number.replaceAll("[^0-9Xx]", "").replace('X', 'x');
	}

	private static int calculate13DigitChecksum(final String isbn13) {
	    int sum = 0;
	    for (int i = 0; i < 12; i += 2) {
	        sum += (isbn13.charAt(i) - '0');
	    }
	    for (int i = 1; i < 12; i += 2) {
	        sum += (isbn13.charAt(i) - '0') * 3;
	    }
	    return 10 - (sum % 10);
	}
	
	private static int calculate10DigitChecksum(final String isbn10) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += ((isbn10.charAt(i) - '0') * (i + 1));
		}
		return sum % 11;
	}
}
