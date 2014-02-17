package se.tanke.exjobb.prevayler.model;

import java.io.Serializable;

import se.tanke.exjobb.util.ISBN;

/**
 * This class represent a simple library for publications.
 *
 * @author tobias
 */
public class Library implements Serializable {
    
	private static final long serialVersionUID = 999305703757559433L;
	public static final String ROOT_CATEGORY_NAME = "Root of visible categories";
    public static final String ROOT_CATEGORY_SHORTNAME = "---root---";
    public static final Category.Key ROOT_CATEGORY_KEY = new Category.Key(ROOT_CATEGORY_SHORTNAME);
    
    public static final String ALL_ITEMS_CATEGORY_NAME = "All publications and categories";
    public static final String ALL_ITEMS_SHORTNAME = "---all-items---";
    public static final Category.Key ALL_ITEMS_CATEGORY_KEY = new Category.Key(ALL_ITEMS_SHORTNAME);

    private final Category rootCategory;
    private final Category allItemsCategory;
    
    public Library() {
        rootCategory = new Category(ROOT_CATEGORY_SHORTNAME);
        rootCategory.getInfo().setName(ROOT_CATEGORY_NAME);
        
        allItemsCategory = new Category(ALL_ITEMS_SHORTNAME);
        allItemsCategory.getInfo().setName(ALL_ITEMS_CATEGORY_NAME);
    }
    
    public Category getRoot() {
        return rootCategory;
    }
    
    public Category getAllItems() {
        return allItemsCategory;
    }

    public Category get(final Category.Key k) {
        if (k.equals(ROOT_CATEGORY_KEY)) {
            return rootCategory;
        }
        return getAllItems().get(k);
    }

    public Publication get(final ISBN isbn) {
        return getAllItems().get(isbn);
    }
}
