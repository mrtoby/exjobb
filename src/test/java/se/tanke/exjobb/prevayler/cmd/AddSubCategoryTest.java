package se.tanke.exjobb.prevayler.cmd;

import static se.tanke.exjobb.util.ExjobbAssert.assertContains;
import static se.tanke.exjobb.util.ExjobbAssert.assertNumberOfCategories;

import org.junit.Test;

import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.util.AbstractPrevaylerTest;

public class AddSubCategoryTest extends AbstractPrevaylerTest {
    
    @Test
    public void addToRootCategory() {
        CategoryInfo ci = createCategory("c1", "Category");
        
        addSubCategory(Library.ROOT_CATEGORY_KEY, ci.toKey());
        
        assertNumberOfCategories(getAllItems(), 1);
        assertContains(getRoot(), ci.toKey());
        assertNumberOfCategories(getRoot(), 1);
    }
    
    @Test
    public void addToNewCategory() {
        CategoryInfo c1 = createCategory("c1", "Category");
        CategoryInfo c2 = createCategory("c2", "Category");
        
        addSubCategory(c1.toKey(), c2.toKey());
        
        assertContains(toCategory(c1), c2.toKey());
        assertNumberOfCategories(getAllItems(), 2);
        assertNumberOfCategories(toCategory(c1), 1);
        assertNumberOfCategories(toCategory(c2), 0);
    }
}
