package com.callfire.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;

public class JsonConverter {
    private static final Logger LOGGER = new Logger(JsonConverter.class);
    private ObjectMapper mapper;

    public JsonConverter() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JodaModule());
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
        } catch (Exception e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Deserialize the given JSON string to Java object.
     *
     * @param body The JSON string
     * @param type The type to deserialize body
     * @return deserialized Java object
     * @throws CallfireClientException in case body cannot be deserialized
     */
    public <T> T deserialize(String body, TypeReference<T> type) throws CallfireClientException {
        try {
            return mapper.readValue(body, type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
