package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectIdBsonSerializer extends  JsonSerializer<ObjectId> {

	@Override
	public void serialize(ObjectId value, JsonGenerator jgen, SerializerProvider provider) throws IOException{
		if (value == null) {
			jgen.writeNull();
		} else {
			jgen.writeStartObject();
			jgen.writeStringField("$oid", value.toString());
			jgen.writeEndObject();
		}
	}
}