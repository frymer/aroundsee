package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ozmi.aroundsee.server.services.GoogleService;
import org.ozmi.aroundsee.server.services.PlacesTypes;

import net.sf.sprockets.google.Places;
import net.sf.sprockets.google.Place.Photo;
import net.sf.sprockets.google.Places.Response;

public class Place {
	private final static int PHOTOS_MAX_WIDTH = 400;
	
	private String name;
	private String address;
	private String id;
	private String rating;
	private PlacesTypes type;
	private String icon;
	private String priceLevel;
	private String review;
	private List<String> openingHours;
	private List<String> images;
	
	public String getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(String priceLevel) {
		this.priceLevel = priceLevel;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public List<String> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<String> openingHours) {
		this.openingHours = openingHours;
	}
	
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
		
		this.review = place.getReviews().size() > 0 ? place.getReviews().get(0).getText() : null;
		this.priceLevel = String.valueOf(place.getPriceLevel());
		this.openingHours = place.getOpeningHours().size() != 0 ? place.getFormattedOpeningHours() : null; 
		
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
		
		json.put("review", this.review);
		json.put("priceLevel", this.priceLevel);
		JSONArray openingHoursArray = new JSONArray(this.openingHours);
		json.put("openingHours", openingHoursArray);
		
		json.put("address", this.address);
		json.put("id", this.id);
		json.put("rating", this.rating);
		json.put("icon", this.icon);
		JSONArray imagesArray = new JSONArray(this.images);
		json.put("images", imagesArray);
		
		return json;
	}
	
	public static JSONObject toJson(net.sf.sprockets.google.Place place) throws JSONException{
		Place placeModel = new Place(place);
		return (placeModel.toJson());
	}
	
	public static JSONArray toJson(List<net.sf.sprockets.google.Place> places) throws JSONException, IOException{
		JSONArray placesJson = new JSONArray();
		
		for(net.sf.sprockets.google.Place p: places){
			Response<net.sf.sprockets.google.Place> detailedPlace = Places.details(Places.Params.create().placeId(p.getPlaceId().getId()));
			placesJson.put(toJson(detailedPlace.getResult()));
		}
		
		return placesJson;
	}


}





