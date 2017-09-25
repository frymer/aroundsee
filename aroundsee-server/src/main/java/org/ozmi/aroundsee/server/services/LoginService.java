package org.ozmi.aroundsee.server.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.ozmi.aroundsee.bl.RepositoryImplHanlder;
import org.ozmi.aroundsee.bl.repository.UserRepository;
import org.ozmi.aroundsee.models.AroundSeeUser;
import org.ozmi.aroundsee.models.serialization.SerializationDeserializationService;
import org.ozmi.aroundsee.models.serialization.SerializeFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/authentication")
public class LoginService {
	private org.eclipse.jetty.server.handler.ContextHandler.Context ctx = ContextHandler.getCurrentContext();
	private UserRepository _aroundseeUserRepository = (UserRepository) new RepositoryImplHanlder<AroundSeeUser>(
			AroundSeeUser.class).getRepository();

	@POST
	@Path("/test")
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response test(@Context HttpServletRequest r) throws IOException {
		System.out.println();

		Map<String, String[]> sa = r.getParameterMap();
		String as = r.getReader().readLine();

		// System.out.println("request :" + r.getParameter("username"));

		return Response.ok("ok").header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();

	}

	@GET
	public Response getAllUsers() throws Throwable {
		List<AroundSeeUser> users = _aroundseeUserRepository.all();
		JsonNode result = new ObjectMapper().readTree(users.toString());

		return Response.status(Status.OK).entity(result).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();

	}

	@POST
	@Path("/login")
	 @Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	// {"username":"orsa","password":"123"}
	public Response login(String request) throws IOException {

		try {
			JSONObject jsonRequest = new JSONObject(request);

			String username = jsonRequest.get("username").toString();
			String pass = jsonRequest.get("password").toString();

			AroundSeeUser user = _aroundseeUserRepository.getUserByUsernameAndPass(username, pass);
			

			if (user != null) {
				return Response.ok().entity(user.get_id().toHexString())
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();

			} else {
				return Response.status(Status.BAD_REQUEST).entity("not allowed user").header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			}

		} catch (JSONException ex) {
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		} catch (Throwable e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Could not verfiy user").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	// {lastName: "b", firstName: "a", username: "c", password: "d"}
	public Response register(String request) {
		try {
			JSONObject jsonRequest = new JSONObject(request);

			String username = jsonRequest.get("username").toString();
			AroundSeeUser arUser = _aroundseeUserRepository.getUserByUsername(username);
			if (arUser != null) {
				return Response.status(Status.BAD_REQUEST).entity("Username already exists").header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			}
			AroundSeeUser user = new AroundSeeUser();
			user.setLastName(jsonRequest.get("lastName").toString());
			user.setFirstName(jsonRequest.get("firstName").toString());
			user.setUsername(jsonRequest.get("username").toString());
			user.setPassword(jsonRequest.get("password").toString());
			user.set_id(new ObjectId());

			_aroundseeUserRepository.create(user);

			// TODO : connect to DB : register new user with above parameters
			boolean isRegisterSuccessfully = true;
			if (isRegisterSuccessfully) {
				return Response.ok().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			} else {
				return Response.status(Status.BAD_REQUEST).entity("register don't end successfully").header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			}

		} catch (JSONException ex) {
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		} catch (Throwable e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Could not save new user").header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();

		}
	}

	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	// {username: "c", password: "d"}
	public Response delete(String request) {
		try {
			JSONObject jsonRequest = new JSONObject(request);

			String userName = jsonRequest.get("username").toString();
			String password = jsonRequest.get("password").toString();

			boolean isDeletedSuccessfully = _aroundseeUserRepository.deleteByUsernameAndPass(userName, password);
			;
			if (isDeletedSuccessfully) {
				return Response.ok().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
			} else {
				return Response.status(Status.BAD_REQUEST).entity("register don't end successfully").build();
			}

		} catch (JSONException ex) {
			return Response.status(Status.BAD_REQUEST).entity("request body is not in json type").build();
		} catch (Throwable e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Could not remove user").build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getUserById(@PathParam("id") String id) throws Throwable {
		AroundSeeUser user = _aroundseeUserRepository.read(id);
		return Response.status(Status.OK)
				.entity(SerializationDeserializationService.serializeTo(user, SerializeFormat.Json)).build();
	}

}
