package net.hardnorth.test.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.stream.Collectors;

@Path("/")
public class QuarkusTestService {

	@GET
	@Path("echo1")
	@Produces(MediaType.TEXT_PLAIN)
	public Response echo1(@QueryParam("test") String test) {
		return Response.status(Response.Status.OK)
				.encoding("UTF-8")
				.entity("test: " + test).build();
	}

	@GET
	@Path("echo2")
	@Produces(MediaType.TEXT_PLAIN)
	public Response echo2(@Context UriInfo uriInfo) {
		String uriStr = uriInfo.getBaseUri().toString() + uriInfo.getPath(true) + uriInfo.getQueryParameters();
		return Response.status(Response.Status.OK)
				.encoding("UTF-8")
				.entity(uriStr + "\n" + uriInfo.getQueryParameters().entrySet().stream().map(e->e.getKey() + ": " + e.getValue()).collect(Collectors.joining("\n"))).build();
	}

	@GET
	@Path("error")
	@Produces(MediaType.TEXT_PLAIN)
	public Response error(@Context UriInfo uriInfo) {
		String uriStr = uriInfo.getBaseUri().toString() + uriInfo.getPath(true) + uriInfo.getQueryParameters();
		return Response.status(Response.Status.BAD_REQUEST)
				.encoding("UTF-8")
				.entity(uriStr + "\nmy error").build();
	}
}
