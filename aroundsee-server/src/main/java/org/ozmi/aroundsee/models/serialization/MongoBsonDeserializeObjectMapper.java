package org.ozmi.aroundsee.models.serialization;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class MongoBsonDeserializeObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 484507645594924513L;

	public MongoBsonDeserializeObjectMapper() {
		SimpleModule objectIdDeserializerModule = new SimpleModule("ObjectIdBsonDeserializer", new Version(1, 0, 0, null, null, null));
	    objectIdDeserializerModule.addDeserializer(ObjectId.class, new ObjectIdBsonDeserializer());
	    this.registerModule(objectIdDeserializerModule);
		
	    SimpleModule longDeserializerModule = new SimpleModule("LongBsonDeserializer", new Version(1, 0, 0, null, null, null));
	    longDeserializerModule.addDeserializer(Long.class, new LongBsonDeserializer());
	    this.registerModule(longDeserializerModule);
	}
}
