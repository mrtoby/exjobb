package se.tanke.exjobb.util;

import static se.tanke.exjobb.util.StringUtils.matches;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.tanke.exjobb.prevayler.model.Category;
import se.tanke.exjobb.prevayler.model.CategoryInfo;

public class SearchCategoryFilter {

	private String searchName;
	private String searchShortname;
	
	public SearchCategoryFilter(final String searchName, final String searchShortname) {
		this.searchName = searchName;
		this.searchShortname = searchShortname;
	}

	/**
	 * Evaluate if a category passes the filter or not.
	 * @param categoryInfo Information about the category to evaluate
	 * @return <code>true</code> on match
	 */
	public boolean apply(final CategoryInfo categoryInfo) {
		return matches(searchName, categoryInfo.getName())
				&& matches(searchShortname, categoryInfo.getShortname());
	}

	/**
	 * Filter a list of categories and also extract the category info.
	 * @param categories Categories to filter
	 * @return Info objects for matching categories
	 */
	public List<CategoryInfo> filter(final Collection<Category> categories) {
		List<CategoryInfo> result = new ArrayList<CategoryInfo>();
		for (Category category : categories) {
			if (apply(category.getInfo())) {
				result.add(category.getInfo());
			}
		}
		return result;
	}
}
