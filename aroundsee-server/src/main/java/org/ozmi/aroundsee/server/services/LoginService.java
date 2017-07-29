package org.ozmi.aroundsee.server.services;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/authentication")
public class LoginService {
 //{"username":"orsa","password":"123"}
	private org.eclipse.jetty.server.handler.ContextHandler.Context ctx = ContextHandler.getCurrentContext();

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String request) {
		
		try{
			JSONObject jsonRequest = new JSONObject(request);
			
			String user = jsonRequest.get("username").toString();
			String pass = jsonRequest.get("password").toString();
			
			//TODO : connect to DB : check authenticate with user&password
			boolean isAllowedUser = true;
			if (isAllowedUser){
				
			}else{
				return Response.status(Status.BAD_REQUEST).entity("not allowed user").build();
			}
			
		} catch (JSONException ex){
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();  
		}
		
		
		return Response.ok().build();
	}

}






