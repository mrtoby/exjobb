package se.tanke.exjobb.prevayler.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;
import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.prevayler.model.PublicationInfo;

/**
 * Base class with some helper methods for the rest services in this package.
 *
 * @author tobias
 */
public abstract class AbstractRestService {

	public static List<CategoryInfo> toCategoryInfo(
			final Collection<Category> categories) {
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>(categories.size());
		for (Category category : categories) {
			categoryInfoList.add(category.getInfo());
		}
		return categoryInfoList;
	}

	public static List<PublicationInfo> toPublicationInfo(
			final Collection<Publication> publications) {
		List<PublicationInfo> publicationInfoList = new ArrayList<PublicationInfo>(publications.size());
		for (Publication publication : publications) {
			publicationInfoList.add(publication.getInfo());
		}
		return publicationInfoList;
	}
}
