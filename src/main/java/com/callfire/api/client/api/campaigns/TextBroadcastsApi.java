package com.callfire.api.client.api.campaigns;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.listHolderOf;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import com.callfire.api.client.AccessForbiddenException;
import com.callfire.api.client.BadRequestException;
import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.InternalServerErrorException;
import com.callfire.api.client.ResourceNotFoundException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.UnauthorizedException;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.TextBroadcast;
import com.callfire.api.client.api.campaigns.model.TextBroadcastStats;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.AddRecipientsRequest;
import com.callfire.api.client.api.campaigns.model.request.CreateBroadcastRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastTextsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindTextBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;

/**
 * Represents rest endpoint /texts/broadcasts
 *
 * @since 1.0
 */
public class TextBroadcastsApi {
    private static final String TB_PATH = "/texts/broadcasts";
    private static final String TB_ITEM_PATH = "/texts/broadcasts/{}";
    private static final String TB_ITEM_BATCHES_PATH = "/texts/broadcasts/{}/batches";
    private static final String TB_ITEM_TEXTS_PATH = "/texts/broadcasts/{}/texts";
    private static final String TB_ITEM_START_PATH = "/texts/broadcasts/{}/start";
    private static final String TB_ITEM_STOP_PATH = "/texts/broadcasts/{}/stop";
    private static final String TB_ITEM_ARCHIVE_PATH = "/texts/broadcasts/{}/archive";
    private static final String TB_ITEM_STATS_PATH = "/texts/broadcasts/{}/stats";
    private static final String TB_ITEM_RECIPIENTS_PATH = "/texts/broadcasts/{}/recipients";

    private RestApiClient client;

    public TextBroadcastsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find all text broadcasts created by the user. Can query on label, name, and the current
     * running status of the campaign.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link TextBroadcast} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<TextBroadcast> find(FindTextBroadcastsRequest request) {
        return client.get(TB_PATH, pageOf(TextBroadcast.class), request);
    }

    /**
     * Create a text broadcast campaign using the Text Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     *
     * @param broadcast text broadcast to create
     * @return {@link ResourceId} object with id of created broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(TextBroadcast broadcast) {
        return create(broadcast, null);
    }

    /**
     * Create a text broadcast campaign using the Text Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     * If start set to true campaign starts immediately
     *
     * @param broadcast text broadcast to create
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
    public ResourceId create(TextBroadcast broadcast, Boolean start) {
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("start", start, queryParams);
        return client.post(TB_PATH, of(ResourceId.class), broadcast, queryParams);
    }

    /**
     * Create a text broadcast campaign using the Text Broadcast API. A campaign can be created with
     * no contacts and bare minimum configuration, but contacts will have to be added further on to use the campaign.
     * If start set to true campaign starts immediately
     *
     * @param request request to create text broadcast
     * @return {@link ResourceId} object with id of created broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(CreateBroadcastRequest<TextBroadcast> request) {
        return client.post(TB_PATH, of(ResourceId.class), request.getBroadcast(), request);
    }

    /**
     * Get text broadcast by id
     *
     * @param id id of broadcast
     * @return {@link TextBroadcast} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public TextBroadcast get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(TB_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(TextBroadcast.class), queryParams);
    }

    /**
     * Update text broadcast
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
    public void update(TextBroadcast broadcast) {
        update(broadcast, null);
    }

    /**
     * Update text broadcast
     *
     * @param broadcast broadcast to update
     * @param strictValidation strict validation flag for broadcast
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(TextBroadcast broadcast, Boolean strictValidation) {
        Validate.notNull(broadcast.getId(), "broadcast.id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("strictValidation", strictValidation, queryParams);
        client.put(TB_ITEM_PATH.replaceFirst(PLACEHOLDER, broadcast.getId().toString()), null, broadcast, queryParams);
    }

    /**
     * Starts text campaign
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
        client.post(TB_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Stops text campaign
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
        client.post(TB_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Archives text campaign
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
        client.post(TB_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Get text broadcast batches. Retrieve batches associated with text campaign
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
        String path = TB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Batch.class), request);
    }

    /**
     * Add batch to text broadcast.
     * The add batch API allows the user to add additional batches to an already created text broadcast
     * campaign. The added batch will go through the CallFire validation process, unlike in the
     * recipients version of this API. Because of this, use the scrubDuplicates flag to remove duplicates
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
        String path = TB_ITEM_BATCHES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("strictValidation", request.getStrictValidation(), queryParams);
        return client.post(path, of(ResourceId.class), request, queryParams);
    }

    /**
     * @deprecated this method will be removed soon, please use findTexts() instead
     *
     * Get texts associated with text broadcast ordered by date
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
    @Deprecated
    public Page<Text> getTexts(GetByIdRequest request) {
        String path = TB_ITEM_TEXTS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Text.class), request);
    }

    /**
     * Get texts associated with text broadcast ordered by date
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
    public Page<Text> findTexts(FindBroadcastTextsRequest request) {
        String path = TB_ITEM_TEXTS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Text.class), request);
    }

    /**
     * Get statistics on text broadcast
     *
     * @param id text broadcast id
     * @return broadcast stats object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public TextBroadcastStats getStats(Long id) {
        return getStats(id, null, null, null);
    }

    /**
     * Get statistics on text broadcast
     *
     * @param id     text broadcast id
     * @param fields limit fields returned. Example fields=id,message
     * @param begin  begin date to filter
     * @param end    end date to filter
     * @return broadcast stats object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public TextBroadcastStats getStats(Long id, String fields, Date begin, Date end) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(3);
        addQueryParamIfSet("fields", fields, queryParams);
        addQueryParamIfSet("begin", begin, queryParams);
        addQueryParamIfSet("end", end, queryParams);
        String path = TB_ITEM_STATS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(TextBroadcastStats.class), queryParams);
    }

    /**
     * Use this API to add recipients to an already created text broadcast. Post a list of Recipient
     * objects for them to be immediately added to the text broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param id         id of text broadcast
     * @param recipients recipients to add
     * @return list of {@link ResourceId} with recipient ids
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Text> addRecipients(Long id, List<TextRecipient> recipients) {
        return addRecipients(id, recipients, null);
    }

    /**
     * Use this API to add recipients to an already created text broadcast. Post a list of Recipient
     * objects for them to be immediately added to the text broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param id         id of text broadcast
     * @param recipients recipients to add
     * @param fields     limit fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return Text objects which were sent to recipients
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Text> addRecipients(Long id, List<TextRecipient> recipients, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = TB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.post(path, listHolderOf(Text.class), recipients, queryParams).getItems();
    }

    /**
     * Use this API to add recipients to an already created text broadcast. Post a list of Recipient
     * objects for them to be immediately added to the text broadcast campaign. These contacts do not
     * go through validation process, and will be acted upon as they are added. Recipients may be added
     * as a list of contact ids, or list of numbers.
     *
     * @param request request with properties for adding recipients
     * @return Text objects which were sent to recipients
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Text> addRecipients(AddRecipientsRequest request) {
        String path = TB_ITEM_RECIPIENTS_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, listHolderOf(Text.class), request.getRecipients(), request).getItems();
    }
}
