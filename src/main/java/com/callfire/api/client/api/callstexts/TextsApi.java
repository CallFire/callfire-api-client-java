package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.request.FindTextsRequest;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.Page;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /texts
 * Use the /texts API to quickly send individual texts. A verified Caller ID and sufficient
 * credits are required to make a call.
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">text states and results</a>
 * @since 1.0
 */
public class TextsApi {
    private static final String TEXTS_PATH = "/texts";
    private static final String TEXTS_ITEM_PATH = "/texts/{}";

    private RestApiClient client;

    public TextsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Finds all texts sent or received by the user. Use "campaignId=0"  parameter to query for all
     * texts sent through the POST /texts API.
     * If no limit is given then the last 100 texts will be returned.
     *
     * @param request request object with different fields to filter
     * @return Page with @{Text} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">text states and results</a>
     */
    public Page<Text> find(FindTextsRequest request) {
        return client.get(TEXTS_PATH, pageOf(Text.class), request);
    }

    /**
     * Get text by id.
     *
     * @param id id of text
     * @return text object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Text get(Long id) {
        return get(id, null);
    }

    /**
     * Get text by id.
     *
     * @param id     id of text
     * @param fields limit fields returned. Example fields=id,name
     * @return text object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Text get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = TEXTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(Text.class), queryParams);
    }

    /**
     * Send texts to recipients through default campaign
     * Use the /texts API to quickly send individual texts. A verified Caller ID and sufficient
     * credits are required to make a call.
     *
     * @param recipients text recipients
     * @return list of {@link Text}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Text> send(List<TextRecipient> recipients) {
        return send(recipients, null, null);
    }

    /**
     * Send texts to recipients through existing campaign, if null default campaign will be used
     * Use the /texts API to quickly send individual texts. A verified Caller ID and sufficient
     * credits are required to make a call.
     *
     * @param recipients text recipients
     * @param campaignId id of outbound campaign
     * @param fields     limit fields returned. Example items(id,name,fromNumber)
     * @return list of {@link Text}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Text> send(List<TextRecipient> recipients, Long campaignId, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>(2);
        addQueryParamIfSet("campaignId", campaignId, queryParams);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.post(TEXTS_PATH, listHolderOf(Text.class), recipients, queryParams).getItems();
    }
}
