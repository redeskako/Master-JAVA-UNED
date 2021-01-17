package ws.soap;

import java.util.List;

import beans.HealthTopics.HealthTopic;

public class SearchResult {

	private int maxResults;
	private int page;
	private int elementsByPage;
	
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getElementsByPage() {
		return elementsByPage;
	}
	public void setElementsByPage(int elementsByPage) {
		this.elementsByPage = elementsByPage;
	}
	public List<HealthTopic> getResults() {
		return results;
	}
	public void setResults(List<HealthTopic> results) {
		this.results = results;
	}
	private List<HealthTopic> results;
}
