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
        PublicationInfo pi = commandInvoker.createPublication(isbn1, "Publication name");
        
        assertContains(getAllItems(), pi.toKey());
        assertNumberOfPublications(getAllItems(), 1);
    }
    
    @Test
    public void newPublicationNotAddedToRoot() {
        PublicationInfo pi = commandInvoker.createPublication(isbn1, "Publication name");
        
        assertNotContains(getRoot(), pi.toKey());
        assertNumberOfPublications(getRoot(), 0);
    }
    
    @Test
    public void createTwoPublications() {
        PublicationInfo p1 = commandInvoker.createPublication(isbn1, "Publication name");
        PublicationInfo p2 = commandInvoker.createPublication(isbn2, "Publication name");
        
        assertContains(getAllItems(), p1.toKey());
        assertContains(getAllItems(), p2.toKey());
        assertNumberOfPublications(getAllItems(), 2);
    }
    
    @Test(expected = IllegalStateException.class)
    public void createCategoryWithSameKeyTwice() {
    	commandInvoker.createPublication(isbn1, "Publication name");
    	commandInvoker.createPublication(isbn1, "Publication name");
        fail("Should have thrown already");
    }
}
