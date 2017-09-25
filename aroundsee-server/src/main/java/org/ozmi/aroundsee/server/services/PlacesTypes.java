package org.ozmi.aroundsee.server.services;

public enum PlacesTypes {
	accounting(0, 0, 0, 0, 0, 0, 0), 
	airport(0, 0, 0, 1, 0, 1, 0.5), 
	campground(1, 0.5, 0, -1, 0, 0, 0.5), 
	amusement_park(0.5, 0.5, 0, 0, 0.5, 0,0), 
	bicycle_store(0.5, 1, 1, 0.5, 0, 1, 0), 
	aquarium(1, 0, 0, 0.5, 0.5, 0, 0), 
	art_gallery(0, 0, 0, 1, 1, 0, 0), 
	atm(0, 0, 0, 0, 0, 0, 0), 
	bank(0, 0, 0, 0, 0, 0, 0),
	bakery(0, 0, 0, 0, 0, 0, 0), 
	bar(0, 0, 0, 0, 0, 0, 0), 
	cafe(0, 0, 0, 0, 0, 0, 0), 
	beauty_salon(0, 0, 0, 0, 0, 0, 0), 
	book_store(0, 0, 0, 0, 0, 0, 0), 
	bowling_alley(0, 0, 0, 0, 0, 0, 0), 
	bus_station(0, 0, 0, 0, 0, 0, 0), 
	car_dealer(0, 0, 0, 0, 0, 0, 0), 
	car_rental(0, 0, 0, 0, 0, 0, 0), 
	car_repair(0, 0, 0, 0, 0, 0, 0), 
	car_wash(0, 0, 0, 0, 0, 0, 0), 
	casino(0, 0, 0, 0, 0, 0, 0), 
	cemetery(0, 0, 0, 0, 0, 0, 0), 
	courthouse(0, 0, 0, 0, 0, 0, 0), 
	church(0, 0, 0, 0, 0, 0, 0), 
	city_hall(0, 0, 0, 0, 0, 0, 0), 
	embassy(0, 0, 0, 0, 0, 0, 0), 
	fire_station(0, 0, 0, 0, 0, 0, 0), 
	clothing_store(0, 0, 0, 0, 0, 0, 0), 
	convenience_store(0, 0, 0, 0, 0, 0, 0), 
	doctor(0, 0, 0, 0, 0, 0, 0), 
	dentist(0, 0, 0, 0, 0, 0, 0), 
	electrician(0, 0, 0, 0, 0, 0, 0), 
	electronics_store(0, 0, 0, 0, 0, 0, 0), 
	department_store(0, 0, 0, 0, 0, 0, 0), 
	florist(0, 0, 0, 0, 0, 0, 0), 
	funeral_home(0, 0, 0, 0, 0, 0, 0), 
	furniture_store(0, 0, 0, 0, 0, 0, 0), 
	gas_station(0, 0, 0, 0, 0, 0, 0), 
	gym(0, 0, 0, 0, 0, 0, 0), 
	hair_care(0, 0, 0, 0, 0, 0, 0), 
	hardware_store(0, 0, 0, 0, 0, 0, 0), 
	hindu_temple(0, 0, 0, 0, 0, 0, 0), 
	home_goods_store(0, 0, 0, 0, 0, 0, 0), 
	hospital(0, 0, 0, 0, 0, 0, 0), 
	insurance_agency(0, 0, 0, 0, 0, 0, 0), 
	jewelry_store(0, 0, 0, 0, 0, 0, 0), 
	laundry(0, 0, 0, 0, 0, 0, 0), 
	lawyer(0, 0, 0, 0, 0, 0, 0), 
	library(0, 0, 0, 0, 0, 0, 0), 
	liquor_store(0, 0, 0, 0, 0, 0, 0), 
	local_government_office(0, 0, 0, 0, 0, 0, 0), 
	locksmith(0, 0, 0, 0, 0, 0, 0), 
	lodging(0, 0, 0, 0, 0, 0, 0), 
	meal_delivery(0, 0, 0, 0, 0, 0, 0), 
	meal_takeaway(0, 0, 0, 0, 0, 0, 0), 
	mosque(0, 0, 0, 0, 0, 0, 0), 
	movie_rental(0, 0, 0, 0, 0, 0, 0), 
	movie_theater(0, 0, 0, 0, 0, 0, 0), 
	moving_company(0, 0, 0, 0, 0, 0, 0), 
	museum(0, 0, 0, 0, 0, 0, 0), 
	night_club(0, 0, 0, 0, 0, 0, 0), 
	painter(0, 0, 0, 0, 0, 0, 0), 
	park(0, 0, 0, 0, 0, 0, 0), 
	parking(0, 0, 0, 0, 0, 0, 0), 
	pet_store(0, 0, 0, 0, 0, 0, 0), 
	pharmacy(0, 0, 0, 0, 0, 0, 0), 
	physiotherapist(0, 0, 0, 0, 0, 0, 0), 
	plumber(0, 0, 0, 0, 0, 0, 0), 
	police(0, 0, 0, 0, 0, 0, 0), 
	post_office(0, 0, 0, 0, 0, 0, 0), 
	real_estate_agency(0, 0, 0, 0, 0, 0, 0), 
	restaurant(0, 0, 0, 0, 0, 0, 0), 
	roofing_contractor(0, 0, 0, 0, 0, 0, 0), 
	rv_park(0, 0, 0, 0, 0, 0, 0), 
	school(0, 0, 0, 0, 0, 0, 0), 
	shoe_store(0, 0, 0, 0, 0, 0, 0), 
	shopping_mall(0, 0, 0, 0, 0, 0, 0), 
	spa(0, 0, 0, 0, 0, 0, 0), 
	stadium(0, 0, 0, 0, 0, 0, 0), 
	storage(0, 0, 0, 0, 0, 0, 0), 
	store(0, 0, 0, 0, 0, 0, 0), 
	subway_station(0, 0, 0, 0, 0, 0, 0), 
	synagogue(0, 0, 0, 0, 0, 0, 0), 
	taxi_stand(0, 0, 0, 0, 0, 0, 0), 
	train_station(0, 0, 0, 0, 0, 0, 0), 
	transit_station(0, 0, 0, 0, 0, 0, 0), 
	travel_agency(0, 0, 0, 0, 0, 0, 0), 
	university(0, 0, 0, 0, 0, 0, 0), 
	veterinary_care(0, 0, 0, 0, 0, 0, 0), 
	zoo(0, 0, 0, 0, 0, 0, 0),
	administrative_area_level_1(0, 0, 0, 0, 0, 0, 0),
	administrative_area_level_2(0, 0, 0, 0, 0, 0, 0),
	administrative_area_level_3(0, 0, 0, 0, 0, 0, 0),
	administrative_area_level_4(0, 0, 0, 0, 0, 0, 0),
	administrative_area_level_5(0, 0, 0, 0, 0, 0, 0),
	colloquial_area(0, 0, 0, 0, 0, 0, 0),
	country(0, 0, 0, 0, 0, 0, 0),
	establishment(0, 0, 0, 0, 0, 0, 0),
	finance(0, 0, 0, 0, 0, 0, 0),
	floor(0, 0, 0, 0, 0, 0, 0),
	food(0, 0, 0, 0, 0, 0, 0),
	general_contractor(0, 0, 0, 0, 0, 0, 0),
	geocode(0, 0, 0, 0, 0, 0, 0),
	health(0, 0, 0, 0, 0, 0, 0),
	intersection(0, 0, 0, 0, 0, 0, 0),
	locality(0, 0, 0, 0, 0, 0, 0),
	natural_feature(0, 0, 0, 0, 0, 0, 0),
	neighborhood(0, 0, 0, 0, 0, 0, 0),
	place_of_worship(0, 0, 0, 0, 0, 0, 0),
	political(0, 0, 0, 0, 0, 0, 0),
	point_of_interest(0, 0, 0, 0, 0, 0, 0),
	post_box(0, 0, 0, 0, 0, 0, 0),
	postal_code(0, 0, 0, 0, 0, 0, 0),
	postal_code_prefix(0, 0, 0, 0, 0, 0, 0),
	postal_code_suffix(0, 0, 0, 0, 0, 0, 0),
	postal_town(0, 0, 0, 0, 0, 0, 0),
	premise(0, 0, 0, 0, 0, 0, 0),
	room(0, 0, 0, 0, 0, 0, 0),
	route(0, 0, 0, 0, 0, 0, 0),
	street_address(0, 0, 0, 0, 0, 0, 0),
	street_number(0, 0, 0, 0, 0, 0, 0),
	sublocality(0, 0, 0, 0, 0, 0, 0),
	sublocality_level_4(0, 0, 0, 0, 0, 0, 0),
	sublocality_level_5(0, 0, 0, 0, 0, 0, 0),
	sublocality_level_3(0, 0, 0, 0, 0, 0, 0),
	sublocality_level_2(0, 0, 0, 0, 0, 0, 0),
	sublocality_level_1(0, 0, 0, 0, 0, 0, 0),
	subpremise(0, 0, 0, 0, 0, 0, 0);
	
	public final int NUM_OF_CATEGORIES = 7;
	double nature;
	double sport;
	double isStore;
	double urban;
	double education;
	double transport;
	double isFood;

	private PlacesTypes(double nature, double sport, double isStore, double urban, double education, double transport,
			double food) {
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
}