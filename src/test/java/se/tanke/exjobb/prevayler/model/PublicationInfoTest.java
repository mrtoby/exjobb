
package se.tanke.exjobb.prevayler.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static se.tanke.exjobb.util.ExjobbAssert.assertSerializable;

import org.junit.Test;

import se.tanke.exjobb.util.AbstractModelTest;

public class PublicationInfoTest extends AbstractModelTest {
    
    @Test
    public void isSerializable() {
        assertSerializable(PublicationInfo.class);
    }
    
    @Test
    public void constructorArgumentIsISBN() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        
        assertEquals(isbn1, sut.getISBN());
    }

    @Test
    public void keyValueIsISBN() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        
        assertEquals(isbn1, sut.toKey());
    }
    
    @Test
    public void newInstanceHasNoKeywords() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        
        assertFalse(sut.hasKeyword("k1"));
        assertNumberOfKeywords(0, sut);
    }
    
    @Test
    public void addSingleKeyword() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        
        sut.addKeyword("k1");
        
        assertTrue(sut.hasKeyword("k1"));
        assertFalse(sut.hasKeyword("k2"));
        assertNumberOfKeywords(1, sut);
    }
    
    @Test
    public void addSameKeywordTwice() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        
        sut.addKeyword("k1");
        sut.addKeyword("k1");
        
        assertTrue(sut.hasKeyword("k1"));
        assertFalse(sut.hasKeyword("k2"));
        assertNumberOfKeywords(1, sut);
    }
    
    @Test
    public void addTwoKeywords() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        
        sut.addKeyword("k1");
        sut.addKeyword("k2");
        
        assertTrue(sut.hasKeyword("k1"));
        assertTrue(sut.hasKeyword("k2"));
        assertNumberOfKeywords(2, sut);
    }
    
    @Test
    public void removeKeyword() {
        final PublicationInfo sut = new PublicationInfo(isbn1);
        sut.addKeyword("k1");
        sut.addKeyword("k2");
        
        sut.removeKeyword("k2");
        
        assertTrue(sut.hasKeyword("k1"));
        assertFalse(sut.hasKeyword("k2"));
        assertNumberOfKeywords(1, sut);
    } 
    
    private void assertNumberOfKeywords(int n, final PublicationInfo sut) {
        assertEquals(n, sut.getKeywords().size());
    }
}
