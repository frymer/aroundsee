package org.ozmi.aroundsee.bl;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.ozmi.aroundsee.bl.repository.Identifiable;
import org.ozmi.aroundsee.bl.repository.MongoRepositoryImpl;
import org.ozmi.aroundsee.bl.repository.PlaceRepository;
import org.ozmi.aroundsee.bl.repository.Repository;
import org.ozmi.aroundsee.bl.repository.RepositoryImpl;
import org.ozmi.aroundsee.bl.repository.UserRepository;
import org.ozmi.aroundsee.models.AroundSeeUser;
import org.ozmi.aroundsee.models.Place;

import com.mongodb.client.MongoCollection;

public class RepositoryImplHanlder<T extends Identifiable> extends Configurable {
	private static Map<String, PersistenceHandler> _handlers;
	private Class<T> _type;

	static {
		_handlers = new HashMap<String, PersistenceHandler>();
		_handlers.put("mongodb", new MongoHandler());
	}

	private Class<T> getGenericType() {
		return this._type;
	}

	public Repository getRepository() {
		String persistence = this.getConf().getString("server.persistence");
		RepositoryImpl<T> impl;
		Repository result;

		switch (persistence) {
		case "mongodb":
			String collectionName = this.getConf().getString("db.collections." + this.getGenericType().getSimpleName());

			MongoCollection<Document> collection = ((MongoHandler) _handlers.get(persistence)).getDB()
					.getCollection(collectionName);
			impl = new MongoRepositoryImpl<T>(collection, this.getGenericType());

			switch (this.getGenericType().getSimpleName()) {
			case ("Place"): {
				result = new PlaceRepository((RepositoryImpl<Place>) impl);
				break;
			}
			case ("AroundSeeUser"): {
				result = new UserRepository((RepositoryImpl<AroundSeeUser>) impl);
				break;
			}
			default: {
				result = null; 
			}
			}

			break;

		default:
			result = null;

			break;
		}

		return result;
	}

	public RepositoryImplHanlder(Class<T> type) {
		this._type = type;
	}
}
