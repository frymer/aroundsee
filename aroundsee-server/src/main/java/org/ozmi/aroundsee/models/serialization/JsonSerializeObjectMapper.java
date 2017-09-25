package org.ozmi.aroundsee.models.serialization;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonSerializeObjectMapper extends ObjectMapper {
	public JsonSerializeObjectMapper() {
		SimpleModule objectIdSerializerModule = new SimpleModule("ObjectIdJsonSerializer", new Version(1, 0, 0, null, null, null));
		objectIdSerializerModule.addSerializer(ObjectId.class, new ObjectIdJsonSerializer());
		this.registerModule(objectIdSerializerModule);
	}
}
