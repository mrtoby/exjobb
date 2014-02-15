package se.tanke.exjobb.util;

import org.junit.BeforeClass;

public abstract class AbstractModelTest {

    /** ISBN-numbers to use. */
	protected static ISBN isbn1;
	protected static ISBN isbn2;
	
	@BeforeClass
	public static void setup() {
		isbn1 = ISBN.valueOf("1234567890123");
		isbn2 = ISBN.valueOf("1234567890124");
	}
}
