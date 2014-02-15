
package se.tanke.exjobb.prevayler.cmd;

import java.util.Date;

import org.prevayler.Transaction;

import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;

/**
 * Creates and stores a new publication in the library.
 * 
 * @author tobias
 */
public class CreateCategory implements Transaction<Library> {
    
	private static final long serialVersionUID = 4705290859535418755L;
	private final CategoryInfo categoryInfo;
    
    public CreateCategory(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    @Override
    public void executeOn(final Library library, final Date date) {
        if (library.getAllItems().contains(categoryInfo.toKey())) {
            throw new IllegalStateException("Category with key " + categoryInfo.toKey() + " already exists");
        }
        library.getAllItems().add(new Category(categoryInfo));
    }    
}
