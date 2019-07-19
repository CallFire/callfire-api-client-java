package com.callfire.api.client;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Getter;
import lombok.Setter;

/**
 * JSON serializer/deserializer
 *
 * @since 1.0
 */
@Getter
@Setter
public class JsonConverter {
    private ObjectMapper mapper;

    public JsonConverter() {
        mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            // there currently no alternative to this deprecated feature
            .disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setSerializationInclusion(Include.NON_NULL);
    }

    /**
     * Serialize the given Java object into JSON string.
     *
     * @param obj object to serialize
     * @return representation in JSON string
     * @throws CallfireClientException in case object cannot be serialized
     */
    public String serialize(Object obj) throws CallfireClientException {
        try {
            return obj == null ? null : mapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Deserialize the given JSON string to Java object.
     *
     * @param body The JSON string
     * @param type The type of deserialized entity
     * @param <T>  type of deserialized entity
     * @return deserialized Java object
     * @throws CallfireClientException in case body cannot be deserialized
     */
    public <T> T deserialize(String body, TypeReference<T> type) throws CallfireClientException {
        try {
            return mapper.readValue(body, type);
        }
        catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }
}
