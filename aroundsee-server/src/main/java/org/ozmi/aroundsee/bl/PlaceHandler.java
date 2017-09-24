package org.ozmi.aroundsee.bl;

import org.ozmi.aroundsee.bl.repository.RepositoryImpl;
import org.ozmi.aroundsee.models.Place;

public class PlaceHandler {
	private RepositoryImpl<Place> _repositoryImpl;
	
	public PlaceHandler(RepositoryImpl<Place> repository) {
		this._repositoryImpl = repository;
	}
}
