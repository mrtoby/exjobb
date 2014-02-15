
package se.tanke.exjobb.prevayler.cmd;

import java.util.Date;

import org.prevayler.Transaction;

import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.util.ISBN;

/**
 * Command for adding a publication to a category.
 *
 * @author tobias
 */
public class AddPublication implements Transaction<Library> {
    
	private static final long serialVersionUID = 384605599447306505L;
	private final ISBN isbn;
    private final Category.Key categoryKey;
  
    public AddPublication(
            final ISBN isbn, 
            final Category.Key c) {
        this.isbn = isbn;
        this.categoryKey = c;
    }

    @Override
    public void executeOn(final Library library, final Date date) {
        final Category c = library.get(categoryKey);
        final Publication p = library.get(isbn);
        c.add(p);
    }
}
