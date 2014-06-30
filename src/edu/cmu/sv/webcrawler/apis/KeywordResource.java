package edu.cmu.sv.webcrawler.apis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.cmu.sv.webcrawler.services.DummyKeywordsService;
import edu.cmu.sv.webcrawler.services.KeywordsService;

@Path("/keywords")
public class KeywordResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public KeywordsJSON getAllKeywords() {
		KeywordsService ks = new DummyKeywordsService();
		return new KeywordsJSON(ks.getKeywords());
	}

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public KeywordsJSON getKeywordsBySymbol(@PathParam("param") String symbol) {
		KeywordsService ks = new DummyKeywordsService();
		return new KeywordsJSON(ks.getKeywordsBySymbol(symbol));
	}
}

class KeywordsJSON {
	List<String> keywords;

	public KeywordsJSON(List<String> keywords) {
		super();
		this.keywords = keywords;
	}

	/**
	 * @return the keywords
	 */
	public List<String> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
}