package org.ozmi.aroundsee.models.serialization;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class MongoBsonSerializeObjectMapper extends ObjectMapper {
	public MongoBsonSerializeObjectMapper() {
		SimpleModule objectIdSerializerModule = new SimpleModule("ObjectIdBsonSerializer", new Version(1, 0, 0, null, null, null));
		objectIdSerializerModule.addSerializer(ObjectId.class, new ObjectIdBsonSerializer());
	    this.registerModule(objectIdSerializerModule);
		
	    SimpleModule longSerializerModule = new SimpleModule("LongValuesBsonSerializer", new Version(1, 0, 0, null, null, null));
	    longSerializerModule.addSerializer(Long.class, new LongValuesBsonSerializer());
	    this.registerModule(longSerializerModule);
	}
}
