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
	private org.eclipse.jetty.server.handler.ContextHandler.Context ctx = ContextHandler.getCurrentContext();

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	 //{"username":"orsa","password":"123"}
	public Response login(String request) {
		
		try{
			JSONObject jsonRequest = new JSONObject(request);
			
			String user = jsonRequest.get("username").toString();
			String pass = jsonRequest.get("password").toString();
			
			//TODO : connect to DB : check authenticate with user&password
			boolean isAllowedUser = true;
			if (isAllowedUser){
				return Response.ok().build();

			}else{
				return Response.status(Status.BAD_REQUEST).entity("not allowed user").build();
			}
			
		} catch (JSONException ex){
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();  
		}
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
//	{lastName: "b", firstName: "a", username: "c", password: "d"}
	public Response register(String request){
		try{
			JSONObject jsonRequest = new JSONObject(request);
			
			String lastName = jsonRequest.get("lastName").toString();
			String firstName = jsonRequest.get("firstName").toString();
			String userName= jsonRequest.get("username").toString();
			String password = jsonRequest.get("password").toString();
			
			//TODO : connect to DB : register new user with above parameters
			boolean isRegisterSuccessfully= true;
			if (isRegisterSuccessfully){
				return Response.ok().build();
			}else{
				return Response.status(Status.BAD_REQUEST).entity("register don't end successfully").build();
			}
			
		} catch (JSONException ex){
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();  
		}
	}

}






