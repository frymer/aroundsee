package org.ozmi.aroundsee.bl.repository;

import java.util.List;

import org.bson.Document;
import org.ozmi.aroundsee.models.AroundSeeUser;

public class UserRepository extends RepositoryObject implements Repository<AroundSeeUser> {
	private final String FIND_BY_USER_N_PASS = "{ \"$and\": [{\"username\": \"%s\"}, {\"password\": \"%s\"}]";
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

	public boolean doesUserExist(String user, String pass) throws Throwable {
		return this.query(String.format(FIND_BY_USER_N_PASS, user, pass)).size() > 0;
	}

	public boolean deleteByUsernameAndPass(String userName, String password) throws Throwable {
		List<AroundSeeUser> users = this.query(String.format(FIND_BY_USER_N_PASS, userName, password));
		if (users.size() == 1) {
			AroundSeeUser user = users.get(0);
			this.delete(user.getId());
		}
		
		return false;
	}
}
