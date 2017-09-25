package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LongBsonDeserializer extends StdDeserializer<Long> {

	public LongBsonDeserializer() {
		this(Long.class);
	}
	
	public LongBsonDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node.get("$numberLong") != null) {
			return Long.parseLong(node.get("$numberLong").asText());
		} else if (node.asLong(-1) != -1) {
			return node.asLong();
		}
		
		throw new JsonMappingException(String.format("Error during long value mapping of %s", node.toString()));
	}
}