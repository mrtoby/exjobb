package se.tanke.exjobb.prevayler.cmd;

import static org.junit.Assert.fail;
import static se.tanke.exjobb.util.ExjobbAssert.assertContains;
import static se.tanke.exjobb.util.ExjobbAssert.assertNotContains;
import static se.tanke.exjobb.util.ExjobbAssert.assertNumberOfPublications;

import org.junit.Test;

import se.tanke.exjobb.prevayler.model.PublicationInfo;
import se.tanke.exjobb.util.AbstractPrevaylerTest;

public class CreatePublicationTest extends AbstractPrevaylerTest {

    @Test
    public void createSinglePublication() {
        PublicationInfo pi = createPublication(isbn1, "Publication name");
        
        assertContains(getAllItems(), pi.toKey());
        assertNumberOfPublications(getAllItems(), 1);
    }
    
    @Test
    public void newPublicationNotAddedToRoot() {
        PublicationInfo pi = createPublication(isbn1, "Publication name");
        
        assertNotContains(getRoot(), pi.toKey());
        assertNumberOfPublications(getRoot(), 0);
    }
    
    @Test
    public void createTwoPublications() {
        PublicationInfo p1 = createPublication(isbn1, "Publication name");
        PublicationInfo p2 = createPublication(isbn2, "Publication name");
        
        assertContains(getAllItems(), p1.toKey());
        assertContains(getAllItems(), p2.toKey());
        assertNumberOfPublications(getAllItems(), 2);
    }
    
    @Test(expected = IllegalStateException.class)
    public void createCategoryWithSameKeyTwice() {
        createPublication(isbn1, "Publication name");
        createPublication(isbn1, "Publication name");
        fail("Should have thrown already");
    }
}
