package se.tanke.exjobb.util;

import static se.tanke.exjobb.util.StringUtils.matches;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.tanke.exjobb.prevayler.model.Publication;
import se.tanke.exjobb.prevayler.model.PublicationInfo;

public class SearchPublicationFilter {

	private String searchTitle;
	private String searchAuthor;
	private String[] searchKeyword;

	public SearchPublicationFilter(final String searchTitle, final String searchAuthor,
			final String[] searchKeyword) {
		this.searchTitle = searchTitle;
		this.searchAuthor = searchAuthor;
		this.searchKeyword = searchKeyword;
	}

	/**
	 * Evaluate if a publication passes the filter or not.
	 * @param publicationInfo Info about the publication to evaluate
	 * @return <code>true</code> on match
	 */
	public boolean apply(PublicationInfo publicationInfo) {
		return matches(searchTitle,  publicationInfo.getTitle())
				&& matches(searchAuthor, publicationInfo.getAuthor())
				&& keywordsMatches(searchKeyword, publicationInfo);
	}

	/**
	 * Filter a list of publications and also extract the publication info.
	 * @param publications Publications to filter
	 * @return Info objects for matching publications
	 */
	public List<PublicationInfo> filter(final Collection<Publication> publications) {
		List<PublicationInfo> result = new ArrayList<PublicationInfo>(publications.size());
		for (Publication publication : publications) {
			if (apply(publication.getInfo())) {
				result.add(publication.getInfo());
			}
		}
		return result;
	}

	/**
	 * Simple matching by checking if all keywords that is searched for exists
	 * in the set of keywords. If the search don't contain any keywords it should
	 * be considered a match.
	 * @param searchKeywords Keywords to search for
	 * @param publicationInfo The keeper of the keywords
	 * @return <code>true</code> if the keywords match
	 */
	private boolean keywordsMatches(final String[] searchKeywords, final PublicationInfo publicationInfo) {
		for (String searchKeyword : searchKeywords) {
			if (!publicationInfo.hasKeyword(searchKeyword)) {
				return false;
			}
		}
		return true;
	}
}
