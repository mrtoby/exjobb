package se.tanke.exjobb.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ISBNTest {

	@Test
	public void anyExtraDashesShouldBeRemoved() {
		assertISBNIsCorrectlyParsed("97-891-7461-235-6", "9789174612356");
	}

	@Test
	public void theLetterXShouldBeAcceptedFor10DigitNumbers() {
		assertISBNIsCorrectlyParsed("0-8044-2957-X", "9780804429573");
	}

	@Test
	public void some10DigitISBNNumbersAreParsedCorrectly() {
		assertISBNIsCorrectlyParsed("99921-58-10-7", "9789992158104");
		assertISBNIsCorrectlyParsed("9971-5-0210-0", "9789971502102");
	}

	@Test
	public void some13DigitISBNNumbersAreParsedCorrectly() {
		assertISBNIsCorrectlyParsed("9789174612356", "9789174612356");
		assertISBNIsCorrectlyParsed("9789185849888", "9789185849888");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrownFor10DigitNumberWithBadChecksum() {
		ISBN.valueOf("0-8044-2957-1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowFor13DigitNumberWithBadChecksum() {
		ISBN.valueOf("9789185849889");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrownFor9DigitNumber() {
		ISBN.valueOf("0-8044-2957");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrownFor12DigitNumber() {
		ISBN.valueOf("0-8044-2957-132");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrownFor14DigitNumber() {
		ISBN.valueOf("0-8044-2957-13232");
	}
	
	private static void assertISBNIsCorrectlyParsed(String input, String expectedOutput) {
		ISBN result = ISBN.valueOf(input);
		assertEquals(expectedOutput, result.getNumber());
	}
}
