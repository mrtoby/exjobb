package se.tanke.exjobb.prevayler.cmd;

import static se.tanke.exjobb.testutil.ExjobbAssert.assertNumberOfCategories;
import static se.tanke.exjobb.testutil.ExjobbAssert.assertNumberOfPublications;

import org.junit.Test;

import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.PublicationInfo;
import se.tanke.exjobb.testutil.AbstractPrevaylerTest;

public class AddPublicationTest extends AbstractPrevaylerTest {
 
    @Test
    public void addPublicationToRootCategory() {
        PublicationInfo pi = commandInvoker.createPublication(isbn1, "Publication name");
        
        commandInvoker.addPublication(pi.toKey(), Library.ROOT_CATEGORY_KEY);
        
        assertNumberOfPublications(getRoot(), 1);
        assertNumberOfPublications(getAllItems(), 1);
    }
    
    @Test
    public void addPublicationToNewCategory() {
        CategoryInfo ci = commandInvoker.createCategory("c1", "Category name");
        PublicationInfo pi = commandInvoker.createPublication(isbn1, "Publication name");
        
        commandInvoker.addPublication(pi.toKey(), ci.toKey());
        
        assertNumberOfPublications(getRoot(), 0);
        assertNumberOfPublications(toCategory(ci), 1);
        assertNumberOfPublications(getAllItems(), 1);
        assertNumberOfCategories(getAllItems(), 1);
    }
    
    @Test
    public void addPublicationTwiceToSameCategory() {
        PublicationInfo pi = commandInvoker.createPublication(isbn1, "Publication name");
        
        commandInvoker.addPublication(pi.toKey(), Library.ROOT_CATEGORY_KEY);
        commandInvoker.addPublication(pi.toKey(), Library.ROOT_CATEGORY_KEY);
        
        assertNumberOfPublications(getRoot(), 1);
        assertNumberOfPublications(getAllItems(), 1);
    }

    @Test
    public void addTwoPublications() {
        PublicationInfo p1 = commandInvoker.createPublication(isbn1, "Publication name");
        PublicationInfo p2 = commandInvoker.createPublication(isbn2, "Publication name");
        
        commandInvoker.addPublication(p1.toKey(), Library.ROOT_CATEGORY_KEY);
        commandInvoker.addPublication(p2.toKey(), Library.ROOT_CATEGORY_KEY);
        
        assertNumberOfPublications(getRoot(), 2);
        assertNumberOfPublications(getAllItems(), 2);
    }    
}
