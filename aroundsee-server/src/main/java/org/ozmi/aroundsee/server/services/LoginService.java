package org.ozmi.aroundsee.server.services;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.ozmi.aroundsee.bl.RepositoryImplHanlder;
import org.ozmi.aroundsee.bl.repository.UserRepository;
import org.ozmi.aroundsee.models.AroundSeeUser;

@Path("/authentication")
public class LoginService {
	private org.eclipse.jetty.server.handler.ContextHandler.Context ctx = ContextHandler.getCurrentContext();
	private UserRepository _aroundseeUserRepository = 
			(UserRepository) new RepositoryImplHanlder<AroundSeeUser>(AroundSeeUser.class).getRepository();


	@POST
	@Path("/test")
//	@Consumes(MediaType.APPLICATION_JSON)
	public Response test (@Context HttpServletRequest r) throws IOException{
		System.out.println();
		
		Map<String, String[]> sa= r.getParameterMap();
		String as = r.getReader().readLine();
		
//		System.out.println("request    :" + r.getParameter("username"));
		
		return Response.ok("ok").header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
	}
	
	@POST
	@Path("/login")
//	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	 //{"username":"orsa","password":"123"}
	public Response login(@Context HttpServletRequest request) throws IOException {
		
		try{
			JSONObject jsonRequest = new JSONObject(request);
			
			String user = jsonRequest.get("username").toString();
			String pass = jsonRequest.get("password").toString();
			
			//TODO : connect to DB : check authenticate with user&password
			boolean isAllowedUser = _aroundseeUserRepository.doesUserExist(user, pass);
			
			if (isAllowedUser){
				return Response.ok().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();

			}else{
				return Response.status(Status.BAD_REQUEST).entity("not allowed user").build();
			}
			
		} catch (JSONException ex){
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();  
		} catch (Throwable e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Could not verfiy user").build();
		}
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
//	{lastName: "b", firstName: "a", username: "c", password: "d"}
	public Response register(String request){
		try{
			JSONObject jsonRequest = new JSONObject(request);
			
			AroundSeeUser user = new AroundSeeUser();
			user.setLastName(jsonRequest.get("lastName").toString());
			user.setFirstName(jsonRequest.get("firstName").toString());
			user.setUsername(jsonRequest.get("username").toString());
			user.setPassword(jsonRequest.get("password").toString());
			
			_aroundseeUserRepository.create(user);
			
			
			//TODO : connect to DB : register new user with above parameters
			boolean isRegisterSuccessfully= true;
			if (isRegisterSuccessfully){
				return Response.ok().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS")..build();
			}else{
				return Response.status(Status.BAD_REQUEST).entity("register don't end successfully").build();
			}
			
		} catch (JSONException ex){
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();  
		} catch (Throwable e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Could not save new user").build();  

		}
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
//	{username: "c", password: "d"}  
	public Response delete(String request){
		try{
			JSONObject jsonRequest = new JSONObject(request);
			
			String userName= jsonRequest.get("username").toString();
			String password = jsonRequest.get("password").toString();
			
			boolean isDeletedSuccessfully= _aroundseeUserRepository.deleteByUsernameAndPass(userName, password);;
			if (isDeletedSuccessfully){
				return Response.ok().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			}else{
				return Response.status(Status.BAD_REQUEST).entity("register don't end successfully").build();
			}
			
		} catch (JSONException ex){
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();  
		} catch (Throwable e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Could not remove user").build();
		}
	}
	
	

}






