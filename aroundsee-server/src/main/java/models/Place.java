package models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ozmi.aroundsee.server.services.GoogleService;

import net.sf.sprockets.google.Place.Photo;

public class Place {
	private final static int PHOTOS_MAX_WIDTH = 400;
	
	private String name;
	private String address;
	private String id;
	private String rating;
	private String icon;
	private List<String> images;
	
	
	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Place(net.sf.sprockets.google.Place place){
		this.name = place.getName();
		this.address = place.getAddress() == null ? null : place.getAddress().toString();
		this.id = place.getPlaceId().getId();
		this.rating = String.valueOf(place.getRating());
		this.icon = place.getIcon();
		this.images = new ArrayList<String>();
		
		for(Photo p : place.getPhotos()){
			this.images.add(buildPhotoUrlRequest(p));
		}
		
	}
	
    public static String buildPhotoUrlRequest(Photo photo){
    	StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?photoreference=");
    	sb.append(photo.getReference());
    	sb.append("&maxwidth=" + PHOTOS_MAX_WIDTH);
    	sb.append("&key=" + GoogleService.apiKey);
    	
    	return sb.toString();
    }
	
	public JSONObject toJson() throws JSONException{
		JSONObject json = new JSONObject();
		
		json.put("name", this.name);
		json.put("address", this.address);
		json.put("id", this.id);
		json.put("rating", this.rating);
		json.put("icon", this.icon);
		
		JSONArray images = new JSONArray(this.images);
		json.put("images", images);
		
		return json;
	}
	
	public static JSONObject toJson(net.sf.sprockets.google.Place place) throws JSONException{
		return (new Place(place).toJson());
	}
	
	public static JSONArray toJson(List<net.sf.sprockets.google.Place> places) throws JSONException{
		JSONArray placesJson = new JSONArray();
		
		for(net.sf.sprockets.google.Place p: places){
			placesJson.put(toJson(p));
		}
		
		return placesJson;
	}
}





