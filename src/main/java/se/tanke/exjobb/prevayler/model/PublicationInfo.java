package se.tanke.exjobb.prevayler.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import se.tanke.exjobb.util.ISBN;

/**
 * A publication in the library.
 *
 * @author tobias
 */
public class PublicationInfo implements Serializable {
 
	private static final long serialVersionUID = 7830870349308168098L;
	private final ISBN isbn;
    private String title = "";
    private String author = "";
    protected final Set<String> keywords = new HashSet<>();
    
    public PublicationInfo(final ISBN isbn) {
        this.isbn = isbn;
    }
    
    protected PublicationInfo(final PublicationInfo template) {
        this(template.getISBN());
        this.title = template.getName();
        this.keywords.addAll(template.keywords);
    }
    
    public ISBN getISBN() {
        return isbn;
    }

    public String getName() {
        return title;
    }

    public void setName(final String name) {
        this.title = name;
    }

    public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void addKeyword(final String keyword) {
        keywords.add(keyword);
    }
    
    public void removeKeyword(final String keyword) {
        keywords.remove(keyword);
    }
    
    public boolean hasKeyword(final String keyword) {
        return keywords.contains(keyword);
    }
    
    public Set<String> getKeywords() {
        return Collections.unmodifiableSet(keywords);
    }

    public ISBN toKey() {
        return getISBN();
    }
}
