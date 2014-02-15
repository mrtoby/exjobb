package se.tanke.exjobb.prevayler.model;

import java.io.Serializable;

/**
 * This object carry information about a category.
 *
 * @see Category
 * @author tobias
 */
public class CategoryInfo implements Serializable {

	private static final long serialVersionUID = -7959999664710805444L;
	private final String shortname;
    private String name = "";

    public CategoryInfo(final String shortname) {
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }
    
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Category.Key toKey() {
        return new Category.Key(shortname);
    }
}
