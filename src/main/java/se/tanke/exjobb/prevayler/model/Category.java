package se.tanke.exjobb.prevayler.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.tanke.exjobb.util.AbstractUnaryKey;
import se.tanke.exjobb.util.DuplicateItemException;
import se.tanke.exjobb.util.ISBN;
import se.tanke.exjobb.util.NoSuchItemException;

/**
 * A category is a grouping of publications that is hierarchical. 
 * 
 * A category may appear on multiple locations in the category structure. The 
 * category structure may not be circular.
 * 
 * A publication may appear in several categories if that makes sense.
 *
 * @author tobias
 */
public class Category implements Serializable {
    
	private static final long serialVersionUID = 489959898853668231L;
	private final Map<Category.Key, Category> subcategories = new HashMap<>();
    private final Map<ISBN, Publication> publications = new HashMap<>();
    private final CategoryInfo categoryInfo;
    
    public Category(final String shortname) {
        categoryInfo = new CategoryInfo(shortname);
    }

    public Category(final CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
    
    public CategoryInfo getInfo() {
        return categoryInfo;
    }

    public void add(final Category c) {
        if (c.containsDeep(this.toKey())) {
            throw new IllegalStateException("Adding " + c
                    + " to " + this
                    + " would create a circular structure");
        }
        final Category existing = subcategories.get(c.toKey());
        if (existing == null) {
            subcategories.put(c.toKey(), c);
        } else if (existing != c) {
            throw new DuplicateItemException("Another category with key " 
                    + c.toKey()
                    + " already exists");
        }
    }
    
    public Category get(final Category.Key k) {
        final Category c = subcategories.get(k);
        if (c == null) {
            throw new NoSuchItemException("No category for key: " + k);
        }
        return c;
    }
    
    public void remove(final Category.Key k) {
        subcategories.remove(k);
    }
    
    public Collection<Category> getCategories() {
        return Collections.unmodifiableCollection(subcategories.values());
    }

    public int getNumberOfCategories() {
        return subcategories.size();
    }
    
    public boolean contains(final Category.Key k) {
        return subcategories.containsKey(k);
    }
    
    public boolean containsDeep(final Category.Key k) {
        if (contains(k)) {
            return true;
        }
        for (Category sc : subcategories.values()) {
            if (sc.containsDeep(k)) {
                return true;
            }
        }
        return false;
    }
    
    public void add(final Publication p) {
        final Publication existing = publications.get(p.getISBN());
        if (existing == null) {
            publications.put(p.getISBN(), p);
        } else if (existing != p) {
            throw new DuplicateItemException("Another publication with ISBN " 
                    + p.getISBN()
                    + " already exists");
        }
    }
    
    public Publication get(final ISBN isbn) {
        final Publication p = publications.get(isbn);
        if (p == null) {
            throw new NoSuchItemException("No publication with ISBN: " + isbn);
        }
        return p;
    }
    
    public void remove(final ISBN isbn) {
        publications.remove(isbn);
    }
    
    public Collection<Publication> getPublications() {
        return Collections.unmodifiableCollection(publications.values());
    }
    
    public int getNumberOfPublications() {
        return publications.size();
    }
    
    public boolean isEmpty() {
        return publications.isEmpty();
    }
    
    public boolean contains(final ISBN isbn) {
        return publications.containsKey(isbn);
    }
    
    public Category.Key toKey() {
        return categoryInfo.toKey();
    }
    
    public static class Key extends AbstractUnaryKey<String> {
		private static final long serialVersionUID = -7166018407433038699L;

		public Key(final String shortname) {
            super(shortname);
        }
		
		public static Key valueOf(final String shortname) {
			return new Key(shortname);
		}
    }

	public List<Publication> findPublications(final String title, final String author,
			final String[] keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> findCategories(final String name, final String shortname) {
		// TODO Auto-generated method stub
		return null;
	}
}
