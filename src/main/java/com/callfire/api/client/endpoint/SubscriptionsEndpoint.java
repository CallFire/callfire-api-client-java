package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.Subscription;
import com.callfire.api.client.model.request.FindSubscriptionRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.callfire.api.client.ApiEndpoints.SUBSCRIPTIONS_ITEM_PATH;
import static com.callfire.api.client.ApiEndpoints.SUBSCRIPTIONS_PATH;
import static com.callfire.api.client.ApiEndpoints.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ApiEndpoints.Type.STRING_TYPE;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /subscriptions
 */
public class SubscriptionsEndpoint {
    private RestApiClient client;
    private static final TypeReference<Subscription> SUBSCRIPTION_TYPE = new TypeReference<Subscription>() {
    };

    public SubscriptionsEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find subscriptions
     *
     * @param request request object with different fields for search
     * @return Page with Subscription objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Subscription> find(FindSubscriptionRequest request)
        throws CallfireApiException, CallfireClientException {
        return client.get(SUBSCRIPTIONS_PATH, new TypeReference<Page<Subscription>>() {
        }, request);
    }

    /**
     * Create subscription
     *
     * @param subscription subscription to create
     * @return ResourceId object with id of created notification
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(Subscription subscription)
        throws CallfireApiException, CallfireClientException {
        return client.post(SUBSCRIPTIONS_PATH, RESOURCE_ID_TYPE, subscription);
    }

    /**
     * Get notification by id.
     *
     * @param id Id of notification
     * @return Subscription object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Subscription get(Long id) throws CallfireApiException, CallfireClientException {
        return get(id, null);
    }

    /**
     * Get notification by id.
     *
     * @param id     Id of notification
     * @param fields Limit fields returned. Example fields=id,name
     * @return Subscription object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Subscription get(Long id, String fields) throws CallfireApiException, CallfireClientException {
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = SUBSCRIPTIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, SUBSCRIPTION_TYPE, queryParams);
    }

    /**
     * Update subscription
     *
     * @param subscription subscription to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(Subscription subscription) throws CallfireApiException, CallfireClientException {
        client.put(SUBSCRIPTIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(subscription.getId())),
            STRING_TYPE, subscription);
    }

    /**
     * Delete subscription by id
     *
     * @param id Id of subscription
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(Long id) throws CallfireApiException, CallfireClientException {
        client.delete(SUBSCRIPTIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(id)));
    }
}
