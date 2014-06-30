package edu.cmu.sv.webcrawler.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import edu.cmu.sv.webcrawler.models.Crawler;
import edu.cmu.sv.webcrawler.models.Symbols;

@Path("/crawl")
public class CrawlerResource {

	@GET
	@Path("/{param}")
	public Response crawlBySymbol(@PathParam("param") String symbol) {
		String output = "Crawl the risk factors of the company with the symbol "
				+ symbol;
		Crawler c = new Crawler();
		c.crawl(symbol);
		return Response.status(200).entity(output).build();
	}

	@GET
	public Response crawlAll() {
		String output = "Crawl the risk factors of all companies";
		Crawler c = new Crawler();
		Symbols ss = new Symbols();
		for (String symbol : ss.getSymbols()) {
			c.crawl(symbol);
		}
		return Response.status(200).entity(output).build();
	}
}
