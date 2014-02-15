
package se.tanke.exjobb.prevayler.cmd;

import java.util.Date;

import org.prevayler.Transaction;

import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.Library;

/**
 * Command for adding a sub category to a category.
 *
 * @author tobias
 */
public class AddSubCategory implements Transaction<Library> {

	private static final long serialVersionUID = -144748565100007111L;
	private final Category.Key subCategoryKey;
    private final Category.Key categoryKey;
  
    public AddSubCategory(
            final Category.Key subCategoryKey, 
            final Category.Key categoryKey) {
        this.subCategoryKey = subCategoryKey;
        this.categoryKey = categoryKey;
    }

    @Override
    public void executeOn(final Library library, final Date date) {
        final Category c = library.get(categoryKey);
        final Category subCategory = library.get(subCategoryKey);
        c.add(subCategory);
    }
}
