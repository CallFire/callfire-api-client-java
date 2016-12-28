package com.callfire.api.client.api.contacts;

import com.callfire.api.client.*;

/**
 * Represents /contacts/dncs endpoint
 *
 * @since 1.0
 */
public class DncApi {

    // TODO vmalinovskiy: uncomment when dnc apis will be tested and available on docs site
    /*private static final String DNC_PATH = "/contacts/dncs";
    private static final String DNC_SOURCES_PATH = "/contacts/dncs/sources/{}";
    private static final String UNIVERSAL_DNC_PATH = "/contacts/dncs/universals/{}";
    private static final String DNC_NUMBER_PATH = "/contacts/dncs/{}";

    private RestApiClient client;

    public DncApi(RestApiClient client) {
        this.client = client;
    }

    *//**
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
     *//*
    public Page<DoNotContact> find(FindDncNumbersRequest request) {
        return client.get(DNC_PATH, pageOf(DoNotContact.class), request);
    }

    *//**
     * Get do not contact (dnc).
     *
     * @param number DNC number to get dnc for
     * @return DoNotContact object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     *//*
    public DoNotContact get(String number) {
        return client.get(DNC_NUMBER_PATH.replaceFirst(PLACEHOLDER, number), of(DoNotContact.class));
    }

    *//**
     * Add Do Not Contact (DNC) entries.
     *
     * @param request DNC items to create
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     *//*
    public void create(CreateDncsRequest request) {
        client.post(DNC_PATH, null, request);
    }

    *//**
     * Update a Do Not Contact (DNC) value. Can toggle whether the DNC is enabled for calls/texts.
     *
     * @param request DNC update request
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     *//*
    public void update(UpdateDncRequest request) {
        client.put(DNC_NUMBER_PATH.replaceFirst(PLACEHOLDER, request.getNumber()), null, request);
    }

    *//**
     * Delete a Do Not Contact (DNC) value.
     *
     * @param number DNC number to remove dnc for
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     *//*
    public void delete(String number) {
        Validate.notNull(number, "number cannot be null");
        client.delete(DNC_NUMBER_PATH.replaceFirst(PLACEHOLDER, number));
    }

    *//**
     * Find universal do not contacts (udnc) associated with toNumber
     *
     * @param request find request with different properties to filter
     * @return List with universal dncs
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     *//*
    public List<UniversalDnc> findUniversalDncs(FindUniversalDncsRequest request) {
        return client.get(UNIVERSAL_DNC_PATH.replaceFirst(PLACEHOLDER, request.getToNumber()), listHolderOf(UniversalDnc.class), request).getItems();
    }

    *//**
     * Delete do not contact (dnc) numbers contained in source.
     *
     * @param source Source associated with Do Not Contact (DNC) entry.
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     *//*
    public void deleteDncsFromSource(String source) {
        Validate.notNull(source, "number cannot be null");
        client.delete(DNC_SOURCES_PATH.replaceFirst(PLACEHOLDER, source));
    }*/

}
