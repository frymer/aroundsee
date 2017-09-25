package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class LongValuesDeserializer extends JsonDeserializer<Long>{

	@Override
	public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
	    JsonNode n = (JsonNode)p.readValueAsTree();
	    return new Long(n.get("$numberLong").asText());
	}
}
