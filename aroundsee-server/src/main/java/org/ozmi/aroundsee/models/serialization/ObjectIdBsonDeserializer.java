package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ObjectIdBsonDeserializer extends StdDeserializer<ObjectId> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectIdBsonDeserializer() {
		this(ObjectId.class);
	}

	public ObjectIdBsonDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public ObjectId deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node.get("$oid") != null) {
			return new ObjectId(node.get("$oid").asText());
		} else if (node.asText() != null) {
			if (!node.asText().isEmpty()) {
				return new ObjectId(node.asText());
			} else {
				return new ObjectId();
			}
		}

		throw new JsonMappingException(String.format("Error mapping ObjectId value for %s", node.toString()));
	}
}