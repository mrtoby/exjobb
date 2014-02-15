package se.tanke.exjobb.util;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import se.tanke.exjobb.prevayler.cmd.AddPublication;
import se.tanke.exjobb.prevayler.cmd.AddSubCategory;
import se.tanke.exjobb.prevayler.cmd.CreateCategory;
import se.tanke.exjobb.prevayler.cmd.CreatePublication;
import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.prevayler.model.PublicationInfo;

public abstract class AbstractPrevaylerTest extends AbstractModelTest {

    /** The prevalent system. */
    protected Prevayler<Library> prevayler;

    @Before
    public void setupTest() {
        final Library library = new Library();
        prevayler = PrevaylerFactory.createTransientPrevayler(library);
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

    protected CategoryInfo createCategory(final String shortname, final String name) {
        final CategoryInfo ci = new CategoryInfo(shortname);
        ci.setName(name);
        prevayler.execute(new CreateCategory(ci));
        return ci;
    }

    protected void addSubCategory(final Category.Key c, final Category.Key sc) {
        prevayler.execute(new AddSubCategory(sc, c));
    }
    
    protected PublicationInfo createPublication(final ISBN isbn, final String name,
    		final String... keywords) {
        final PublicationInfo pi = new PublicationInfo(isbn);
        pi.setName(name);
        for (String keyword : keywords) {
            pi.addKeyword(keyword);
        }
        prevayler.execute(new CreatePublication(pi));
        return pi;
    }
    
    protected void addPublication(final ISBN isbn, final Category.Key c) {
        prevayler.execute(new AddPublication(isbn, c));
    }
}
