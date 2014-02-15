package se.tanke.exjobb.prevayler.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.prevayler.Prevayler;

import se.tanke.exjobb.prevayler.cmd.CreateCategory;
import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.util.ISBN;

@Path("prevayler/category")
public class RestCategoryService {

	@Inject private Prevayler<Library> prevayler;
	
	private Library getLibrary() {
		return prevayler.prevalentSystem();
	}
	
	@GET
	public List<Category> findCategories(
			@QueryParam("name") final String name,
			@QueryParam("shortname") final String shortname) {
		return null;
	}
	
	@GET
	@Path("{shortname}")
	public Category getCategory(@PathParam("shortname") final Category.Key shortname) {
		return getLibrary().get(shortname);
	}
	
	@GET
	@Path("{shortname}/publication")
	public List<Publication> findPublicationsInCategory(
			@PathParam("shortname") final Category.Key shortname,
			@QueryParam("title") final String title,
			@QueryParam("author") final String author,
			@QueryParam("keywords") final String keywords) {
		return getLibrary().get(shortname).findPublications(title, author, keywords.split("\\s+"));
	}
	
	@POST
	@Consumes("application/json")
	public void createCategory(final CategoryInfo categoryInfo) {
		prevayler.execute(new CreateCategory(categoryInfo));
	}
	
	@PUT
	@Path("{shortname}/publication/{isbn}")
	public void addPublicationToCategory(
			@PathParam("shortname") final Category.Key shortname,
			@PathParam("isbn") final ISBN isbn) {
		
	}
}