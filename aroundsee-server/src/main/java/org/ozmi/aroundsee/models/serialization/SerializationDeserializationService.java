package org.ozmi.aroundsee.models.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationDeserializationService {
	private static Map<DeserializeFormat, ObjectMapper> deserializeObjectMappers;
	private static Map<SerializeFormat, ObjectMapper> serializeObjectMappers;

	private static Map<DeserializeFormat, ObjectMapper> getDeserializeObjectMappers() {
		if (deserializeObjectMappers == null) {
			deserializeObjectMappers = new HashMap<>(DeserializeFormat.values().length);
			deserializeObjectMappers.put(DeserializeFormat.Json, new JsonDeserializeObjectMapper());
			deserializeObjectMappers.put(DeserializeFormat.MongoBson, new MongoBsonDeserializeObjectMapper());
			deserializeObjectMappers.put(DeserializeFormat.Default, new ObjectMapper());
		}

		return deserializeObjectMappers;
	}

	public static Map<SerializeFormat, ObjectMapper> getSerializeObjectMappers() {
		if (serializeObjectMappers == null) {
			serializeObjectMappers = new HashMap<>(DeserializeFormat.values().length);
			serializeObjectMappers.put(SerializeFormat.Json, new JsonSerializeObjectMapper());
			serializeObjectMappers.put(SerializeFormat.MongoBson, new MongoBsonSerializeObjectMapper());
			serializeObjectMappers.put(SerializeFormat.Default, new ObjectMapper());
		}

		return serializeObjectMappers;
	}

	public static <T> T deserializeAs(byte[] input, Class<T> clazz, DeserializeFormat format)
			throws UnsupportedOperationException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = getDeserializeObjectMappers().get(format);
		if (mapper == null) {
			throw new UnsupportedOperationException("Deserialization format is not supported!");
		}

		T output = mapper.readValue(input, clazz);

		return output;
	}

	public static <T> T tryDeserializeAs(byte[] input, Class<T> clazz, DeserializeFormat format) {
		ObjectMapper mapper = getDeserializeObjectMappers().get(format);
		if (mapper == null) {
			return null;
		}

		T output;
		try {
			output = mapper.readValue(input, clazz);
		} catch (IOException e) {
			return null;
		}

		return output;
	}

	public static <T> T deserializeAs(InputStream input, Class<T> clazz, DeserializeFormat format)
			throws UnsupportedOperationException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = getDeserializeObjectMappers().get(format);
		if (mapper == null) {
			throw new UnsupportedOperationException("Deserialization format is not supported!");
		}

		T output = mapper.readValue(input, clazz);

		return output;
	}

	public static <T> T tryDeserializeAs(InputStream input, Class<T> clazz, DeserializeFormat format) {
		ObjectMapper mapper = getDeserializeObjectMappers().get(format);
		if (mapper == null) {
			return null;
		}

		T output;
		try {
			output = mapper.readValue(input, clazz);
		} catch (IOException e) {
			return null;
		}

		return output;
	}

	public static <T> T deserializeAs(String input, Class<T> clazz, DeserializeFormat format)
			throws UnsupportedOperationException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = getDeserializeObjectMappers().get(format);
		if (mapper == null) {
			throw new UnsupportedOperationException("Deserialization format is not supported!");
		}

		T output = mapper.readValue(input, clazz);

		return output;
	}

	public static <T> T tryDeserializeAs(String input, Class<T> clazz, DeserializeFormat format) {
		ObjectMapper mapper = getDeserializeObjectMappers().get(format);
		if (mapper == null) {
			return null;
		}

		T output;
		try {
			output = mapper.readValue(input, clazz);
		} catch (IOException e) {
			return null;
		}

		return output;
	}

	public static <T> boolean streamSerializeTo(T input, OutputStream output, SerializeFormat format)
			throws UnsupportedOperationException, JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = getSerializeObjectMappers().get(format);
		if (mapper == null) {
			throw new UnsupportedOperationException("Serialization format is not supported!");
		}

		mapper.writeValue(output, input);
		return true;
	}

	public static <T> boolean tryStreamSerializeTo(T input, OutputStream output, SerializeFormat format) {
		ObjectMapper mapper = getSerializeObjectMappers().get(format);
		if (mapper == null) {
			return false;
		} else {
			try {
				mapper.writeValue(output, input);
				return true;
			} catch (IOException e) {
				return false;
			}
		}
	}

	public static <T> byte[] bytesSerializeTo(T input, SerializeFormat format)
			throws UnsupportedOperationException, JsonProcessingException {
		ObjectMapper mapper = getSerializeObjectMappers().get(format);
		if (mapper == null) {
			throw new UnsupportedOperationException("Serialization format is not supported!");
		}

		byte[] output = mapper.writeValueAsBytes(input);
		return output;
	}

	public static <T> byte[] tryBytesSerializeTo(T input, SerializeFormat format) {
		ObjectMapper mapper = getSerializeObjectMappers().get(format);
		if (mapper == null) {
			return null;
		}

		byte[] output;
		try {
			output = mapper.writeValueAsBytes(input);
		} catch (JsonProcessingException e) {
			output = null;
		}

		return output;
	}

	public static <T> String serializeTo(T input, SerializeFormat format)
			throws UnsupportedOperationException, JsonProcessingException {
		ObjectMapper mapper = getSerializeObjectMappers().get(format);
		if (mapper == null) {
			throw new UnsupportedOperationException("Serialization format is not supported!");
		}

		String output = mapper.writeValueAsString(input);
		return output;
	}

	public static <T> String trySerializeTo(T input, SerializeFormat format) {
		ObjectMapper mapper = getSerializeObjectMappers().get(format);
		if (mapper == null) {
			return null;
		}

		String output;
		try {
			output = mapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			output = null;
		}

		return output;
	}
}