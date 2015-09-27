package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.VoiceBroadcast;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.*;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.api.callstexts.CallsApi.PAGE_OF_CALLS_TYPE;
import static com.callfire.api.client.api.campaigns.BatchesApi.PAGE_OF_BATCH_TYPE;

/**
 * Represents rest endpoint /campaigns/voice-broadcasts
 */
public class VoiceBroadcastsApi {
    private static final String VB_PATH = "/campaigns/voice-broadcasts";
    private static final String VB_ITEM_PATH = "/campaigns/voice-broadcasts/{}";
    private static final String VB_ITEM_BATCHES_PATH = "/campaigns/voice-broadcasts/{}/batches";
    private static final String VB_ITEM_CALLS_PATH = "/campaigns/voice-broadcasts/{}/calls";
    private static final String VB_ITEM_CONTROL_PATH = "/campaigns/voice-broadcasts/{}/control";
    private static final String VB_ITEM_RECIPIENTS_PATH = "/campaigns/voice-broadcasts/{}/recipients";
    private static final TypeReference<VoiceBroadcast> VB_TYPE = new TypeReference<VoiceBroadcast>() {
    };
    private static final TypeReference<Page<VoiceBroadcast>> PAGE_OF_VBS_TYPE = new TypeReference<Page<VoiceBroadcast>>() {
    };

    private RestApiClient client;

    public VoiceBroadcastsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find voice broadcasts by name, label, etc...
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link VoiceBroadcast} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<VoiceBroadcast> find(FindBroadcastsRequest request) {
        return client.get(VB_PATH, PAGE_OF_VBS_TYPE, request);
    }

    /**
     * Create voice broadcast
     *
     * @param broadcast voice broadcast to create
     * @param start     if set to true broadcast will starts immediately
     * @return {@link ResourceId} object with id of created broadcast
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(VoiceBroadcast broadcast, Boolean start) {
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("start", start, queryParams);
        return client.post(VB_PATH, RESOURCE_ID_TYPE, broadcast, queryParams);
    }

    /**
     * Get voice broadcast by id
     *
     * @param id id of broadcast
     * @return {@link VoiceBroadcast} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public VoiceBroadcast get(Long id) {
        return get(id, null);
    }

    /**
     * Get voice broadcast by id
     *
     * @param id     id of broadcast
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link VoiceBroadcast} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public VoiceBroadcast get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(VB_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), VB_TYPE, queryParams);
    }

    /**
     * Update voice broadcast
     *
     * @param broadcast broadcast to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(VoiceBroadcast broadcast) {
        Validate.notNull(broadcast.getId(), "broadcast.id cannot be null");
        client.put(VB_ITEM_PATH.replaceFirst(PLACEHOLDER, broadcast.getId().toString()), VOID_TYPE, broadcast);
    }

    /**
     * Get voice broadcast batches. Retrieve batches associated with voice campaign
     *
     * @param request get request
     * @return {@link Page} with {@link Batch} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Batch> getBatches(GetByIdRequest request) {
        String path = VB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_BATCH_TYPE, request);
    }

    /**
     * Add batch to voice broadcast
     *
     * @param request request with contacts
     * @return {@link ResourceId} with id of created {@link Batch}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId addBatch(AddBatchRequest request) {
        String path = VB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, RESOURCE_ID_TYPE, request);
    }

    /**
     * Get voice broadcast calls.
     * Get calls associated with voice broadcast ordered by date
     *
     * @param request request with properties to filter
     * @return {@link Page} with {@link Call} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Call> getCalls(GetByIdRequest request) {
        String path = VB_ITEM_CALLS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_CALLS_TYPE, request);
    }

    /**
     * Control voice broadcast (START, STOP, ARCHIVE)
     *
     * @param id      id of voice broadcast
     * @param command command for broadcast
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void control(Long id) {
        Validate.notNull(id, "id cannot be null");
        Validate.notNull(null, "command cannot be null");
        client.post(VB_ITEM_CONTROL_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Add recipients to voice broadcast
     *
     * @param id         id of voice broadcast
     * @param recipients recipients to add
     * @return list of {@link ResourceId} with recipient ids
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<ResourceId> addRecipients(Long id, List<Recipient> recipients) {
        Validate.notNull(id, "id cannot be null");
        String path = VB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, LIST_OF_RESOURCE_ID_TYPE, recipients).getItems();
    }
}
