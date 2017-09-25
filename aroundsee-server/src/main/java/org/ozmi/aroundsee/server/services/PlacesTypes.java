package org.ozmi.aroundsee.server.services;

public enum PlacesTypes {
	accounting("accounting",-1, -1, 1, 0.7, 0.5, -1, 0), 
	airport("airport",0, 0, 0, 1, 0, 1, 0.5), 
	campground("campground",1, 0.5, 0, -1, 0, 0, 0.5), 
	amusement_park("amusement_park",0.5, 0.5, 0, 0, 0.5, 0,0), 
	bicycle_store("bicycle_store",0.5, 1, 1, 0.5, 0, 1, 0), 
	aquarium("aquarium",1, 0, 0, 0.5, 0.5, 0, 0), 
	art_gallery("art_gallery",0, 0, 0, 1, 1, 0, 0), 
	atm("atm",0, 0, 0.3, 0.5, 0, -0.5, -1), 
	bank("bank",0, 0, 0.3, 0.5, 0, 0, -0.5),
	bakery("bakery",0, -1, 1, 0.7, 0, 0, 1), 
	bar("bar",0, -0.5, 1, 0.5, 0, 0, 0.6), 
	cafe("cafe",0, -0.5, 1, 0.5, 0, 0, 0.6), 
	beauty_salon("beauty_salon",0, -0.3, 0.4, 0.5, 0, 0, -0.8), 
	book_store("book_store",0, 0, 1, 0, 1, 0, 0), 
	bowling_alley("bowling_alley",0, 1, 0.3, 0.5, 0, 0, 0.5), 
	bus_station("bus_station",0, 0, -1, 1, 0, 1, 0), 
	car_dealer("",0, 0, 0.2, 0.5, 0, 0.2, 0), 
	car_rental("car_rental",0, 0, 0.2, 0.5, 0, 0.2, 0),
	car_repair("car_repair",0, 0, 0.2, 0.5, 0, 0.2, 0),
	car_wash("car_wash",0, 0, 0.2, 0.5, 0, 0.2, 0), 
	casino("casino",0, 0, 0.7, 0.4, 0, 0, 0.6), 
	cemetery("cemetery",0.1, -0.5, 0, 0, 0, 0, -0.5), 
	courthouse("courthouse",0, 0, 0, 0, 0.5, 0, 0), 
	church("church",0, 0, 0, 0, 0.5, 0, 0), 
	city_hall("church",-1, 0, -1, 0.7, 0, 0, 0), 
	embassy("embassy",-1, 0, -1, 0.7, 0, 0, 0), 
	fire_station("fire_station",0, 0.3, -0.4, 0.3, 0, 0.5, 0), 
	clothing_store("clothing_store",0, 0.2, 1, 0.6, 0, 0, 0), 
	convenience_store("convenience_store",0.2, 0, 0.8, -0.5, 0, 0.3, 0.5), 
	doctor("doctor",0, 0, 0.2, 0, 0, 0, -0.5), 
	dentist("dentist",0, 0, 0.2, 0, 0, 0, -0.5), 
	electrician("electrician",0, 0, 0.2, 0, 0, 0, -0.5), 
	electronics_store("electronics_store",0, 0, 1, 0, 0, 0, -0.5),
	department_store("department_store",0, 0, 1, 0.3, 0, 0, 0), 
	florist("florist",0.3, 0, 1, 0.2, 0, 0, -0.3), 
	funeral_home("funeral_home",-0.2, -0.2, -0.2, -0.2, -0.2, -0.2, -0.2), 
	furniture_store("furniture_store",0, 0, 1, 0.2, 0, 0, -0.3), 
	gas_station("gas_station",0.2, 0, 0.2, -0.4, 0, 0.4, 0.4), 
	gym("gym",0, 1, 0.2, 0.5, -0.3, 0, 0), 
	hair_care("hair_care",0, -0.3, 0.6, 0.5, 0, 0, -0.8), 
	hardware_store("hardware_store",0, 0, 1, 0.3, 0.2, 0, 0), 
	hindu_temple("hindu_temple",0.7, 0, -0.5, -0.5, 0, 0, 0), 
	home_goods_store("home_goods_store",0, 0, 1, 0.3, 0, 0, -0.4), 
	hospital("hospital",-0.3, -0.3, 0, 0.7, 0, 0, 0), 
	insurance_agency("insurance_agency",0, 0, 0.1, 0.4, 0, 0, 0), 
	jewelry_store("jewelry_store",0, 0, 1, 0.3, 0, 0, -0.5), 
	laundry("laundry",0, 0, 0.5, 0.5, 0, 0, -0.5), 
	lawyer("lawyer",-0.5, 0, 0, 0, 0.6, 0, 0), 
	library("library",0, 0, 0.5, 0.7, 1, 0, 0), 
	liquor_store("liquor_store",0, 0, 1, 0, -0.4, 0, 0.1), 
	local_government_office("local_government_office",-1, 0, -1, 0.7, 0, 0, 0), 
	locksmith("locksmith",0, 0, 0.2, 0.2, -0.6, 0, -0.2), 
	lodging("lodging",0.1, 0, 0, 0.1, 0.1, 0, 0.1), 
	meal_delivery("meal_delivery",0, 0, 0.5, 0.4, 0, 0.1, 1), 
	meal_takeaway("meal_takeaway",0, 0, 0.5, 0.4, 0, 0.1, 1), 
	mosque("mosque",0, 0, 0.1, 0, 0.5, 0, 0), 
	movie_rental("movie_rental",0, 0, 0.5, 0.7, 0.3, 0, 0.3), 
	movie_theater("movie_theater",0, 0, 0.5, 0.7, 0.3, 0, 0.3), 
	moving_company("moving_company",0, 0, 0.5, 0.7, 0.3, 0, 0.3),
	museum("museum",0.2, 0, 0.5, 0.1, 0.8, 0, 0), 
	night_club("night_club",0, 0.1, 0.8, 0.8, 0, 0, 0.4), 
	painter("painter",0, 0.2, 0, 0, 0.4, 0, 0), 
	park("park",1, 0.4, 0, 0.3, 0, 0, 0.2), 
	parking("parking",0.2, 0, 0.4, 0.2, 0, 0.5, 0), 
	pet_store("pet_store",0, 0, 1, 0.2, 0, 0, 0), 
	pharmacy("pharmacy",0, 0, 1, 0.4, 0.2, 0, 0.3), 
	physiotherapist("physiotherapist",0.1, 0, 0.4, 0, 0, 0, 0), 
	plumber("plumber",0, 0, 0.4, 0, 0.1, 0, 0), 
	police("police",0, 0, 0.4, 0, 0.1, 0, 0), 
	post_office("post_office",0, 0, 0.4, 0, 0.1, 0.1, 0), 
	real_estate_agency("real_estate_agency",0, 0, 0.3, 0.6, 0, 0, 0), 
	restaurant("restaurant",0, 0, 1, 0.6, 0, 0, 1), 
	roofing_contractor("roofing_contractor",0, 0, 0.4, 0, 0.1, 0, 0),  
	rv_park("rv_park",1, 0.4, 0, 0.3, 0, 0, 0.2), 
	school("school",0.2, 0.2, 0.1, 0.6, 1, 0, 0.2), 
	shoe_store("shoe_store",0, 0, 1, 0.5, 0, 0, 0), 
	shopping_mall("shopping_mall",0, 0, 1, 0.5, 0, 0, 0.8), 
	spa("spa",0.2, 0, 1, 0.5, 0, 0, 0.1), 
	stadium("stadium",0, 1, 0, 0.3, 0, 0, 0.4), 
	storage("storage",0, 0, 0, 0, 0, 0, 0), 
	store("store",0, 0, 1, 0, 0, 0, 0), 
	subway_station("subway_station",0, 0, 0, 0.5, 0, 1, 0), 
	synagogue("synagogue",0, 0, 0.2, 0.4, 0.5, 0, 0), 
	taxi_stand("taxi_stand",0, 0, 0.2, 0.6, 0, 1, 0), 
	train_station("train_station",0, 0, 0.2, 0.6, 0, 1, 0), 
	transit_station("transit_station",0, 0, 0.2, 0.6, 0, 1, 0),
	travel_agency("travel_agency",0, 0, 0.6, 0.6, 0, 1, 0),
	university("university",0.1, 0, 0, 0, 1, 0, 0.2), 
	veterinary_care("veterinary_care",0, 0, 0.8, 0, 0, 0, 0), 
	zoo("zoo",0.8, 0, 0.4, 0.3, 0, 0, 0),
	administrative_area_level_1("administrative_area_level_1",0, 0, 0.4, 0.6, 0, 0.1, 0),
	administrative_area_level_2("administrative_area_level_2",0, 0, 0.4, 0.6, 0, 0.1, 0),
	administrative_area_level_3("administrative_area_level_3",0, 0, 0.4, 0.6, 0, 0.1, 0),
	administrative_area_level_4("administrative_area_level_4",0, 0, 0.4, 0.6, 0, 0.1, 0),
	administrative_area_level_5("administrative_area_level_5",0, 0, 0.4, 0.6, 0, 0.1, 0),
	colloquial_area("colloquial_area",0, 0, 0, 0.6, 0.4, 0, 0),
	country("country",0, 0, 0, 0.2, 0, 0, 0),
	establishment("establishment",0, 0, 0.5, 0.5, 0, 0, 0),
	finance("finance",0, 0, 0.4, 0.3, 0.5, 0, 0),
	floor("floor",0, 0, 0, 0.5, 0, 0, 0),
	food("food",0, 0, 0, 0, 0, 0, 1),
	general_contractor("general_contractor",0, 0, 0, 0.5, 0.3, 0, 0),
	geocode("general_contractor",0, 0, 0, 0, 0, 0, 0),
	health("health",0, 0, 0.5, 0, 0.3, 0, 0),
	intersection("intersection",0, 0, 1, 0, 0, 0, 0),
	locality("locality",0.1, 0, 0, 1, -1, 0, 0),
	natural_feature("natural_feature",1, 0, 0, 0, 0.2, 0, 0),
	neighborhood("neighborhood",0, 0, 0, 0.8, 0, 0, 0),
	place_of_worship("place_of_worship",0, 0, 0.2, 0.4, 0.5, 0, 0), 
	political("political",0, 0, 0, 0.3, 0.3, 0, 0),
	point_of_interest("point_of_interest",0, 0, 0, 0, 0, 0, -1),
	post_box("post_box",0, 0, 0, 0, 0, 0.8, 0),
	postal_code("postal_code",0, 0, 0, 0, 0, 0.6, 0),
	postal_code_prefix("postal_code_prefix",0, 0, 0, 0, 0, 0.6, 0),
	postal_code_suffix("postal_code_suffix",0, 0, 0, 0, 0, 0.6, 0),
	postal_town("postal_town",0, 0, 0, 0.2, 0, 0.3, 0),
	premise("premise",0, 0, 0, 0, 0, 0, 0),
	room("room",-1, 0, -1, 0.5, 0, 0, 0),
	route("route",0, -1, 0, 0.5, 0, 0.4, -1),
	street_address("street_address",0, -1, 0, 0.5, 0, 0.4, -1),
	street_number("street_number",0, -1, 0, 0.5, 0, 0.4, -1),
	sublocality("sublocality",0.1, 0, 0, 1, -1, 0, 0),
	sublocality_level_4("sublocality_level_4",0.1, 0, 0, 1, -1, 0, 0),
	sublocality_level_5("sublocality_level_5",0.1, 0, 0, 1, -1, 0, 0),
	sublocality_level_3("sublocality_level_3",0.1, 0, 0, 1, -1, 0, 0),
	sublocality_level_2("sublocality_level_2",0.1, 0, 0, 1, -1, 0, 0),
	sublocality_level_1("sublocality_level_1",0.1, 0, 0, 1, -1, 0, 0),
	subpremise("subpremise",0, 0, 0, 0, 0, 0, 0);
	
	public final int NUM_OF_CATEGORIES = 7;
	double nature;
	double sport;
	double isStore;
	double urban;
	double education;
	double transport;
	double isFood;
	private String value;

	private PlacesTypes(String value, double nature, double sport, double isStore, double urban, double education, double transport,
			double food) {
		this.value = value;
		this.nature = nature;
		this.sport = sport;
		this.isStore = isStore;
		this.urban = urban;
		this.education = education;
		this.transport = transport;
		this.isFood = food;
	}
	
	public double[][] getVector() {
		double[][] vector = new double[1][NUM_OF_CATEGORIES];
		vector[0][0] = nature;
		vector[0][1] = sport;
		vector[0][2] = isStore;
		vector[0][3] = urban;
		vector[0][4] = education;
		vector[0][5] = transport;
		vector[0][6] = isFood;
		return vector;
	}
	
	@Override
	public String toString() {
		return value;
	}
}