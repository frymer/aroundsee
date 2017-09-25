package org.ozmi.aroundsee.server.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.ozmi.aroundsee.bl.RepositoryImplHanlder;
import org.ozmi.aroundsee.bl.repository.UserRepository;
import org.ozmi.aroundsee.models.AroundSeeUser;

import Jama.Matrix;
import net.sf.sprockets.Sprockets;
import net.sf.sprockets.google.Place;
import net.sf.sprockets.google.Places;
import net.sf.sprockets.google.Places.Response;
import se.walkercrou.places.GooglePlaces;

@Path("/places")
public class GoogleService {
	public static String apiKey = "AIzaSyBSnG-6XtF3lWyCPldv_En0DqJ0H8o16Uc";// "AIzaSyB1TNzF_kw1hbCkdmmWDOTkOW2xvPpQNNY";
	private static GooglePlaces client = null;
	private static final int DEFAULT_PLACES_REQUEST_NUMBER = 10;
	private static final int DEFAULT_RADIUS = 10000;
	private static final int USER_VECTOR_LENGTH = 7;
	private static UserRepository _aroundseeUserRepository = (UserRepository) new RepositoryImplHanlder<AroundSeeUser>(
			AroundSeeUser.class).getRepository();

	private static void setAPIKey() {
		Sprockets.getConfig().setProperty("google.api-key", apiKey);

		if (client == null) {
			client = new GooglePlaces(apiKey);
		}
	}

	@GET
	@Path("/getPlaces/{lat}/{lng}/{radius}/{type}")
	@Deprecated
	public static List<Place> radarGoogleSearch(@PathParam("lat") double lat, @PathParam("lng") double lng,
			@PathParam("radius") int radius, @PathParam("type") String type) throws IOException {
		setAPIKey();
		Places.Response<List<Place>> resp = Places.nearbySearch(
				Places.Params.create().latitude(lat).longitude(lng).radius(radius).addTypes(type).maxResults(60));

		return resp.getResult();
	}

	@GET
	@Path("/getPlacesByLatLng/{Lat}/{Lng}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public static javax.ws.rs.core.Response getPlacesByLatLng(@PathParam("Lat") double lat,
			@PathParam("Lng") double lng, @PathParam("userId") String userId) throws Throwable {
		setAPIKey();
		Response<List<Place>> places = Places
				.nearbySearch(Places.Params.create().latitude(lat).longitude(lng).radius(DEFAULT_RADIUS));
		List<org.ozmi.aroundsee.models.Place> sorted = sortPlaces(userId, places.getResult());

		JSONArray results = new JSONArray();
		sorted.subList(0, DEFAULT_PLACES_REQUEST_NUMBER).forEach(place -> results.put(place.toJson()));

		return javax.ws.rs.core.Response.ok(results.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	private static List<org.ozmi.aroundsee.models.Place> sortPlaces(Object userId, List<Place> results)
			throws Throwable {
		Matrix userVector = calcUserVector(userId);
		Map<org.ozmi.aroundsee.models.Place, Matrix> placesWithVectors = calcPlacesVectors(results);
		Map<org.ozmi.aroundsee.models.Place, Double> placesWithScores = new HashMap<org.ozmi.aroundsee.models.Place, Double>();

		// Calc scores for vectors
		for (Entry<org.ozmi.aroundsee.models.Place, Matrix> placeMat : placesWithVectors.entrySet()) {
			double dotProduct = userVector.arrayTimes(placeMat.getValue()).norm1();
			double eucledianDist = userVector.normF() * placeMat.getValue().normF();
			placesWithScores.put(placeMat.getKey(), dotProduct / eucledianDist);
		}

		// Sort by values
		placesWithScores = sortByValue(placesWithScores);

		List<org.ozmi.aroundsee.models.Place> resultsList = new ArrayList<org.ozmi.aroundsee.models.Place>();
		placesWithScores.keySet().forEach(place -> resultsList.add(place));

		return resultsList;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	private static Matrix calcUserVector(Object id) throws Throwable {
		AroundSeeUser user = _aroundseeUserRepository.read(id);

		double[][] userLikedPlaceMatrix = new double[1][org.ozmi.aroundsee.models.Place.PLACE_VECTOR_SIZE];
		for (int j = 0; j < org.ozmi.aroundsee.models.Place.PLACE_VECTOR_SIZE; j++) {
			userLikedPlaceMatrix[0][j] = 0;
		}

		if (!user.getLikedPlaces().isEmpty()) {

			for (int i = 0; i < user.getLikedPlaces().size(); i++) {
				double[][] placeVector = user.getLikedPlaces().get(i).getVector();
				for (int j = 0; j < placeVector.length; j++) {
					userLikedPlaceMatrix[0][j] += placeVector[0][j];
				}
			}

			for (int j = 0; j < org.ozmi.aroundsee.models.Place.PLACE_VECTOR_SIZE; j++) {
				userLikedPlaceMatrix[0][j] = ((double) userLikedPlaceMatrix[0][j])
						/ ((double) user.getLikedPlaces().size());
			}
		}

		return new Matrix(userLikedPlaceMatrix);
	}

	private static Map<org.ozmi.aroundsee.models.Place, Matrix> calcPlacesVectors(List<Place> results) {
		Map<org.ozmi.aroundsee.models.Place, Matrix> matrices = new HashMap<org.ozmi.aroundsee.models.Place, Matrix>();
		for (Place currGooglePlace : results) {
			org.ozmi.aroundsee.models.Place place = new org.ozmi.aroundsee.models.Place(currGooglePlace);
			matrices.put(place, extractVectorFromPlace(place));
		}

		return matrices;
	}

	private static Matrix extractVectorFromPlace(org.ozmi.aroundsee.models.Place place) {
		return new Matrix(place.getVector());
	}

	@GET
	@Path("/getPlaceById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static javax.ws.rs.core.Response getPlaceById(@PathParam("id") String id) throws IOException, JSONException {
		setAPIKey();
		// ChIJH3w7GaZMHRURkD-WwKJy-8E

		Response<Place> place = Places.details(Places.Params.create().placeId(id));

		String places = org.ozmi.aroundsee.models.Place.toJson(place.getResult()).toString();
		return javax.ws.rs.core.Response.ok(places).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@GET
	@Path("/rankPlaces/{userId}/{placeId}/{rank}")
	@Produces(MediaType.APPLICATION_JSON)
	public static javax.ws.rs.core.Response rankPlaces(@PathParam("userId") String userId,
			@PathParam("placeId") String placeId, @PathParam("rank") int rank) throws Throwable {
		setAPIKey();
		Response<Place> placeResponse = Places.details(Places.Params.create().placeId(placeId));
		_aroundseeUserRepository.addToUserLikedPlaces(userId, new org.ozmi.aroundsee.models.Place(placeResponse.getResult()));
		return javax.ws.rs.core.Response.ok().header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();

	}
}