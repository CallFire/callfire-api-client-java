package com.callfire.api.client;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
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
        Class<?> superclass = request.getClass().getSuperclass();
        while (superclass != null) {
            readFields(request, params, superclass);
            superclass = superclass.getSuperclass();
        }
        readFields(request, params, request.getClass());
        return params;
    }

    private static void readFields(Object request, List<NameValuePair> params, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            readField(request, params, field);
        }
    }

    private static void readField(Object request, List<NameValuePair> params, Field field) {
        try {
            field.setAccessible(true);
            Object value = field.get(request);
            if (value != null) {
                if (field.isAnnotationPresent(ConvertToString.class) && value instanceof Iterable) {
                    value = StringUtils.join((Iterable) value, field.getAnnotation(ConvertToString.class).separator());
                    if (StringUtils.isEmpty((String) value)) {
                        return;
                    }
                }
                if (value instanceof Date) {
                    value = ((Date) value).getTime();
                }
                params.add(new BasicNameValuePair(field.getName(), value.toString()));
            }
        } catch (IllegalAccessException e) {
            throw new CallfireClientException(e);
        }
    }
}
