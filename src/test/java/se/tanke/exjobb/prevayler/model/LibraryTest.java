package se.tanke.exjobb.prevayler.model;

import static org.junit.Assert.assertEquals;
import static se.tanke.exjobb.util.ExjobbAssert.assertNotContains;
import static se.tanke.exjobb.util.ExjobbAssert.assertNumberOfCategories;
import static se.tanke.exjobb.util.ExjobbAssert.assertNumberOfPublications;
import static se.tanke.exjobb.util.ExjobbAssert.assertSerializable;

import org.junit.Test;

import se.tanke.exjobb.util.AbstractModelTest;

public class LibraryTest extends AbstractModelTest {

    @Test
    public void isSerializable() {
        assertSerializable(Library.class);
    }
    
    @Test
    public void newLibraryHasNoCategoriesNorPublications() {
        final Category.Key ck1 = new Category.Key("c1");
        final Library sut = new Library();  
        
        // No acting
        
        assertNotContains(sut.getAllItems(), ck1);
        assertNotContains(sut.getAllItems(), isbn1);
        assertNumberOfCategories(sut.getAllItems(), 0);
        assertNumberOfPublications(sut.getAllItems(), 0);
    }
    
    @Test
    public void addedPublicationReturnedByGet() {
        final Publication p = new Publication(isbn1);
        final Library sut = new Library();
        
        sut.getAllItems().add(p);
        
        assertEquals(p, sut.get(p.toKey()));
    }

    @Test
    public void addedCategoryReturnedByGet() {
        final Category c = new Category("shortname");
        final Library sut = new Library();
        
        sut.getAllItems().add(c);
        
        assertEquals(c, sut.get(c.toKey()));
    }
}
