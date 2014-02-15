package se.tanke.exjobb.prevayler.model;

import java.io.Serializable;

import se.tanke.exjobb.util.ISBN;

/**
 * A publication in the library.
 *
 * @author tobias
 */
public class Publication implements Serializable {
 
	private static final long serialVersionUID = -5011448564213877642L;
	private final PublicationInfo publicationInfo;
    
    public Publication(final ISBN isbn) {
        publicationInfo = new PublicationInfo(isbn);
    }
    
    public Publication(final PublicationInfo publicationInfo) {
        this.publicationInfo = publicationInfo;
    }
    
    public PublicationInfo getInfo() {
        return publicationInfo;
    }
    
    public ISBN getISBN() {
        return publicationInfo.getISBN();
    }

    public ISBN toKey() {
        return publicationInfo.toKey();
    }
}
