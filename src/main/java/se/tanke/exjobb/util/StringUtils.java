package se.tanke.exjobb.util;

/**
 * Misc utils for strings.
 * 
 * @author tobias
 */
public final class StringUtils {

	private StringUtils() {
	}
	
	/**
	 * Simple matching function that search for a substring within some value. Searching
	 * using a null or empty string will be considered a match.
	 * @param searchFor Substring to search for
	 * @param value Value to search
	 * @return <code>true</code> if the value matches
	 */
	public static boolean matches(String searchFor, String value) {
		if (value == null) {
			throw new IllegalArgumentException("The value may not be null");
		}
		if (searchFor == null || searchFor.isEmpty()) {
			return true;
		}
		return value.toLowerCase().contains(searchFor.toLowerCase());
	}
	
}
