package com.callfire.api.client;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.ErrorMessage;
import com.callfire.api.client.auth.Authentication;
import com.callfire.api.client.auth.BasicAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.callfire.api.client.ClientConstants.*;
import static com.callfire.api.client.ClientUtils.buildQueryParams;
import static com.callfire.api.client.ModelType.of;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * REST client which makes HTTP calls to Callfire service
 *
 * @since 1.0
 */
public class RestApiClient {
    private static final Logger LOGGER = new Logger(RestApiClient.class);

    private HttpClient httpClient;
    private JsonConverter jsonConverter;
    private Authentication authentication;
    private SortedSet<RequestFilter> filters = new TreeSet<>();

    /**
     * REST API client constructor. Currently available authentication methods: {@link BasicAuth}
     *
     * @param authentication API authentication method
     */
    public RestApiClient(Authentication authentication) {
        this.authentication = authentication;
        jsonConverter = new JsonConverter();
        httpClient = buildHttpClient();
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
     * Get Jackson's json converter
     *
     * @return json converter
     */
    public JsonConverter getJsonConverter() {
        return jsonConverter;
    }

    /**
     * Performs GET request to specified path
     *
     * @param path request path
     * @param type return entity type
     * @param <T>  return entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T get(String path, TypeReference<T> type) {
        return get(path, type, Collections.<NameValuePair>emptyList());
    }

    /**
     * Performs GET request to specified path
     *
     * @param path    request path
     * @param type    return entity type
     * @param request finder request with query parameters
     * @param <T>     return entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T get(String path, TypeReference<T> type, CallfireModel request) {
        List<NameValuePair> queryParams = buildQueryParams(request);
        return get(path, type, queryParams);
    }

    /**
     * Performs GET request to specified path
     *
     * @param path        request path
     * @param type        return entity type
     * @param queryParams query parameters
     * @param <T>         return entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T get(String path, TypeReference<T> type, List<NameValuePair> queryParams) {
        try {
            String uri = getApiBasePath() + path;
            LOGGER.debug("GET request to {} with params: {}", uri, queryParams);
            RequestBuilder requestBuilder = RequestBuilder.get(uri)
                .addParameters(queryParams.toArray(new NameValuePair[queryParams.size()]));

            return doRequest(requestBuilder, type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Performs POST request to specified path with empty body
     *
     * @param path request path
     * @param type return entity type
     * @param <T>  return entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T postFile(String path, TypeReference<T> type, Map<String, ?> params) {
        try {
            String uri = getApiBasePath() + path;
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            File file = (File) params.get("file");
            String mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file.getName());
            entityBuilder.addBinaryBody("file", file, ContentType.create(mimeType), file.getName());
            if (params.get("name") != null) {
                entityBuilder.addTextBody("name", (String) params.get("name"));
            }
            RequestBuilder requestBuilder = RequestBuilder.post(uri).setEntity(entityBuilder.build());
            LOGGER.debug("POST file upload request to {} with params {}", uri, params);

            return doRequest(requestBuilder, type);
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
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T post(String path, TypeReference<T> type, Object payload) {
        return post(path, type, payload, Collections.<NameValuePair>emptyList());
    }

    /**
     * Performs POST request with body to specified path
     *
     * @param path        request path
     * @param type        response entity type
     * @param payload     request payload
     * @param queryParams query parameters
     * @param <T>         response entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T post(String path, TypeReference<T> type, Object payload, List<NameValuePair> queryParams) {
        try {
            String uri = getApiBasePath() + path;
            RequestBuilder requestBuilder = RequestBuilder.post(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                .addParameters(queryParams.toArray(new NameValuePair[queryParams.size()]));
            if (payload != null) {
                validatePayload(payload);
                String stringPayload = jsonConverter.serialize(payload);
                requestBuilder.setEntity(EntityBuilder.create().setText(stringPayload).build());
                logDebugPrettyJson("POST request to {} entity \n{}", uri, payload);
            } else {
                LOGGER.debug("POST request to {}", uri);
            }

            return doRequest(requestBuilder, type);
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
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T put(String path, TypeReference<T> type, Object payload) {
        return put(path, type, payload, Collections.<NameValuePair>emptyList());
    }

    /**
     * Performs PUT request with body to specified path
     *
     * @param path        request path
     * @param type        response entity type
     * @param payload     request payload
     * @param queryParams query parameters
     * @param <T>         response entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T put(String path, TypeReference<T> type, Object payload, List<NameValuePair> queryParams) {
        try {
            String uri = getApiBasePath() + path;
            validatePayload(payload);
            HttpEntity httpEntity = EntityBuilder.create().setText(jsonConverter.serialize(payload)).build();
            RequestBuilder requestBuilder = RequestBuilder.put(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                .addParameters(queryParams.toArray(new NameValuePair[queryParams.size()]))
                .setEntity(httpEntity);
            logDebugPrettyJson("PUT request to {} entity \n{}", uri, payload);

            return doRequest(requestBuilder, type);
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Performs DELETE request to specified path
     *
     * @param path request path
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void delete(String path) {
        delete(path, Collections.<NameValuePair>emptyList());
    }

    /**
     * Performs DELETE request to specified path with query parameters
     *
     * @param path        request path
     * @param queryParams query parameters
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void delete(String path, List<NameValuePair> queryParams) {
        try {
            String uri = getApiBasePath() + path;
            LOGGER.debug("DELETE request to {} with params {}", uri, queryParams);
            RequestBuilder requestBuilder = RequestBuilder.delete(uri);
            requestBuilder.addParameters(queryParams.toArray(new NameValuePair[queryParams.size()]));
            doRequest(requestBuilder, null);
            LOGGER.debug("delete executed");
        } catch (IOException e) {
            throw new CallfireClientException(e);
        }
    }

    /**
     * Returns base URL path for all Callfire's API 2.0 endpoints
     *
     * @return string representation of base URL path
     */
    public String getApiBasePath() {
        return CallfireClient.getClientConfig().getProperty(BASE_PATH_PROPERTY);
    }

    /**
     * Returns HTTP request filters associated with API client
     *
     * @return active filters
     */
    public SortedSet<RequestFilter> getFilters() {
        return filters;
    }

    @SuppressWarnings("unchecked")
    private <T> T doRequest(RequestBuilder requestBuilder, TypeReference<T> type) throws IOException {
        for (RequestFilter filter : filters) {
            filter.filter(requestBuilder);
        }
        HttpUriRequest httpRequest = authentication.apply(requestBuilder.build());
        HttpResponse response = httpClient.execute(httpRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity == null) {
            LOGGER.debug("received http code: {} with null entity, returning null", statusCode);
            return null;
        }
        verifyResponse(statusCode, httpEntity);

        if (type == null) {
            LOGGER.debug("received response with code: {} and payload: {}, but expected type is null, returning null",
                statusCode, EntityUtils.toString(httpEntity, "UTF-8"));
            return null;
        }
        if (type.getType() == InputStream.class) {
            return (T) httpEntity.getContent();
        }

        T model = jsonConverter.deserialize(EntityUtils.toString(httpEntity, "UTF-8"), type);
        logDebugPrettyJson("received response with code: {} and entity \n{}", statusCode, model);
        return model;
    }

    private void verifyResponse(int statusCode, HttpEntity httpEntity) throws IOException {
        if (statusCode >= 400) {
            ErrorMessage message;
            String stringResponse = EntityUtils.toString(httpEntity, "UTF-8");
            try {
                message = jsonConverter.deserialize(stringResponse, of(ErrorMessage.class));
            } catch (CallfireClientException e) {
                LOGGER.warn("cannot deserialize response entity.", e);
                message = new ErrorMessage(statusCode, stringResponse, GENERIC_HELP_LINK);
            }
            switch (statusCode) {
                case 400:
                    throw new BadRequestException(message);
                case 401:
                    throw new UnauthorizedException(message);
                case 403:
                    throw new AccessForbiddenException(message);
                case 404:
                    throw new ResourceNotFoundException(message);
                case 500:
                    throw new InternalServerErrorException(message);
                default:
                    throw new CallfireApiException(message);
            }
        }
    }

    private void validatePayload(Object payload) {
        if (payload != null && payload instanceof CallfireModel) {
            ((CallfireModel) payload).validate();
        }
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

    private HttpClient buildHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setUserAgent(CallfireClient.getClientConfig().getProperty(USER_AGENT_PROPERTY));
        String proxyAddress = CallfireClient.getClientConfig().getProperty(PROXY_ADDRESS_PROPERTY);
        String proxyCredentials = CallfireClient.getClientConfig().getProperty(PROXY_CREDENTIALS_PROPERTY);

        if (isNotBlank(proxyAddress)) {
            LOGGER.debug("Configuring proxy host for client: {} auth: {}", proxyAddress, proxyCredentials);
            String[] parsedAddress = proxyAddress.split(":");
            String[] parsedCredentials = StringUtils.split(defaultString(proxyCredentials), ":");
            HttpHost proxy = new HttpHost(parsedAddress[0],
                parsedAddress.length > 1 ? toInt(parsedAddress[1], DEFAULT_PROXY_PORT) : DEFAULT_PROXY_PORT);
            if (isNotBlank(proxyCredentials)) {
                if (parsedCredentials.length > 1) {
                    CredentialsProvider provider = new BasicCredentialsProvider();
                    provider.setCredentials(
                        new AuthScope(proxy),
                        new UsernamePasswordCredentials(parsedCredentials[0], parsedCredentials[1])
                    );
                    builder.setDefaultCredentialsProvider(provider);
                } else {
                    LOGGER.warn("Proxy credentials have wrong format, must be username:password");
                }
            }

            builder.setRoutePlanner(new DefaultProxyRoutePlanner(proxy));
        }

        return builder.build();
    }
}
