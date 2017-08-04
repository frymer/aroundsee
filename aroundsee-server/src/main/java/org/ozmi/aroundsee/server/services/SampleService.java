package org.ozmi.aroundsee.server.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.server.handler.ContextHandler;

import net.sf.sprockets.google.Place;

@Path("/sample")
public class SampleService {
	private org.eclipse.jetty.server.handler.ContextHandler.Context ctx = ContextHandler.getCurrentContext();

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response hello() throws IOException {
		
		List<Place> places = GoogleService.radarGoogleSearch(31.9696, 34.8386, 10000, PlacesTypes.bar.toString());
		
		return Response.ok("Hello World! " + places.get(0).getName()).build();
	}
	
	@GET
	@Path("/check/{id}")
	public Response test(@PathParam("id") String id){
		return Response.ok(id).build();
	}
}
