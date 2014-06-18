package com.ibm.cloudoe.samples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.wink.json4j.JSONObject;

//This class define the /hello RESTful API to fetch all system environment information.

@Path("/hello")
public class HelloResource {

	@GET
	public String getInformation() {
		// load all system environments
		JSONObject sysEnv = new JSONObject(System.getenv());

		// 'VCAP_APPLICATION' is in JSON format, it contains useful information about a deployed application
		// String envApp = System.getenv("VCAP_APPLICATION");

		// 'VCAP_SERVICES' contains all the credentials of services bound to this application.
		// String envServices = System.getenv("VCAP_SERVICES");
		// TODO Get service credentials and communicate with BlueMix services
		return sysEnv.toString();
	}
}