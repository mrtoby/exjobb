package se.tanke.exjobb.util;

import java.io.Serializable;

import org.junit.Assert;

import se.tanke.exjobb.prevayler.model.Category;

/**
 *
 * @author tobias
 */
public final class ExjobbAssert {

    private ExjobbAssert() {
    }
    
    public static void assertSerializable(final Class<?> c) {
        Assert.assertTrue("Should be serializable", c instanceof Serializable);
    }

    public static void assertContains(final Category c, final Category.Key ck) {
        Assert.assertTrue(
                "Category does not contain sub category with key: " + ck,
                c.contains(ck));
    }

    public static void assertContains(final Category c, final ISBN isbn) {
        Assert.assertTrue(
                "Category does not contain publication with ISBN: " + isbn,
                c.contains(isbn));
    }

    public static void assertNotContains(final Category c, final Category.Key ck) {
        Assert.assertFalse(
                "Category does contain sub category with key: " + ck,
                c.contains(ck));
    }

    public static void assertNotContains(final Category c, final ISBN isbn) {
        Assert.assertFalse(
                "Category does contain publication with ISBN: " + isbn,
                c.contains(isbn));
    }

    public static void assertNumberOfCategories(final Category c, final int n) {
        Assert.assertEquals(n, c.getCategories().size());
    }

    public static void assertNumberOfPublications(final Category c, final int n) {
        Assert.assertEquals(n, c.getPublications().size());
    }
}
