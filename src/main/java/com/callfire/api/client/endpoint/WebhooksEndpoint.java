package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.Webhook;
import com.callfire.api.client.model.request.FindWebhooksRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /webhooks
 */
public class WebhooksEndpoint {
    private static final String WEBHOOKS_PATH = "/webhooks";
    private static final String WEBHOOKS_ITEM_PATH = "/webhooks/{}";
    private static final TypeReference<Webhook> WEBHOOK_TYPE = new TypeReference<Webhook>() {
    };
    private static final TypeReference<Page<Webhook>> PAGE_OF_WEBHOOK_TYPE = new TypeReference<Page<Webhook>>() {
    };

    private RestApiClient client;

    public WebhooksEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find webhooks by name, event, etc.
     *
     * @param request request object with different fields to filter
     * @return Page with {@link Webhook} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Webhook> findWebhooks(FindWebhooksRequest request) {
        return client.get(WEBHOOKS_PATH, PAGE_OF_WEBHOOK_TYPE, request);
    }

    /**
     * Get webhook by id.
     *
     * @param id id of webhook
     * @return {@link Webhook} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Webhook getWebhook(Long id) {
        return getWebhook(id, null);
    }

    /**
     * Get webhook by id.
     *
     * @param id     id of webhook
     * @param fields limit fields returned. Example fields=id,name
     * @return {@link Webhook} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Webhook getWebhook(Long id, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = WEBHOOKS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, WEBHOOK_TYPE, queryParams);
    }
}
