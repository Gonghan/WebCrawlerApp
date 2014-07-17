package edu.cmu.sv.webcrawler.apis;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.cmu.sv.webcrawler.models.Categories;
import edu.cmu.sv.webcrawler.models.Keywords;

@Path("/category")
public class CategoriesResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Categories getCategory(@PathParam("param") String symbol){
		Categories c= new Categories();
		return c;
	}
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Integer> getCategoryBySymbol(
			@PathParam("param") String symbol, @QueryParam("year") String year) {
		Keywords ks = new Keywords();
		Map<String, Integer> map=ks.getKeywords(symbol, year);
		Categories c= new Categories(map);
		return c.getMap();
	}
}
