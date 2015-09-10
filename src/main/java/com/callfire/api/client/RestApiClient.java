package com.callfire.api.client;

import com.callfire.api.client.model.BaseModel;
import com.callfire.api.client.model.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.BASE_PATH;
import static com.callfire.api.client.ClientConstants.USER_AGENT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * REST client which makes HTTP calls to Callfire service
 */
public class RestApiClient {
    private static final Logger LOGGER = new Logger(RestApiClient.class);

    private HttpClient httpClient;
    private JsonConverter jsonConverter;
    private UsernamePasswordCredentials credentials;

    public RestApiClient(String username, String password) {
        jsonConverter = new JsonConverter();
        credentials = new UsernamePasswordCredentials(username, password);
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
        return get(path, type, new ArrayList<>());
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
     * Performs POST request with body to specified path
     *
     * @param path    request path
     * @param type    response entity type
     * @param request request body
     * @param <T>     response entity type
     * @param <E>     request entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T, E> T post(String path, TypeReference<T> type, E request) {
        try {
            String uri = BASE_PATH + path;
            RequestBuilder requestBuilder = RequestBuilder.post(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType());
            if (request != null) {
                HttpEntity httpEntity = EntityBuilder.create().setText(jsonConverter.serialize(request)).build();
                requestBuilder.setEntity(httpEntity);
                logDebugPrettyJson("POST request to {} entity \n{}", uri, request);
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
     * @param request request body
     * @param <T>     response entity type
     * @param <E>     request entity type
     * @return pojo mapped from json
     * @throws CallfireApiException    in case API cannot be queried for some reason
     * @throws CallfireClientException in case error has occurred in client
     */
    public <T, E> T put(String path, TypeReference<T> type, E request) {
        try {
            String uri = BASE_PATH + path;
            HttpEntity httpEntity = EntityBuilder.create().setText(jsonConverter.serialize(request)).build();
            HttpUriRequest httpRequest = RequestBuilder.put(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                .setEntity(httpEntity)
                .build();
            logDebugPrettyJson("PUT request to {} entity \n{}", uri, request);

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
        try {
            String uri = BASE_PATH + path;
            LOGGER.debug("DELETE request to {}", uri);
            doRequest(RequestBuilder.delete(uri).build(), new TypeReference<Void>() {
            });
            LOGGER.debug("delete executed");
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    private <T> T doRequest(HttpUriRequest request, TypeReference<T> type) throws IOException {
        HttpUriRequest httpRequest = addAuthorizationHeader(request);
        HttpResponse response = httpClient.execute(httpRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        if (response.getEntity() != null) {
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (statusCode >= 400) {
                createAndThrowCallfireApiException(result);
            }
            T entity = jsonConverter.deserialize(result, type);
            logDebugPrettyJson("received entity \n{}", entity);

            return entity;
        } else {
            LOGGER.debug("received http code {} with null entity, returning null", statusCode);
            return null;
        }
    }

    private void createAndThrowCallfireApiException(String result) throws CallfireApiException {
        throw new CallfireApiException(jsonConverter.deserialize(result,
            new TypeReference<ErrorMessage>() {
            }));
    }

    private HttpUriRequest addAuthorizationHeader(HttpUriRequest request) throws CallfireClientException {
        try {
            request.addHeader(new BasicScheme().authenticate(credentials, request, null));
            return request;
        } catch (AuthenticationException e) {
            throw new CallfireClientException(e);
        }
    }

    // makes extra deserialization to get pretty json string, enable only in case of debugging
    private void logDebugPrettyJson(String message, Object... params) throws JsonProcessingException {
        if (LOGGER.isDebugEnabled()) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof BaseModel) {
                    String prettyJson = jsonConverter.getMapper().writerWithDefaultPrettyPrinter()
                        .writeValueAsString(params[i]);
                    params[i] = prettyJson;
                }
            }
            LOGGER.debug(message, params);
        }
    }
}
