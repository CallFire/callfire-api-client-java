package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.TextBroadcast;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.*;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.api.callstexts.TextsApi.LIST_OF_TEXTS_TYPE;
import static com.callfire.api.client.api.callstexts.TextsApi.PAGE_OF_TEXTS_TYPE;
import static com.callfire.api.client.api.campaigns.BatchesApi.BATCH_TYPE;
import static com.callfire.api.client.api.campaigns.BatchesApi.PAGE_OF_BATCH_TYPE;

/**
 * Represents rest endpoint /campaigns/text-broadcasts
 *
 * @since 1.0
 */
public class TextBroadcastsApi {
    private static final String TB_PATH = "/campaigns/text-broadcasts";
    private static final String TB_BATCHES_ITEM_PATH = "/campaigns/text-broadcasts/batches/{}";
    private static final String TB_ITEM_PATH = "/campaigns/text-broadcasts/{}";
    private static final String TB_ITEM_BATCHES_PATH = "/campaigns/text-broadcasts/{}/batches";
    private static final String TB_ITEM_TEXTS_PATH = "/campaigns/text-broadcasts/{}/texts";
    private static final String TB_ITEM_START_PATH = "/campaigns/text-broadcasts/{}/start";
    private static final String TB_ITEM_STOP_PATH = "/campaigns/text-broadcasts/{}/stop";
    private static final String TB_ITEM_ARCHIVE_PATH = "/campaigns/text-broadcasts/{}/archive";
    private static final String TB_ITEM_RECIPIENTS_PATH = "/campaigns/text-broadcasts/{}/recipients";
    private static final String TB_ITEM_RECIPIENTS_FILE_PATH = "/campaigns/text-broadcasts/{}/recipients-file";
    private static final TypeReference<TextBroadcast> TB_TYPE = new TypeReference<TextBroadcast>() {
    };
    private static final TypeReference<Page<TextBroadcast>> PAGE_OF_TBS_TYPE = new TypeReference<Page<TextBroadcast>>() {
    };

    private RestApiClient client;

    public TextBroadcastsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find text broadcasts by name, label, etc...
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link TextBroadcast} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<TextBroadcast> find(FindBroadcastsRequest request) {
        return client.get(TB_PATH, PAGE_OF_TBS_TYPE, request);
    }

    /**
     * Create text broadcast
     *
     * @param broadcast text broadcast to create
     * @return {@link ResourceId} object with id of created broadcast
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(TextBroadcast broadcast) {
        return create(broadcast, null);
    }

    /**
     * Create text broadcast. If start set to true campaign starts immediately
     *
     * @param broadcast text broadcast to create
     * @param start     if set to true broadcast will starts immediately
     * @return {@link ResourceId} object with id of created broadcast
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(TextBroadcast broadcast, Boolean start) {
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("start", start, queryParams);
        return client.post(TB_PATH, RESOURCE_ID_TYPE, broadcast, queryParams);
    }

    /**
     * Get text broadcast by id
     *
     * @param id id of broadcast
     * @return {@link TextBroadcast} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public TextBroadcast get(Long id) {
        return get(id, null);
    }

    /**
     * Get text broadcast by id
     *
     * @param id     id of broadcast
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link TextBroadcast} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public TextBroadcast get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(TB_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), TB_TYPE, queryParams);
    }

    /**
     * Update text broadcast
     *
     * @param broadcast broadcast to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(TextBroadcast broadcast) {
        Validate.notNull(broadcast.getId(), "broadcast.id cannot be null");
        client.put(TB_ITEM_PATH.replaceFirst(PLACEHOLDER, broadcast.getId().toString()), VOID_TYPE, broadcast);
    }

    /**
     * Starts text campaign
     *
     * @param id id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void start(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(TB_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Stops text campaign
     *
     * @param id id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void stop(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(TB_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Archives text campaign
     *
     * @param id id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void archive(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(TB_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Get text broadcast batch. Retrieve batch associated with text campaign
     *
     * @param request get request
     * @return {@link Page} with {@link Batch} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Batch getBatch(GetByIdRequest request) {
        String path = TB_BATCHES_ITEM_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, BATCH_TYPE, request);
    }

    /**
     * Update text broadcast batch
     *
     * @param batch batch to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void updateBatch(Batch batch) {
        Validate.notNull(batch.getId(), "batch.id cannot be null");
        client.put(TB_BATCHES_ITEM_PATH.replaceFirst(PLACEHOLDER, batch.getId().toString()), VOID_TYPE, batch);
    }

    /**
     * Get text broadcast batches. Retrieve batches associated with text campaign
     *
     * @param request get request
     * @return {@link Page} with {@link Batch} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Batch> getBatches(GetByIdRequest request) {
        String path = TB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_BATCH_TYPE, request);
    }

    /**
     * Add batch to text broadcast
     *
     * @param request request with contacts
     * @return {@link ResourceId} with id of created {@link Batch}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId addBatch(AddBatchRequest request) {
        String path = TB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, RESOURCE_ID_TYPE, request);
    }

    /**
     * Get text broadcast texts.
     * Get texts associated with text broadcast ordered by date
     *
     * @param request request with properties to filter
     * @return {@link Page} with {@link Call} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Text> getTexts(GetByIdRequest request) {
        String path = TB_ITEM_TEXTS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_TEXTS_TYPE, request);
    }

    /**
     * Add recipients to text broadcast
     *
     * @param id         id of text broadcast
     * @param recipients recipients to add
     * @return list of {@link ResourceId} with recipient ids
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Text> addRecipients(Long id, List<TextRecipient> recipients) {
        return addRecipients(id, recipients, null);
    }

    /**
     * Add recipients to text broadcast
     *
     * @param id         id of text broadcast
     * @param recipients recipients to add
     * @param fields     limit fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return list of {@link ResourceId} with recipient ids
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Text> addRecipients(Long id, List<TextRecipient> recipients, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = TB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, LIST_OF_TEXTS_TYPE, recipients, queryParams).getItems();
    }

    /**
     * Add recipients to text broadcast from file
     *
     * @param id   id of text broadcast
     * @param file csv file with recipients
     * @return list of {@link ResourceId} with recipient ids
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    // TODO vmikhailov backend isn't ready yet
    private List<ResourceId> addRecipients(Long id, File file) {
        Validate.notNull(id, "id cannot be null");
        Validate.notNull(file, "file cannot be null");
        Map<String, Object> params = new HashMap<>(1);
        params.put("file", file);
        String path = TB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.postFile(path, LIST_OF_RESOURCE_ID_TYPE, params).getItems();
    }
}
