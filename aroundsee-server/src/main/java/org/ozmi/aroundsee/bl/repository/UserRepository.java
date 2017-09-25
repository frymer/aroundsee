package org.ozmi.aroundsee.bl.repository;

import java.util.List;

import org.ozmi.aroundsee.models.AroundSeeUser;
import org.ozmi.aroundsee.models.Place;

public class UserRepository extends RepositoryObject implements Repository<AroundSeeUser> {
	private final String FIND_BY_USER_N_PASS = "{ \"$and\": [{\"username\": \"%s\"}, {\"password\": \"%s\"}]}";
	public UserRepository(RepositoryImpl<AroundSeeUser> implementation) {
		super(implementation);
	}
	
	public List<AroundSeeUser> all() throws Throwable {
		return _repository.all();
	}

	public void update(AroundSeeUser object) throws Throwable {
		_repository.update(object);	
	}

	public void create(AroundSeeUser object) throws Throwable {
		_repository.create(object);
	}

	public AroundSeeUser read(Object id) throws Throwable {
		return (AroundSeeUser) _repository.read(id);
	}

	public void delete(Object id) throws Throwable {
		_repository.delete(id);	
	}

	@Override
	public List<AroundSeeUser> query(String query) throws Throwable {
		return _repository.query(query);
	}

	public AroundSeeUser getUserByUsernameAndPass(String user, String pass) throws Throwable {
		List<AroundSeeUser> lRes = this.query(String.format(FIND_BY_USER_N_PASS, user, pass));
		if ((lRes != null) && (!lRes.isEmpty())) {
			return lRes.get(0);
		} else {
			return null;
		}
	}

	public boolean deleteByUsernameAndPass(String userName, String password) throws Throwable {
		List<AroundSeeUser> users = this.query(String.format(FIND_BY_USER_N_PASS, userName, password));
		if (users.size() == 1) {
			AroundSeeUser user = users.get(0);
			this.delete(user.get_id());
		}
		
		return false;
	}
	
	public void addToUserLikedPlaces(Object id, Place newPlace) throws Throwable {
		AroundSeeUser p = this.read(id);
		p.getLikedPlaces().add(newPlace);
		this.update(p);
	}
}
