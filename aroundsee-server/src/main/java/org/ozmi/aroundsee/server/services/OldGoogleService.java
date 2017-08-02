package org.ozmi.aroundsee.server.services;

import net.sf.sprockets.Sprockets;
import net.sf.sprockets.google.Place;
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
public class OldGoogleService {
    public static String apiKey = "AIzaSyBSnG-6XtF3lWyCPldv_En0DqJ0H8o16Uc";//"AIzaSyB1TNzF_kw1hbCkdmmWDOTkOW2xvPpQNNY";
    private static GooglePlaces client = null;
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
    
    private String buildPhotoUrl(String photoReference){
    	StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?photoreference=");
    	sb.append(photoReference);
    	sb.append("&key=" + apiKey);
    	
    	return sb.toString();
    }
    
    @GET
    @Path("/getPlacesByLatLng/{Lat}/{Lng}")
    @Produces(MediaType.APPLICATION_JSON)
    public static javax.ws.rs.core.Response getPlaceByLatLng(@PathParam("Lat") double lat, @PathParam("Lng") double lng) throws IOException, JSONException{
    	setAPIKey();
    	Response<List<Place>> places = Places.nearbySearch(Places.Params.create().latitude(lat).longitude(lng).radius(DEFAULT_RADIUS));
    	
//    	models.Place.toJson(places)places.getResult().get(0).
    	
    	JSONArray results = models.Place.toJson(places.getResult());
    	
    	return javax.ws.rs.core.Response.ok(results.toString() + places.getResult().get(0).getPlaceId()).build();
    }
    
    @GET
    @Path("/getPlaceById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static javax.ws.rs.core.Response getPlaceById(@PathParam("id") String id) throws IOException, JSONException{
    	setAPIKey();
    	
    	Response<List<Place>> place = Places.nearbySearch/*radarSearch*/(Places.Params.create().placeId(id));
    	String places = models.Place.toJson(place.getResult()).toString();
    	return javax.ws.rs.core.Response.ok(places).build();
    }
}


