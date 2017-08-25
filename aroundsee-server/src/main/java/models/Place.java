package models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
	private String website;
	private String openingHoursToday;
	private String geoLocation;
	private String phone;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
		this.address = place.getAddress() == null ? place.getVicinity() : place.getFormattedAddress();

		this.id = place.getPlaceId().getId();
		this.rating = String.valueOf(place.getRating());
		this.icon = place.getIcon();
		this.images = new ArrayList<String>();
		this.website = place.getWebsite();
		this.geoLocation = place.getLatitude()+","+place.getLongitude();
		this.phone = place.getFormattedPhoneNumber();

		// DayOfWeek is based on american week and starts at monday, calendar is
		// based on system calendar and is set to israel time so we modify the result
		// to create a match between them
		int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
		if(today == 0){
			today = 7;
		}
		final int finalToday = today;
		Optional<net.sf.sprockets.google.Place.OpeningHours> openingHours = place.getOpeningHours().stream().filter(oh -> oh.getOpenDay().toString().equals(
																														  DayOfWeek.of(finalToday).toString()))
																										    .findFirst();

		this.openingHoursToday = place.getOpeningHours().isEmpty() || !openingHours.isPresent() ? "opening hours unknown" :
				Place.toTimePart(openingHours.get().getOpenHour())+":"+
				Place.toTimePart(openingHours.get().getOpenMinute())+" - "+
				Place.toTimePart(openingHours.get().getCloseHour())+":"+
				Place.toTimePart(openingHours.get().getCloseMinute());
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
		json.put("activityHours", this.openingHoursToday);
		json.put("navData",this.geoLocation);
		json.put("phone",this.phone);
		if(this.images.size()> 1){
			json.put("mainImage",this.images.get(0));
			JSONArray images = new JSONArray(this.images.subList(1,this.images.size()));
			json.put("images", images);
		}
		else if(this.images.size() > 0){
			json.put("mainImage",this.images.get(0));
		}
		json.put("website", this.website);
		
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

	public static String toTimePart(int part){
		if(part < 10){
			return "0"+part;
		}
		return ""+part;
	}
}





