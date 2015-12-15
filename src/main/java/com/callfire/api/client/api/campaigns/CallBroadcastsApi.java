package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.CallBroadcast;
import com.callfire.api.client.api.campaigns.model.CallBroadcastStats;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindCallBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /calls/broadcasts
 *
 * @since 1.8
 */
public class CallBroadcastsApi {
    private static final String CB_PATH = "/calls/broadcasts";
    private static final String CB_ITEM_PATH = "/calls/broadcasts/{}";
    private static final String CB_ITEM_STATS_PATH = "/calls/broadcasts/{}/stats";
    private static final String CB_ITEM_BATCHES_PATH = "/calls/broadcasts/{}/batches";
    private static final String CB_ITEM_CALLS_PATH = "/calls/broadcasts/{}/calls";
    private static final String CB_ITEM_START_PATH = "/calls/broadcasts/{}/start";
    private static final String CB_ITEM_STOP_PATH = "/calls/broadcasts/{}/stop";
    private static final String CB_ITEM_ARCHIVE_PATH = "/calls/broadcasts/{}/archive";
    private static final String CB_ITEM_RECIPIENTS_PATH = "/calls/broadcasts/{}/recipients";

    private RestApiClient client;

    public CallBroadcastsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find all call broadcasts created by the user. Can query on label, name, and the current
     * running status of the campaign.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link CallBroadcast} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<CallBroadcast> find(FindCallBroadcastsRequest request) {
        return client.get(CB_PATH, pageOf(CallBroadcast.class), request);
    }

    /**
     * Create a call broadcast campaign using the Call Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     *
     * @param broadcast call broadcast to create
     * @return {@link ResourceId} object with id of created broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(CallBroadcast broadcast) {
        return create(broadcast, null);
    }

    /**
     * Create a call broadcast campaign using the Call Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     * If start argument true campaign starts immediately
     *
     * @param broadcast call broadcast to create
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
    public ResourceId create(CallBroadcast broadcast, Boolean start) {
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("start", start, queryParams);
        return client.post(CB_PATH, of(ResourceId.class), broadcast, queryParams);
    }

    /**
     * Get call broadcast by id
     *
     * @param id id of broadcast
     * @return {@link CallBroadcast} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallBroadcast get(Long id) {
        return get(id, null);
    }

    /**
     * Get call broadcast by id
     *
     * @param id     id of broadcast
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link CallBroadcast} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallBroadcast get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(CB_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(CallBroadcast.class), queryParams);
    }

    /**
     * Update call broadcast
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
    public void update(CallBroadcast broadcast) {
        Validate.notNull(broadcast.getId(), "broadcast.id cannot be null");
        client.put(CB_ITEM_PATH.replaceFirst(PLACEHOLDER, broadcast.getId().toString()), null, broadcast);
    }

    /**
     * Get call broadcast batches. Retrieve batches associated with call campaign
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
        String path = CB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Batch.class), request);
    }

    /**
     * Add batch to call broadcast.
     * The add batch API allows the user to add additional batches to an already created call broadcast
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
        String path = CB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, of(ResourceId.class), request);
    }

    /**
     * Get call broadcast calls.
     * Get calls associated with call broadcast ordered by date
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
        String path = CB_ITEM_CALLS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Call.class), request);
    }

    /**
     * Get statistics on call broadcast
     *
     * @param id call broadcast id
     * @return {@link Page} with {@link Call} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallBroadcastStats getStats(Long id) {
        return getStats(id, null, null, null);
    }

    /**
     * Get statistics on call broadcast
     *
     * @param id     call broadcast id
     * @param fields limit fields returned. Example fields=id,message
     * @param begin  begin date to filter
     * @param end    end date to filter
     * @return {@link Page} with {@link Call} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallBroadcastStats getStats(Long id, String fields, Date begin, Date end) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(3);
        addQueryParamIfSet("fields", fields, queryParams);
        addQueryParamIfSet("begin", begin, queryParams);
        addQueryParamIfSet("end", end, queryParams);
        String path = CB_ITEM_STATS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(CallBroadcastStats.class), queryParams);
    }

    /**
     * Starts call campaign
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
        client.post(CB_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Stops call campaign
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
        client.post(CB_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Archives call campaign
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
        client.post(CB_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Use this API to add recipients to an already created call broadcast. Post a list of Recipient
     * objects for them to be immediately added to the call broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param id         id of call broadcast
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
     * Use this API to add recipients to an already created call broadcast. Post a list of Recipient
     * objects for them to be immediately added to the call broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param id         id of call broadcast
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
        String path = CB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, listHolderOf(Call.class), recipients, queryParams).getItems();
    }
}
