package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.common.model.Page;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /calls
 * Use the /calls API to quickly send individual calls, get results.
 * A verified Caller ID and sufficient credits are required to make a call.
 * <br>
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
 * @since 1.0
 */
public class CallsApi {
    private static final String CALLS_PATH = "/calls";
    private static final String CALLS_ITEM_PATH = "/calls/{}";

    private RestApiClient client;

    public CallsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Finds all calls sent or received by the user, filtered by different properties, broadcast id,
     * toNumber, fromNumber, label, state, etc. Use "campaignId=0" parameter to query
     * for all calls sent through the POST /calls API {@link CallsApi#send(List)}.
     *
     * @param request request object with different fields to filter
     * @return Page with @{Call} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    public Page<Call> find(FindCallsRequest request) {
        return client.get(CALLS_PATH, pageOf(Call.class), request);
    }

    /**
     * Get call by id
     *
     * @param id id of call
     * @return Call object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Call get(Long id) {
        return get(id, null);
    }

    /**
     * Get call by id
     *
     * @param id     id of call
     * @param fields limit fields returned. Example fields=id,name
     * @return Call object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Call get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(Call.class), queryParams);
    }

    /**
     * Send calls to recipients through default campaign.
     * Use the API to quickly send individual calls.
     * A verified Caller ID and sufficient credits are required to make a call.
     *
     * @param recipients call recipients
     * @return list of {@link Call}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> send(List<CallRecipient> recipients) {
        return send(recipients, null, null);
    }

    /**
     * Send call to recipients through existing campaign, if null default campaign will be used
     * Use the API to quickly send individual calls.
     * A verified Caller ID and sufficient credits are required to make a call.
     *
     * @param recipients call recipients
     * @param campaignId specify a campaignId to send calls quickly on a previously created campaign
     * @param fields     fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return list of {@link Call}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> send(List<CallRecipient> recipients, Long campaignId, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>(2);
        addQueryParamIfSet("campaignId", campaignId, queryParams);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.post(CALLS_PATH, listHolderOf(Call.class), recipients, queryParams).getItems();
    }
}
