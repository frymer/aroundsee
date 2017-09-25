package org.ozmi.aroundsee.models;

import java.util.ArrayList;
import java.util.List;

import org.ozmi.aroundsee.bl.repository.Identifiable;

public class AroundSeeUser implements Identifiable {
	private String id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<Place> likedPlaces;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Object getId() {
		return id;
	}

	@Override
	public void setId(Object id) {
		this.id = (String) id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Place> getLikedPlaces() {
		if (this.likedPlaces == null) {
			this.likedPlaces = new ArrayList<Place>();
		}
		
		return likedPlaces;
	}

	public void setLikedPlaces(List<Place> likedPlaces) {
		this.likedPlaces = likedPlaces;
	}
}
