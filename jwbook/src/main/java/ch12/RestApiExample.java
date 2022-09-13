package ch12;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class RestApiExample {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello API Service";
	}
	
	@POST
	public String sayHello(@QueryParam("msg") String msg) {
		return msg+" API Service";
	}
}
