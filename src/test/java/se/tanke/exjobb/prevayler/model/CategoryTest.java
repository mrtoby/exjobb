package se.tanke.exjobb.prevayler.model;

import static org.junit.Assert.assertEquals;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertContains;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertNotContains;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertNumberOfCategories;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertNumberOfPublications;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertSerializable;

import org.junit.Test;

import se.tanke.exjobb.exception.DuplicateItemException;
import se.tanke.exjobb.exception.NoSuchItemException;
import se.tanke.exjobb.testutil.AbstractModelTest;

public class CategoryTest extends AbstractModelTest {
    
    @Test
    public void isSerializable() {
        assertSerializable(Category.class);
    }
    
    @Test
    public void constructorArgumentIsShortname() {
        final String shortname = "SOMETHING";
        final Category sut = new Category(shortname);
        assertEquals(shortname, sut.getInfo().getShortname());
    }

    @Test
    public void keyIsShortname() {
        final String shortname = "SOMETHING";
        final Category sut = new Category(shortname);
        assertEquals(shortname, sut.toKey().getKeyValue());
    }

    @Test
    public void newIntanceHasNoCategoriesNorPublications() {
        final Category.Key ck1 = new Category.Key("c1");        

        final Category sut = new Category("sut");
        
        assertNumberOfCategories(sut, 0);
        assertNumberOfPublications(sut, 0);
        assertNotContains(sut, ck1);
        assertNotContains(sut, isbn1);
    }

    @Test
    public void addSingleSubcategory() {
        final Category c1 = new Category("c1");
        final Category sut = new Category("sut");
        
        sut.add(c1);
        
        assertContains(sut, c1.toKey());
        assertNumberOfCategories(sut, 1);
    }
    
    @Test
    public void addSameSubCategoryTwiceToSameCategory() {
        final Category c1 = new Category("c1");
        final Category sut = new Category("sut");
        
        sut.add(c1);
        sut.add(c1);
        
        assertContains(sut, c1.toKey());
        assertNumberOfCategories(sut, 1);
    }
    
    @Test
    public void addTwoSubCategories() {
        final Category c1 = new Category("c1");
        final Category c2 = new Category("c2");
        final Category sut = new Category("sut");
        
        sut.add(c1);
        sut.add(c2);
        
        assertContains(sut, c1.toKey());
        assertContains(sut, c2.toKey());
        assertNumberOfCategories(sut, 2);
    }
    
    @Test
    public void addingDeepStructuresShouldWork() {
        final Category c1 = new Category("c1");
        final Category c2 = new Category("c2");
        final Category c3 = new Category("c3");
        final Category c4 = new Category("c4");
        final Category c5 = new Category("c5");
        
        c2.add(c3);
        c3.add(c4);
        c4.add(c5);

        c1.add(c2);
    }
    
    @Test(expected = DuplicateItemException.class)
    public void addingTwoDifferentSubCategoriesWithSameKeyShouldThrow() {
        final Category c1 = new Category("c1");
        final Category c1Prim = new Category("c1");
        final Category sut = new Category("sut");
        
        sut.add(c1);
        sut.add(c1Prim);
    }

    @Test
    public void removeSubcategory() {
        final Category c1 = new Category("c1");
        final Category c2 = new Category("c2");
        final Category sut = new Category("sut");        
        sut.add(c1);
        sut.add(c2);
        
        sut.remove(c1.toKey());
        
        assertNotContains(sut, c1.toKey());
        assertContains(sut, c2.toKey());
        assertNumberOfCategories(sut, 1);
    }
    
    @Test
    public void addCategoryToDifferentCategories() {
        final Category c1 = new Category("c1");
        final Category c2 = new Category("c2");
        final Category c3 = new Category("c3");
        
        c1.add(c3);
        c2.add(c3);
        
        assertContains(c1, c3.toKey());
        assertContains(c2, c3.toKey());
        assertEquals(c1.get(c3.toKey()), c2.get(c3.toKey()));
    }
    
    @Test(expected = IllegalStateException.class)
    public void addingCircularCategoryShouldThrow() {
        final Category c1 = new Category("c1");
        final Category c2 = new Category("c2");
        final Category c3 = new Category("c3");
        final Category c4 = new Category("c4");
        
        c2.add(c3);
        c3.add(c4);
        c4.add(c1);

        c1.add(c2);
    }
    
    @Test
    public void addSinglePublication() {
        final Publication p1 = new Publication(isbn1);
        final Category sut = new Category("sut");
        
        sut.add(p1);
        
        assertContains(sut, p1.toKey());
        assertNumberOfPublications(sut, 1);
    }

    @Test
    public void addSamePublicationTwiceToSameCategory() {
        final Publication p1 = new Publication(isbn1);
        final Category sut = new Category("sut");

        sut.add(p1);
        sut.add(p1);
        
        assertContains(sut, p1.toKey());
        assertNumberOfPublications(sut, 1);
    }
    
    @Test
    public void addTwoPublications() {
        final Publication p1 = new Publication(isbn1);
        final Publication p2 = new Publication(isbn2);
        final Category sut = new Category("sut");

        sut.add(p1);
        sut.add(p2);
        
        assertContains(sut, p1.toKey());
        assertContains(sut, p2.toKey());
        assertNumberOfPublications(sut, 2);
    }
    
    @Test(expected = DuplicateItemException.class)
    public void addingTwoDifferentPublicationsWithSameKeyShouldThrow() {
        final Publication p1 = new Publication(isbn1);
        final Publication p1Prim = new Publication(isbn1);
        final Category sut = new Category("sut");
        
        sut.add(p1);
        sut.add(p1Prim);
    }

    @Test(expected = NoSuchItemException.class)
    public void getUnknownPublicationShouldThrow() {
    	final Category sut = new Category("sut");
    	
    	sut.get(isbn1);
    }
    
    @Test(expected = NoSuchItemException.class) 
    public void getUnknownCategoryShouldThrow() {
    	final Category sut = new Category("sut");
    	
    	sut.get(new Category.Key("shortnameX"));
    }
    
    @Test
    public void removePublication() {
        final Publication p1 = new Publication(isbn1);
        final Publication p2 = new Publication(isbn2);        
        final Category sut = new Category("sut");        
        sut.add(p1);
        sut.add(p2);
        
        sut.remove(p1.toKey());
        
        assertNotContains(sut, p1.toKey());
        assertContains(sut, p2.toKey());
        assertNumberOfPublications(sut, 1);
        assertNumberOfCategories(sut, 0);
    }

    @Test
    public void addPublicationToTwoDifferentCategories() {
        final Publication p = new Publication(isbn1);
        final Category c1 = new Category("c1");
        final Category c2 = new Category("c2");
        
        c1.add(p);
        c2.add(p);

        assertContains(c1, p.toKey());
        assertContains(c2, p.toKey());
        assertNumberOfPublications(c1, 1);
        assertNumberOfPublications(c2, 1);
        assertEquals(c1.get(p.toKey()), c2.get(p.toKey()));
    }
}
