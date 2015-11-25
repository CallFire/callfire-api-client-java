package com.callfire.api.client.api.contacts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.request.FindDncContactsRequest;

import static com.callfire.api.client.ModelType.pageOf;

/**
 * Represents rest endpoint /contacts/do-not-calls
 *
 * @since 1.0
 */
public class DncApi {
    private static final String DNC_PATH = "/contacts/dncs";

    private RestApiClient client;

    public DncApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find all Do Not Contact (DNC) objects created by the user.
     * These DoNotContact entries only affect calls/texts/campaigns on this account.
     *
     * @param request find request with different properties to filter
     * @return Page with numbers which must not be contacted
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<DoNotContact> find(FindDncContactsRequest request) {
        return client.get(DNC_PATH, pageOf(DoNotContact.class), request);
    }

    /**
     * Update a Do Not Contact (DNC) contact value. Can toggle whether the DNC is enabled for calls/texts.
     *
     * @param dnc DNC item to update
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(DoNotContact dnc) {
        client.put(DNC_PATH, null, dnc);
    }
}
