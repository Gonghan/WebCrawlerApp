package edu.cmu.sv.webcrawler.apis;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.cmu.sv.webcrawler.models.Record;

@Path("/results")
public class ResultsResource {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Records getResult(@PathParam("param") String symbol,
			@QueryParam("year") String year) {
		Records records = new Records();
		List<Record> list = null;
		if (year == null || year.isEmpty()) {
			list = Record.search(symbol);
		} else {
			list = Record.search(symbol, year);
		}
		records.setRecords(list);
		return records;
	}

	@DELETE
	@Path("/{param}")
	public Response removeResult(@PathParam("param") String symbol,
			@QueryParam("year") String year) {
		Record.remove(symbol, year);
		return Response.status(200).entity("Remove a result with the symbol "+symbol).build();
	}
	
	//delete all records
	@DELETE
	public Response removeAllResult(){
		Record.removeAll();
		return Response.status(200).entity("Remove all result.").build();
		
	}
}

class Records {
	List<Record> records;

	/**
	 * @return the records
	 */
	public List<Record> getRecords() {
		return records;
	}

	/**
	 * @param records
	 *            the records to set
	 */
	public void setRecords(List<Record> records) {
		this.records = records;
	}

}