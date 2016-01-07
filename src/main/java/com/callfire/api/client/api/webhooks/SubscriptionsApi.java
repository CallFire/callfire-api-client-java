package com.callfire.api.client.api.webhooks;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.webhooks.model.Subscription;
import com.callfire.api.client.api.webhooks.model.request.FindSubscriptionsRequest;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

/**
 * Represents rest endpoint /subscriptions
 *
 * @since 1.0
 */
public class SubscriptionsApi {
    private static final String SUBSCRIPTIONS_PATH = "/subscriptions";
    private static final String SUBSCRIPTIONS_ITEM_PATH = "/subscriptions/{}";
    private RestApiClient client;

    public SubscriptionsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find subscriptions
     *
     * @param request request object with different fields for search
     * @return {@link Page} with {@link Subscription} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Subscription> find(FindSubscriptionsRequest request) {
        return client.get(SUBSCRIPTIONS_PATH, pageOf(Subscription.class), request);
    }

    /**
     * Create subscription
     *
     * @param subscription subscription to create
     * @return {@link ResourceId} object with id of created {@link Subscription}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(Subscription subscription) {
        return client.post(SUBSCRIPTIONS_PATH, of(ResourceId.class), subscription);
    }

    /**
     * Get subscription by id.
     *
     * @param id id of subscription
     * @return {@link Subscription} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Subscription get(Long id) {
        return get(id, null);
    }

    /**
     * Get subscription by id.
     *
     * @param id     id of subscription
     * @param fields limit fields returned. Example fields=id,name
     * @return {@link Subscription} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Subscription get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = SUBSCRIPTIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(Subscription.class), queryParams);
    }

    /**
     * Update subscription
     *
     * @param subscription subscription to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(Subscription subscription) {
        Validate.notNull(subscription.getId(), "subscription.id cannot be null");
        String path = SUBSCRIPTIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(subscription.getId()));
        client.put(path, null, subscription);
    }

    /**
     * Delete subscription by id
     *
     * @param id id of subscription
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(SUBSCRIPTIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(id)));
    }
}
