package org.ozmi.aroundsee.bl.repository;

import java.util.List;

import org.ozmi.aroundsee.models.Place;


public class PlaceRepository extends RepositoryObject implements Repository<Place> {
	
	public PlaceRepository(RepositoryImpl<Place> implementation) {
		super(implementation);
	}
	
	public List<Place> all() throws Throwable {
		return _repository.all();
	}

	public void update(Place object) throws Throwable {
		_repository.update(object);	
	}

	public void create(Place object) throws Throwable {
		_repository.create(object);
	}

	public Place read(Object id) throws Throwable {
		return (Place) _repository.read(id);
	}

	public void delete(Object id) throws Throwable {
		_repository.delete(id);	
	}

	@Override
	public List<Place> query(String query) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
}
