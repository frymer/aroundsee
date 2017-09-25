package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LongValuesBsonSerializer extends JsonSerializer<Long> {

	@Override
	public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		if (value == null) {
			jgen.writeNull();
		} else {
			jgen.writeStartObject();
			jgen.writeStringField("$numberLong", value.toString());
			jgen.writeEndObject();
		}
	}
}