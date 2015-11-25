package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.IvrBroadcast;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindIvrBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /campaigns/ivrs
 *
 * @since 1.0
 */
public class IvrBroadcastsApi {
    private static final String IVR_PATH = "/campaigns/ivrs";
    private static final String IVR_ITEM_PATH = "/campaigns/ivrs/{}";
    private static final String IVR_ITEM_BATCHES_PATH = "/campaigns/ivrs/{}/batches";
    private static final String IVR_ITEM_CALLS_PATH = "/campaigns/ivrs/{}/calls";
    private static final String IVR_ITEM_START_PATH = "/campaigns/ivrs/{}/start";
    private static final String IVR_ITEM_STOP_PATH = "/campaigns/ivrs/{}/stop";
    private static final String IVR_ITEM_ARCHIVE_PATH = "/campaigns/ivrs/{}/archive";
    private static final String IVR_ITEM_RECIPIENTS_PATH = "/campaigns/ivrs/{}/recipients";

    private RestApiClient client;

    public IvrBroadcastsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find all IVR campaigns created by the user. Can query on label, name,
     * and the current running status of the campaign.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link IvrBroadcast} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<IvrBroadcast> find(FindIvrBroadcastsRequest request) {
        return client.get(IVR_PATH, pageOf(IvrBroadcast.class), request);
    }

    /**
     * Create an IVR (Interactive Voice Response) campaign using the this API. By default, Campaigns are STOPPED.
     * To start your campaign on creation, set start=true along with the standard request, or by using the
     * {@link IvrBroadcastsApi#start(Long)} API for the campaign. A campaign can be created with no contacts
     * and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     *
     * @param broadcast ivr broadcast to create
     * @return {@link ResourceId} object with id of created broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(IvrBroadcast broadcast) {
        return create(broadcast, null);
    }

    /**
     * Create an IVR (Interactive Voice Response) campaign using the this API. By default, Campaigns are STOPPED.
     * To start your campaign on creation, set start=true along with the standard request, or by using the
     * {@link IvrBroadcastsApi#start(Long)} API for the campaign. A campaign can be created with no contacts
     * and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     *
     * @param broadcast ivr broadcast to create
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
    public ResourceId create(IvrBroadcast broadcast, Boolean start) {
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("start", start, queryParams);
        return client.post(IVR_PATH, of(ResourceId.class), broadcast, queryParams);
    }

    /**
     * Get ivr broadcast by id
     *
     * @param id id of broadcast
     * @return {@link IvrBroadcast} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public IvrBroadcast get(Long id) {
        return get(id, null);
    }

    /**
     * Get ivr broadcast by id
     *
     * @param id     id of broadcast
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link IvrBroadcast} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public IvrBroadcast get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(IVR_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(IvrBroadcast.class), queryParams);
    }

    /**
     * Update ivr broadcast
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
    public void update(IvrBroadcast broadcast) {
        Validate.notNull(broadcast.getId(), "broadcast.id cannot be null");
        client.put(IVR_ITEM_PATH.replaceFirst(PLACEHOLDER, broadcast.getId().toString()), null, broadcast);
    }

    /**
     * Get ivr broadcast batches. Retrieve batches associated with ivr campaign
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
        String path = IVR_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Batch.class), request);
    }

    /**
     * The add batch API allows the user to add additional batches to an already created IVR campaign.
     * The added batch will go through the CallFire validation process, unlike in the recipients
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
        String path = IVR_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, of(ResourceId.class), request);
    }

    /**
     * Get calls associated with ivr broadcast ordered by date
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
        String path = IVR_ITEM_CALLS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Call.class), request);
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
        client.post(IVR_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
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
        client.post(IVR_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
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
        client.post(IVR_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Add recipients to ivr broadcast
     *
     * @param id         id of ivr broadcast
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
     * Add recipients to ivr broadcast
     *
     * @param id         id of ivr broadcast
     * @param recipients recipients to add
     * @param fields     limit fields returned. Example fields=id,message
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
        String path = IVR_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, listHolderOf(Call.class), recipients, queryParams).getItems();
    }
}
