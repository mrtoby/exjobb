package se.tanke.exjobb.prevayler.model;

import static org.junit.Assert.assertEquals;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertSerializable;

import org.junit.Test;

import se.tanke.exjobb.prevayler.model.CategoryInfo;

public class CategoryInfoTest {
    
    @Test
    public void isSerializable() {
        assertSerializable(CategoryInfo.class);
    }
 
    @Test
    public void constructorArgumentIsShortname() {
        final String shortname = "SOMETHING";
        final CategoryInfo sut = new CategoryInfo(shortname);
        assertEquals(shortname, sut.getShortname());
    }

    @Test
    public void keyIsShortname() {
        final String shortname = "SOMETHING";
        final CategoryInfo sut = new CategoryInfo(shortname);
        assertEquals(shortname, sut.toKey().getKeyValue());
    }
}
