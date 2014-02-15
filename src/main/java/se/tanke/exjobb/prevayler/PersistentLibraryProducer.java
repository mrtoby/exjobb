package se.tanke.exjobb.prevayler;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import se.tanke.exjobb.prevayler.model.Library;

@ApplicationScoped
public class PersistentLibraryProducer {

	private Prevayler<Library> persistentLibrary;
	
	@PostConstruct
	public void init() {
		final Library library = new Library();
		persistentLibrary = PrevaylerFactory.createTransientPrevayler(library);
	}

    
	@Produces
    public Prevayler<Library> getPersistentLibrary() {
		return persistentLibrary;
	}
}
