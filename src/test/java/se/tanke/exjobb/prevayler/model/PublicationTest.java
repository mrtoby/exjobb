package se.tanke.exjobb.prevayler.model;

import org.junit.Assert;
import org.junit.Test;

import se.tanke.exjobb.testutil.AbstractModelTest;
import se.tanke.exjobb.testutil.ExjobbAssert;

public class PublicationTest extends AbstractModelTest {
    
    @Test
    public void isSerializable() {
        ExjobbAssert.assertSerializable(Publication.class);
    }    

    @Test
    public void constructorArgumentIsISBN() {
        final Publication sut = new Publication(isbn1);
        Assert.assertEquals(isbn1, sut.getInfo().getISBN());
    }

    @Test
    public void keyValueIsISBN() {
        final Publication sut = new Publication(isbn1);
        Assert.assertEquals(isbn1, sut.toKey());
    }
}
