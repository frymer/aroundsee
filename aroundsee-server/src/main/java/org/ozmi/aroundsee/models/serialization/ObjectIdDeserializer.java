package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
@Deprecated
public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

	@Override
	 public ObjectId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
	    JsonNode n = (JsonNode)p.readValueAsTree();
//	    return new ObjectId(n.get("timestamp").asInt(), n.get("machineIdentifier").asInt(), (short) n.get("processIdentifier").asInt(), n.get("counter").asInt());
	    return new ObjectId(n.get("$oid").asText());
	}
	
}