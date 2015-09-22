package com.callfire.api.client;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.GetRequest;
import com.callfire.api.client.auth.Authentication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.BASE_PATH;
import static com.callfire.api.client.ClientConstants.Type.ERROR_MESSAGE_TYPE;
import static com.callfire.api.client.ClientConstants.Type.STRING_TYPE;
import static com.callfire.api.client.ClientConstants.USER_AGENT;
import static com.callfire.api.client.ClientUtils.buildQueryParams;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * REST client which makes HTTP calls to Callfire service
 */
public class RestApiClient {
    private static final Logger LOGGER = new Logger(RestApiClient.class);

    private HttpClient httpClient;
    private JsonConverter jsonConverter;
    private Authentication authentication;

    public RestApiClient(Authentication authentication) {
        this.authentication = authentication;
        jsonConverter = new JsonConverter();
        httpClient = HttpClientBuilder.create().setUserAgent(USER_AGENT).build();
    }

    /**
     * Get Apache HTTP client
     *
     * @return http client
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Set Apache HTTP client
     *
     * @param httpClient http client
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Performs GET request to specified path
     *
     * @param path request path
     * @param type entity type
     * @param <T>  entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T> T get(String path, TypeReference<T> type) throws CallfireClientException, CallfireApiException {
        return get(path, type, new ArrayList<NameValuePair>());
    }

    /**
     * Performs GET request to specified path
     *
     * @param path    request path
     * @param type    entity type
     * @param request find request with query parameters
     * @param <T>     entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T> T get(String path, TypeReference<T> type, GetRequest request)
        throws CallfireClientException, CallfireApiException {
        List<NameValuePair> queryParams = buildQueryParams(request);
        return get(path, type, queryParams);
    }

    /**
     * Performs GET request to specified path
     *
     * @param path        request path
     * @param type        entity type
     * @param queryParams query parameters
     * @param <T>         entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T> T get(String path, TypeReference<T> type, List<NameValuePair> queryParams)
        throws CallfireClientException, CallfireApiException {
        try {
            String uri = BASE_PATH + path;
            LOGGER.debug("GET request to {} with params: {}", uri, queryParams);
            RequestBuilder requestBuilder = RequestBuilder.get(uri);
            requestBuilder.addParameters(queryParams.toArray(new NameValuePair[queryParams.size()]));

            return doRequest(requestBuilder.build(), type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Performs POST request to specified path with empty body
     *
     * @param path request path
     * @param type entity type
     * @param <T>  entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T> T post(String path, TypeReference<T> type) {
        return post(path, type, null);
    }

    /**
     * Performs POST request with binary body to specified path
     *
     * @param path   request path
     * @param type   response entity type
     * @param params request parameters
     * @param <T>    response entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T> T postFile(String path, TypeReference<T> type, Map<String, ?> params) {
        try {
            String uri = BASE_PATH + path;
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entityBuilder.addBinaryBody("file", (File) params.get("file"));
            if (params.get("name") != null) {
                entityBuilder.addTextBody("name", (String) params.get("name"));
            }
            RequestBuilder requestBuilder = RequestBuilder.post(uri)
                .setEntity(entityBuilder.build());
            LOGGER.debug("POST file upload request to {} with params {}", uri, params);

            return doRequest(requestBuilder.build(), type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Performs POST request with body to specified path
     *
     * @param path    request path
     * @param type    response entity type
     * @param payload request payload
     * @param <T>     response entity type
     * @param <E>     request payload type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T, E> T post(String path, TypeReference<T> type, E payload) {
        try {
            String uri = BASE_PATH + path;
            RequestBuilder requestBuilder = RequestBuilder.post(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType());
            if (payload != null) {
                HttpEntity httpEntity = EntityBuilder.create().setText(jsonConverter.serialize(payload)).build();
                requestBuilder.setEntity(httpEntity);
                logDebugPrettyJson("POST request to {} entity \n{}", uri, payload);
            } else {
                LOGGER.debug("POST request to {}", uri);
            }

            return doRequest(requestBuilder.build(), type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Performs PUT request with body to specified path
     *
     * @param path    request path
     * @param type    response entity type
     * @param payload request payload
     * @param <T>     response entity type
     * @param <E>     request payload type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T, E> T put(String path, TypeReference<T> type, E payload) {
        try {
            String uri = BASE_PATH + path;
            HttpEntity httpEntity = EntityBuilder.create().setText(jsonConverter.serialize(payload)).build();
            HttpUriRequest httpRequest = RequestBuilder.put(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                .setEntity(httpEntity)
                .build();
            logDebugPrettyJson("PUT request to {} entity \n{}", uri, payload);

            return doRequest(httpRequest, type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Performs DELETE request to specified path
     *
     * @param path request path
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(String path) {
        delete(path, new ArrayList<NameValuePair>());
    }

    /**
     * Performs DELETE request to specified path with query parameters
     *
     * @param path        request path
     * @param queryParams query parameters
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(String path, List<NameValuePair> queryParams) {
        try {
            String uri = BASE_PATH + path;
            LOGGER.debug("DELETE request to {} with params {}", uri, queryParams);
            RequestBuilder requestBuilder = RequestBuilder.delete(uri);
            requestBuilder.addParameters(queryParams.toArray(new NameValuePair[queryParams.size()]));
            doRequest(requestBuilder.build(), STRING_TYPE);
            LOGGER.debug("delete executed");
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T doRequest(HttpUriRequest request, TypeReference<T> type) throws IOException {
        HttpUriRequest httpRequest = authentication.apply(request);
        HttpResponse response = httpClient.execute(httpRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        if (response.getEntity() == null) {
            LOGGER.debug("received http code {} with null entity, returning null", statusCode);
            return null;
        }
        if (statusCode >= 400) {
            createAndThrowCallfireApiException(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }
        if (type.getType() == InputStream.class) {
            return (T) response.getEntity().getContent();
        }

        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        T entity = jsonConverter.deserialize(result, type);
        logDebugPrettyJson("received entity \n{}", entity);
        return entity;
    }

    private void createAndThrowCallfireApiException(String result) throws CallfireApiException {
        throw new CallfireApiException(jsonConverter.deserialize(result, ERROR_MESSAGE_TYPE));
    }

    // makes extra deserialization to get pretty json string, enable only in case of debugging
    private void logDebugPrettyJson(String message, Object... params) throws JsonProcessingException {
        if (LOGGER.isDebugEnabled()) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof CallfireModel) {
                    String prettyJson = jsonConverter.getMapper().writerWithDefaultPrettyPrinter()
                        .writeValueAsString(params[i]);
                    params[i] = prettyJson;
                }
            }
            LOGGER.debug(message, params);
        }
    }
}
