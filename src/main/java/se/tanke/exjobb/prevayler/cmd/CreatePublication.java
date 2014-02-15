
package se.tanke.exjobb.prevayler.cmd;

import java.util.Date;

import org.prevayler.Transaction;

import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.prevayler.model.PublicationInfo;

/**
 * Creates and stores a new publication in the library.
 * 
 * @author tobias
 */
public class CreatePublication implements Transaction<Library> {
    
	private static final long serialVersionUID = 7352829784855028602L;
	private final PublicationInfo publicationInfo;
    
    public CreatePublication(PublicationInfo publicationInfo) {
        this.publicationInfo = publicationInfo;
    }

    @Override
    public void executeOn(final Library library, final Date date) {
        if (library.getAllItems().contains(publicationInfo.toKey())) {
            throw new IllegalStateException("Publication with key " + publicationInfo.toKey() + " already exists");
        }
        library.getAllItems().add(new Publication(publicationInfo));
    }    
}
