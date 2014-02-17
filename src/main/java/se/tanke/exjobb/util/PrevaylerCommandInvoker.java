package se.tanke.exjobb.util;

import org.prevayler.Prevayler;

import se.tanke.exjobb.prevayler.cmd.AddPublication;
import se.tanke.exjobb.prevayler.cmd.AddSubCategory;
import se.tanke.exjobb.prevayler.cmd.CreateCategory;
import se.tanke.exjobb.prevayler.cmd.CreatePublication;
import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.PublicationInfo;

public class PrevaylerCommandInvoker {

	private Prevayler<Library> prevayler;

	public PrevaylerCommandInvoker(Prevayler<Library> prevayler) {
		this.prevayler = prevayler;
	}

    public CategoryInfo createCategory(final String shortname, final String name) {
        final CategoryInfo ci = new CategoryInfo(shortname);
        ci.setName(name);
        prevayler.execute(new CreateCategory(ci));
        return ci;
    }

    public void addSubCategory(final Category.Key c, final Category.Key sc) {
        prevayler.execute(new AddSubCategory(sc, c));
    }
    
    public PublicationInfo createPublication(final ISBN isbn, final String title,
    		final String... keywords) {
        final PublicationInfo pi = new PublicationInfo(isbn);
        pi.setTitle(title);
        for (String keyword : keywords) {
            pi.addKeyword(keyword);
        }
        prevayler.execute(new CreatePublication(pi));
        return pi;
    }
    
    public void addPublication(final ISBN isbn, final Category.Key c) {
        prevayler.execute(new AddPublication(isbn, c));
    }
}