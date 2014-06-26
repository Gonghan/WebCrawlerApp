package edu.cmu.sv.webcrawler.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.cmu.sv.webcrawler.models.Symbols;

@Path("/symbols")
public class SymbolsResource {
	
//	@GET
//	@Path("/{param}")
//	public Response getMsg(@PathParam("param") String msg) {
//		String output = " ";
//		return Response.status(200).entity(output).build();
//	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON )
	public Symbols getSymbols(){
		
		return new Symbols();
	}
}

