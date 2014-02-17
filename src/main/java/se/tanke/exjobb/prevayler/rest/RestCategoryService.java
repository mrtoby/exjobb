package se.tanke.exjobb.prevayler.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.prevayler.Prevayler;

import se.tanke.exjobb.prevayler.cmd.AddPublication;
import se.tanke.exjobb.prevayler.cmd.CreateCategory;
import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Library;
import se.tanke.exjobb.prevayler.model.PublicationInfo;
import se.tanke.exjobb.util.ISBN;
import se.tanke.exjobb.util.SearchCategoryFilter;
import se.tanke.exjobb.util.SearchPublicationFilter;

/**
 * Rest service for dealing with categories.
 * 
 * @author tobias
 */
@Path("prevayler/category")
public class RestCategoryService {

	@Inject private Prevayler<Library> prevayler;
	
	private Library getLibrary() {
		return prevayler.prevalentSystem();
	}
	
	@GET
	public List<CategoryInfo> findCategories(
			@QueryParam("searchName") final String searchName,
			@QueryParam("searchShortname") final String searchShortname) {
		return new SearchCategoryFilter(searchName, searchShortname)
				.filter(getLibrary().getAllItems().getCategories());
	}
	
	@GET
	@Path("{shortname}")
	public CategoryInfo getCategory(@PathParam("shortname") final Category.Key shortname) {
		return getLibrary().get(shortname).getInfo();
	}
	
	@GET
	@Path("{shortname}/publication")
	public List<PublicationInfo> findPublicationsInCategory(
			@PathParam("shortname") final Category.Key shortname,
			@QueryParam("searchTitle") final String searchTitle,
			@QueryParam("searchAuthor") final String searchAuthor,
			@QueryParam("searchKeywords") final String searchKeywords) {
		return new SearchPublicationFilter(searchTitle, searchAuthor, searchKeywords.split("\\s+"))
				.filter(getLibrary().get(shortname).getPublications());
	}
	
	@POST
	public void createCategory(final CategoryInfo categoryInfo) {
		prevayler.execute(new CreateCategory(categoryInfo));
	}
	
	@PUT
	@Path("{shortname}/publication/{isbn}")
	public void addPublicationToCategory(
			@PathParam("shortname") final Category.Key shortname,
			@PathParam("isbn") final ISBN isbn) {
		prevayler.execute(new AddPublication(isbn, shortname));
	}
	
	@GET
	@Path("{shortname}/category")
	public List<CategoryInfo> getSubcategories(
			@PathParam("shortname") final Category.Key shortname,
			@QueryParam("searchName") final String searchName,
			@QueryParam("searchShortname") final String searchShortname) {
		return new SearchCategoryFilter(searchName, searchShortname)
				.filter(getLibrary().get(shortname).getCategories());
	}
}
