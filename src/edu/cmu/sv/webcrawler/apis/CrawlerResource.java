package edu.cmu.sv.webcrawler.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.cmu.sv.webcrawler.models.Crawler;

@Path("/crawl")
public class CrawlerResource {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg(@PathParam("param") String symbol) {
		String output = "Crawl the risk factors of the company with the symbol "
				+ symbol;
		Crawler c=new Crawler();
		c.crawl(symbol);
		return Response.status(200).entity(output).build();
	}
}
