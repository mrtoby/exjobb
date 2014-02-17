package se.tanke.exjobb.testutil;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.prevayler.model.PublicationInfo;
import se.tanke.exjobb.util.ISBN;
import se.tanke.exjobb.util.PrevaylerCommandInvoker;

public abstract class AbstractPrevaylerTest extends AbstractModelTest {

    /** The prevalent system. */
    protected Prevayler<Library> prevayler;
    protected PrevaylerCommandInvoker commandInvoker;

    @Before
    public void setupTest() {
        prevayler = PrevaylerFactory.createTransientPrevayler(new Library());
        commandInvoker = new PrevaylerCommandInvoker(prevayler);
    }
    
    @After
    public void cleanup() throws IOException {
        prevayler.close();
        prevayler = null;
    }

    protected Library getLibrary() {
        return prevayler.prevalentSystem();
    }

    protected Category getRoot() {
        return prevayler.prevalentSystem().getRoot();
    }
    
    protected Category getAllItems() {
        return prevayler.prevalentSystem().getAllItems();
    }
    
    protected Category toCategory(final CategoryInfo ci) {
        return toCategory(ci.toKey());
    }

    protected Category toCategory(final Category.Key c) {
        return getLibrary().getAllItems().get(c);
    }

    protected Publication toPublication(final PublicationInfo pi) {
        return toPublication(pi.toKey());
    }

    protected Publication toPublication(final ISBN isbn) {
        return getLibrary().getAllItems().get(isbn);
    }
}
