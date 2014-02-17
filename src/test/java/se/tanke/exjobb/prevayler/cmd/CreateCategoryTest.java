package se.tanke.exjobb.prevayler.cmd;

import static se.tanke.exjobb.util.ExjobbAssert.assertContains;
import static se.tanke.exjobb.util.ExjobbAssert.assertNotContains;
import static se.tanke.exjobb.util.ExjobbAssert.assertNumberOfCategories;

import org.junit.Test;

import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.util.AbstractPrevaylerTest;

 public class CreateCategoryTest extends AbstractPrevaylerTest {
 
    @Test
    public void createSingleCategory() {
        CategoryInfo ci = commandInvoker.createCategory("c1", "Category name");
        
        assertContains(getAllItems(), ci.toKey());
        assertNumberOfCategories(getAllItems(), 1);
    }
    
    @Test
    public void newCategoryNotAddedToRoot() {
        CategoryInfo ci = commandInvoker.createCategory("c1", "Category name");
        
        assertNotContains(getRoot(), ci.toKey());
        assertNumberOfCategories(getRoot(), 0);
    }
    
    @Test
    public void createTwoCategories() {
        CategoryInfo c1 = commandInvoker.createCategory("c1", "Category name");
        CategoryInfo c2 = commandInvoker.createCategory("c2", "Category name");
        
        assertContains(getAllItems(), c1.toKey());
        assertContains(getAllItems(), c2.toKey());
        assertNumberOfCategories(getAllItems(), 2);
    }
    
    @Test(expected = IllegalStateException.class)
    public void createCategoryWithSameKeyTwice() {
    	commandInvoker.createCategory("c1", "Category name");
    	commandInvoker.createCategory("c1", "Category name");
    }   
}
