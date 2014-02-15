package se.tanke.exjobb.prevayler.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.prevayler.Prevayler;

import se.tanke.exjobb.prevayler.cmd.CreatePublication;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.PublicationInfo;
import se.tanke.exjobb.util.ISBN;

/**
 * Rest service for dealing with publications.
 * 
 * @author tobias
 */
@Path("prevayler/publication")
public class  RestPublicationService extends AbstractRestService {

	@Inject private Prevayler<Library> prevayler;
	
	private Library getLibrary() {
		return prevayler.prevalentSystem();
	}
	
	@GET
	public List<PublicationInfo> findPublications(
			@QueryParam("title") final String title,
			@QueryParam("author") final String author,
			@QueryParam("keywords") final String keywords) {
		return toPublicationInfo(getLibrary().getAllItems()
				.findPublications(title, author, keywords.split("\\s+")));
	}
	
	@POST
	public void createPublication(PublicationInfo publicationInfo) {
		prevayler.execute(new CreatePublication(publicationInfo));
	}

	@GET
	@Path("{isbn}")
	public PublicationInfo getPublication(@PathParam("isbn") final ISBN isbn) {
		return getLibrary().get(isbn).getInfo();
	}	
}
