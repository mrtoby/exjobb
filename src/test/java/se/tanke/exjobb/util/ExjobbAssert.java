package se.tanke.exjobb.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import se.tanke.exjobb.prevayler.model.Category;

/**
 *
 * @author tobias
 */
public final class ExjobbAssert {

    private ExjobbAssert() {
    }
    
    public static void assertSerializable(final Class<?> c) {
        assertTrue("Should be serializable", c instanceof Serializable);
    }

    public static void assertContains(final Category c, final Category.Key ck) {
        assertTrue(
                "Category does not contain sub category with key: " + ck,
                c.contains(ck));
    }

    public static void assertContains(final Category c, final ISBN isbn) {
        assertTrue(
                "Category does not contain publication with ISBN: " + isbn,
                c.contains(isbn));
    }

    public static void assertNotContains(final Category c, final Category.Key ck) {
        assertFalse(
                "Category does contain sub category with key: " + ck,
                c.contains(ck));
    }

    public static void assertNotContains(final Category c, final ISBN isbn) {
        assertFalse(
                "Category does contain publication with ISBN: " + isbn,
                c.contains(isbn));
    }

    public static void assertNumberOfCategories(final Category c, final int n) {
        assertEquals(n, c.getCategories().size());
    }

    public static void assertNumberOfPublications(final Category c, final int n) {
        assertEquals(n, c.getPublications().size());
    }
}
