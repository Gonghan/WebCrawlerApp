package edu.cmu.sv.webcrawler.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.cmu.sv.webcrawler.models.Categories;

@Path("/category")
public class CategoriesResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Categories getCategory(@PathParam("param") String symbol){
		Categories c= new Categories();
		return c;
	}
}
