package com.callfire.api.client;

import static com.callfire.api.client.ClientConstants.BASE_PATH_PROPERTY;
import static com.callfire.api.client.ClientConstants.DEFAULT_PROXY_PORT;
import static com.callfire.api.client.ClientConstants.GENERIC_HELP_LINK;
import static com.callfire.api.client.ClientConstants.PROXY_ADDRESS_PROPERTY;
import static com.callfire.api.client.ClientConstants.PROXY_CREDENTIALS_PROPERTY;
import static com.callfire.api.client.ClientConstants.USER_AGENT_PROPERTY;
import static com.callfire.api.client.ClientUtils.buildQueryParams;
import static com.callfire.api.client.ModelType.of;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.ErrorMessage;
import com.callfire.api.client.auth.Authentication;
import com.callfire.api.client.auth.BasicAuth;

/**
 * REST client which makes HTTP calls to Callfire service
 *
 * @since 1.0
 */
public class RestApiClient {
    private static final Logger log = new Logger(RestApiClient.class);
    private static final Map<String, String> MIME_TYPES = initMimeTypes();

    private HttpClient httpClient;
    private JsonConverter jsonConverter;
    private Authentication authentication;
    private SortedSet<RequestFilter> filters = new TreeSet<>();

    static Map<String, String> initMimeTypes() {
        Map<String, String> types = new HashMap<>();
        types.put("jpeg", "image/jpeg");
        types.put("jpg", "image/jpeg");
        types.put("jpe", "image/jpeg");
        types.put("jfif", "image/jpeg");
        types.put("pjpeg", "image/jpeg");
        types.put("pjp", "image/jpeg");
        types.put("png", "image/png");
        types.put("bmp", "image/bmp");
        types.put("gif", "image/gif");

        types.put("wav", "audio/x-wav");
        types.put("mp3", "audio/mp3");
        types.put("m4a", "audio/m4a");
        types.put("mp4a", "audio/m4a");

        types.put("mp4", "video/mp4");
        return types;
    }

    static ContentType getContentType(String fileName) {
        return ContentType.create(defaultIfBlank(MIME_TYPES.get(substringAfterLast(fileName, ".")), "application/octet-stream"));
    }

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
        return get(path, type, emptyList());
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
        return get(path, type, buildQueryParams(request));
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
            log.debug("GET request to {} with params: {}", uri, queryParams);
            RequestBuilder requestBuilder = RequestBuilder.get(uri).addParameters(queryParams.toArray(new NameValuePair[0]));

            return doRequest(requestBuilder, type);
        }
        catch (IOException e) {
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
     * @param path           request path
     * @param type           response entity type
     * @param fileDataParams request parameters
     * @param <T>            response entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T postFile(String path, TypeReference<T> type, Map<String, ?> fileDataParams) {
        return postFile(path, type, fileDataParams, null);
    }

    /**
     * Performs POST request with binary body to specified path
     *
     * @param path           request path
     * @param type           response entity type
     * @param fileDataParams request parameters
     * @param queryParams    query parameters
     * @param <T>            response entity type
     * @return pojo mapped from json
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public <T> T postFile(String path, TypeReference<T> type, Map<String, ?> fileDataParams, List<NameValuePair> queryParams) {
        try {
            String uri = getApiBasePath() + path;
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            File file = (File) fileDataParams.get("file");
            entityBuilder.addBinaryBody("file", file, getContentType(file.getName()), file.getName());
            fileDataParams.remove("file");

            for (Map.Entry<String, ?> entry : fileDataParams.entrySet()) {
                if ((entry.getValue() != null && entry.getKey() != null)) {
                    entityBuilder.addTextBody(entry.getKey(), entry.getValue().toString());
                }
            }

            RequestBuilder requestBuilder = RequestBuilder.post(uri)
                .setEntity(entityBuilder.build())
                .addParameters(queryParams != null ? queryParams.toArray(new NameValuePair[0]) : new NameValuePair[]{});
            log.debug("POST file upload request to {} with params {}", uri, fileDataParams);

            return doRequest(requestBuilder, type);
        }
        catch (IOException e) {
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
        return post(path, type, payload, emptyList());
    }

    /**
     * Performs POST request with body to specified path
     *
     * @param path    request path
     * @param type    response entity type
     * @param payload request payload
     * @param request finder request with query parameters
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
    public <T> T post(String path, TypeReference<T> type, Object payload, CallfireModel request) {
        List<NameValuePair> queryParams = buildQueryParams(request);
        return post(path, type, payload, queryParams);
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
                .addParameters(queryParams.toArray(new NameValuePair[0]));
            if (payload != null) {
                validatePayload(payload);
                requestBuilder.setEntity(new StringEntity(jsonConverter.serialize(payload), Consts.UTF_8));
                logDebugPrettyJson("POST request to {} entity \n{}", uri, payload);
            }
            else {
                log.debug("POST request to {}", uri);
            }

            return doRequest(requestBuilder, type);
        }
        catch (IOException e) {
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
        return put(path, type, payload, emptyList());
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
            RequestBuilder requestBuilder = RequestBuilder.put(uri)
                .setHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                .addParameters(queryParams.toArray(new NameValuePair[0]))
                .setEntity(new StringEntity(jsonConverter.serialize(payload), Consts.UTF_8));
            logDebugPrettyJson("PUT request to {} entity \n{}", uri, payload);

            return doRequest(requestBuilder, type);
        }
        catch (IOException e) {
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
        delete(path, emptyList());
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
            log.debug("DELETE request to {} with params {}", uri, queryParams);
            RequestBuilder requestBuilder = RequestBuilder.delete(uri);
            requestBuilder.addParameters(queryParams.toArray(new NameValuePair[0]));
            doRequest(requestBuilder, null);
            log.debug("delete executed");
        }
        catch (IOException e) {
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
            log.debug("received http code: {} with null entity, returning null", statusCode);
            return null;
        }
        verifyResponse(statusCode, httpEntity);

        if (type == null) {
            log.debug("received response with code: {} and payload: {}, but expected type is null, returning null",
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
            String stringResponse = EntityUtils.toString(httpEntity, Consts.UTF_8);
            try {
                message = jsonConverter.deserialize(stringResponse, of(ErrorMessage.class));
            }
            catch (CallfireClientException e) {
                log.warn("cannot deserialize response entity.", e);
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
        if (payload instanceof CallfireModel) {
            ((CallfireModel) payload).validate();
        }
    }

    // makes extra deserialization to get pretty json string, enable only in case of debugging
    private void logDebugPrettyJson(String message, Object... params) throws JsonProcessingException {
        if (log.isDebugEnabled()) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof CallfireModel) {
                    String prettyJson = jsonConverter.getMapper().writerWithDefaultPrettyPrinter()
                        .writeValueAsString(params[i]);
                    params[i] = prettyJson;
                }
            }
            log.debug(message, params);
        }
    }

    private HttpClient buildHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setUserAgent(CallfireClient.getClientConfig().getProperty(USER_AGENT_PROPERTY));
        String proxyAddress = CallfireClient.getClientConfig().getProperty(PROXY_ADDRESS_PROPERTY);
        String proxyCredentials = CallfireClient.getClientConfig().getProperty(PROXY_CREDENTIALS_PROPERTY);

        if (isNotBlank(proxyAddress)) {
            log.debug("Configuring proxy host for client: {} auth: {}", proxyAddress, proxyCredentials);
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
                }
                else {
                    log.warn("Proxy credentials have wrong format, must be username:password");
                }
            }

            builder.setRoutePlanner(new DefaultProxyRoutePlanner(proxy));
        }

        return builder.build();
    }
}
