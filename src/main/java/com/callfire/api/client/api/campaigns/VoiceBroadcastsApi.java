package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.VoiceBroadcast;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindVoiceBroadcastsRequest;
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
import static com.callfire.api.client.api.callstexts.CallsApi.LIST_OF_CALLS_TYPE;
import static com.callfire.api.client.api.callstexts.CallsApi.PAGE_OF_CALLS_TYPE;
import static com.callfire.api.client.api.campaigns.BatchesApi.PAGE_OF_BATCH_TYPE;

/**
 * Represents rest endpoint /campaigns/voice-broadcasts
 *
 * @since 1.0
 */
public class VoiceBroadcastsApi {
    private static final String VB_PATH = "/campaigns/voice-broadcasts";
    private static final String VB_ITEM_PATH = "/campaigns/voice-broadcasts/{}";
    private static final String VB_ITEM_BATCHES_PATH = "/campaigns/voice-broadcasts/{}/batches";
    private static final String VB_ITEM_CALLS_PATH = "/campaigns/voice-broadcasts/{}/calls";
    private static final String VB_ITEM_START_PATH = "/campaigns/voice-broadcasts/{}/start";
    private static final String VB_ITEM_STOP_PATH = "/campaigns/voice-broadcasts/{}/stop";
    private static final String VB_ITEM_ARCHIVE_PATH = "/campaigns/voice-broadcasts/{}/archive";
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
     * Find all voice broadcasts created by the user. Can query on label, name, and the current
     * running status of the campaign.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link VoiceBroadcast} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<VoiceBroadcast> find(FindVoiceBroadcastsRequest request) {
        return client.get(VB_PATH, PAGE_OF_VBS_TYPE, request);
    }

    /**
     * Create a voice broadcast campaign using the Voice Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     *
     * @param broadcast voice broadcast to create
     * @return {@link ResourceId} object with id of created broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(VoiceBroadcast broadcast) {
        return create(broadcast, null);
    }

    /**
     * Create a voice broadcast campaign using the Voice Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     * If start argument true campaign starts immediately
     *
     * @param broadcast voice broadcast to create
     * @param start     if set to true broadcast will starts immediately
     * @return {@link ResourceId} object with id of created broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<Batch> getBatches(GetByIdRequest request) {
        String path = VB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_BATCH_TYPE, request);
    }

    /**
     * Add batch to voice broadcast.
     * The add batch API allows the user to add additional batches to an already created voice broadcast
     * campaign. The added batch will go through the CallFire validation process, unlike in the recipients
     * version of this API. Because of this, use the scrubDuplicates flag to remove duplicates
     * from your batch. Batches may be added as a contact list id, a list of contact ids, or a list of numbers.
     *
     * @param request request with contacts
     * @return {@link ResourceId} with id of created {@link Batch}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<Call> getCalls(GetByIdRequest request) {
        String path = VB_ITEM_CALLS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_CALLS_TYPE, request);
    }

    /**
     * Starts IVR campaign
     *
     * @param id id of campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void start(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(VB_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Stops IVR campaign
     *
     * @param id id of campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void stop(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(VB_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Archives IVR campaign
     *
     * @param id id of campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void archive(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(VB_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Use this API to add recipients to an already created voice broadcast. Post a list of Recipient
     * objects for them to be immediately added to the voice broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param id         id of voice broadcast
     * @param recipients recipients to add
     * @return list of {@link Call} to recipients
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> addRecipients(Long id, List<Recipient> recipients) {
        return addRecipients(id, recipients, null);
    }

    /**
     * Use this API to add recipients to an already created voice broadcast. Post a list of Recipient
     * objects for them to be immediately added to the voice broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param id         id of voice broadcast
     * @param recipients recipients to add
     * @param fields     limit fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return list of {@link Call} to recipients
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> addRecipients(Long id, List<Recipient> recipients, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = VB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, LIST_OF_CALLS_TYPE, recipients, queryParams).getItems();
    }
}
