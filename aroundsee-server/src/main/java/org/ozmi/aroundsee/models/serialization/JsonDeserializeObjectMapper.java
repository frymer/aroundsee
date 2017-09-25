package org.ozmi.aroundsee.models.serialization;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonDeserializeObjectMapper extends ObjectMapper {
	public JsonDeserializeObjectMapper() {
		SimpleModule objectIdDeserializerModule = new SimpleModule("ObjectIdBsonDeserializer", new Version(1, 0, 0, null, null, null));
		objectIdDeserializerModule.addDeserializer(ObjectId.class, new ObjectIdBsonDeserializer());
		this.registerModule(objectIdDeserializerModule);
	}
}
