package edu.cmu.sv.webcrawler.apis;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.cmu.sv.webcrawler.models.Keywords;

@Path("/keywords")
public class KeywordResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public KeywordsJSON getAllKeywords() {
		Keywords ks = new Keywords();
		return new KeywordsJSON(ks.getKeywords());
	}

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public KeywordBySymbolJSON getKeywordsBySymbol(
			@PathParam("param") String symbol, @QueryParam("year") String year) {
		Keywords ks = new Keywords();
		return new KeywordBySymbolJSON(ks.getKeywords(symbol,year));
	}
}

class KeywordBySymbolJSON {
	Map<String, Integer> keywords;

	public KeywordBySymbolJSON(Map<String, Integer> keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the keywords
	 */
	public Map<String, Integer> getKeywords() {
		return keywords;
	}

}

class KeywordsJSON {
	Set<String> keywords;

	public KeywordsJSON(Set<String> keywords) {
		super();
		this.keywords = keywords;
	}

	/**
	 * @return the keywords
	 */
	public Set<String> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
}