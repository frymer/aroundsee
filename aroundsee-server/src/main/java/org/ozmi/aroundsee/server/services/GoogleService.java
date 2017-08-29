package org.ozmi.aroundsee.server.services;

import net.sf.sprockets.Sprockets;
import net.sf.sprockets.google.Place;
import net.sf.sprockets.google.Place.Id;
import net.sf.sprockets.google.Place.Photo;
import net.sf.sprockets.google.Places;
import net.sf.sprockets.google.Places.Params;
import net.sf.sprockets.google.Places.Response;
import se.walkercrou.places.GooglePlaces;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

@Path("/places")
public class GoogleService {
    public static String apiKey = "AIzaSyBSnG-6XtF3lWyCPldv_En0DqJ0H8o16Uc";//"AIzaSyB1TNzF_kw1hbCkdmmWDOTkOW2xvPpQNNY";
    private static GooglePlaces client = null;
    private static final int DEFAULT_PLACES_REQUEST_NUMBER = 10;
    private static final int DEFAULT_RADIUS = 10000;

    private static void setAPIKey(){
        Sprockets.getConfig().setProperty("google.api-key", apiKey);
        
        if(client == null){
            client = new GooglePlaces(apiKey);
        }
    }
    
    @GET
	@Path("/getPlaces/{lat}/{lng}/{radius}/{type}")
    @Deprecated
    public static List<Place> radarGoogleSearch(@PathParam("lat") double lat, @PathParam("lng") double lng, 
    											@PathParam("radius") int radius, @PathParam("type") String type) throws IOException {
    	setAPIKey();
        Places.Response<List<Place>> resp = Places.nearbySearch(Places.Params.create().latitude(lat).longitude(lng).radius(radius).addTypes(type).maxResults(60));
        
        return resp.getResult();
    }
    
    @GET
    @Path("/getPlacesByLatLng/{Lat}/{Lng}")
    @Produces(MediaType.APPLICATION_JSON)
    public static javax.ws.rs.core.Response getPlacesByLatLng(@PathParam("Lat") double lat, @PathParam("Lng") double lng) throws IOException, JSONException{
    	setAPIKey();
    	Response<List<Place>> places = Places.nearbySearch(Places.Params.create().latitude(lat).longitude(lng).radius(DEFAULT_RADIUS));
    	JSONArray results = models.Place.toJson(places.getResult().subList(0, DEFAULT_PLACES_REQUEST_NUMBER));
    	
    	return javax.ws.rs.core.Response.ok(results.toString())
    		   .header("Access-Control-Allow-Origin", "*")
    		   .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    		   .allow("OPTIONS").build();
    }
    
    @GET
    @Path("/getPlaceById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static javax.ws.rs.core.Response getPlaceById(@PathParam("id") String id) throws IOException, JSONException{
    	setAPIKey();
//    	ChIJH3w7GaZMHRURkD-WwKJy-8E
    	
    	Response<Place> place = Places.details(Places.Params.create().placeId(id));
    	
    	String places = models.Place.toJson(place.getResult()).toString();
    	return javax.ws.rs.core.Response.ok(places)
    		   .header("Access-Control-Allow-Origin", "*")
    		   .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    		   .allow("OPTIONS").build();
    }
}


