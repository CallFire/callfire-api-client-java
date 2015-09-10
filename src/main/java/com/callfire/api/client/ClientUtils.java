package com.callfire.api.client;

import com.callfire.api.client.model.BaseModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utility class
 */
public final class ClientUtils {
    private ClientUtils() {
    }

    /**
     * Add query param to map if it's value not null
     *
     * @param name        parameter name
     * @param value       parameter value
     * @param queryParams parameters map
     */
    public static void addQueryParamIfSet(String name, Object value, List<NameValuePair> queryParams) {
        if (name != null && value != null && queryParams != null) {
            queryParams.add(new BasicNameValuePair(name, Objects.toString(value)));
        }
    }

    /**
     * Method traverses request object using reflection and build List<NameValuePair> from it
     *
     * @param request request
     * @param <T>     type of request
     * @return list contains query parameters
     * @throws CallfireClientException in case IllegalAccessException occurred
     */
    public static <T extends BaseModel> List<NameValuePair> buildQueryParams(T request)
        throws CallfireClientException {
        List<NameValuePair> params = new ArrayList<>();
        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(request);
                if (value != null) {
                    params.add(new BasicNameValuePair(field.getName(), value.toString()));
                }
            } catch (IllegalAccessException e) {
                throw new CallfireClientException(e);
            }
        }
        return params;
    }
}
