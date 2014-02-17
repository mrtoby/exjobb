package se.tanke.exjobb.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void nullShouldMatchAnyString() {
		assertTrue(StringUtils.matches(null,  "whatever"));
		assertTrue(StringUtils.matches(null,  "something else"));
	}
	
	@Test
	public void emptyStringShouldMatchAnyString() {
		assertTrue(StringUtils.matches("",  "whatever"));
		assertTrue(StringUtils.matches("",  "something else"));
	}
	
	@Test
	public void substringShouldMatch() {
		assertTrue(StringUtils.matches("foo", "foobar"));
		assertTrue(StringUtils.matches("ob", "foobar"));
		assertTrue(StringUtils.matches("z", "gazonk"));
		assertTrue(StringUtils.matches("onk", "gazonk"));
		assertTrue(StringUtils.matches("everything", "everything"));
	}
	
	@Test
	public void nonSubstringShouldNotMatch() {
		assertFalse(StringUtils.matches("foobar", "foo"));
		assertFalse(StringUtils.matches("gazonk", "foobar"));
		assertFalse(StringUtils.matches("ooz", "foobar"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void matchesShouldThrowIfTheValueIsNull() {
		StringUtils.matches("anything", null);
	}
	
	@Test
	public void onlyNullOrEmptyStringShouldMatchAnEmptyString() {
		assertTrue(StringUtils.matches(null, ""));
		assertTrue(StringUtils.matches("", ""));
		assertFalse(StringUtils.matches("whatever", ""));
	}
}
