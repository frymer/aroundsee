package org.ozmi.aroundsee.bl.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.ozmi.aroundsee.models.serialization.DeserializeFormat;
import org.ozmi.aroundsee.models.serialization.SerializationDeserializationService;
import org.ozmi.aroundsee.models.serialization.SerializeFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoRepositoryImpl<T extends Identifiable> extends RepositoryImpl<T> {
	private MongoCollection<Document> _collection;
	private Class<T> _type;

	@Override
	protected Class<T> getGenericType() {
		return _type;
	}

	public MongoRepositoryImpl(MongoCollection<Document> collection, Class<T> type) {
		this._collection = collection;
		this._type = type;
	}

	private T pasrseDocumentToT(Document doc) throws JsonParseException, JsonMappingException, IOException {
		T result = SerializationDeserializationService.deserializeAs(doc.toJson(), getGenericType(),
				DeserializeFormat.MongoBson);
		return result;
	}

	@Override
	public List<T> all() throws JsonParseException, JsonMappingException, IOException {
		List<T> results = new ArrayList<T>();
		Iterator<Document> itemsIterator = _collection.find().iterator();

		while (itemsIterator.hasNext()) {
			T object = this.pasrseDocumentToT(itemsIterator.next());

			results.add(object);
		}

		return results;
	}

	@Override
	public void update(T object) throws JsonProcessingException {
		Document documentToUpdate = Document
				.parse(SerializationDeserializationService.serializeTo(object, SerializeFormat.MongoBson));
		BasicDBObject idFilter = new BasicDBObject("_id", documentToUpdate.getObjectId("_id"));
		_collection.replaceOne(idFilter, documentToUpdate);
	}

	@Override
	public void create(T object) throws JsonProcessingException {
		Document documentToInsert = Document
				.parse(SerializationDeserializationService.serializeTo(object, SerializeFormat.MongoBson));

		_collection.insertOne(documentToInsert);
	}

	@Override
	public T read(Object id) throws JsonParseException, JsonMappingException, IOException {
		BasicDBObject idFilter = new BasicDBObject("_id", new ObjectId((String) id));
		Document res = _collection.find(idFilter).first();
		if (res == null) {
			return null;
		} else {
			return (T) SerializationDeserializationService.deserializeAs(res.toJson(),
					getGenericType(), DeserializeFormat.MongoBson);
		}
	}

	@Override
	public void delete(Object id) {
		BasicDBObject idFilter = new BasicDBObject("_id", new ObjectId((String) id));
		_collection.deleteOne(idFilter);
	}

	@Override
	public List<T> query(String query) throws Throwable {
		List<T> results = new ArrayList<T>();
		MongoCursor<Document> cursor = _collection.find(Document.parse(query)).iterator();
		while (cursor.hasNext()) {
			Document item = cursor.next();
			results.add(SerializationDeserializationService.deserializeAs(item.toJson(), getGenericType(),
					DeserializeFormat.MongoBson));
		}

		return results;
	}
}
