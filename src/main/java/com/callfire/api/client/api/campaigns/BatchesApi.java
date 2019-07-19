package com.callfire.api.client.api.campaigns;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;

import java.util.ArrayList;
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
import com.callfire.api.client.api.campaigns.model.Batch;

/**
 * Represents rest endpoint /campaigns/batches
 *
 * @since 1.0
 */
public class BatchesApi {
    private static final String BATCH_PATH = "/campaigns/batches/{}";

    private RestApiClient client;

    public BatchesApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Returns a single Batch instance for a given batch id.
     * This API is useful for determining the state of a validating batch.
     *
     * @param id id of batch
     * @return requested batch
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Batch get(Long id) {
        return get(id, null);
    }

    /**
     * Returns a single Batch instance for a given batch id.
     * This API is useful for determining the state of a validating batch.
     *
     * @param id     id of batch
     * @param fields limit fields returned. Example fields=id,name
     * @return requested batch
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Batch get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(BATCH_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(Batch.class), queryParams);
    }

    /**
     * Update campaign batch
     *
     * @param batch campaign batch to update
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(Batch batch) {
        Validate.notNull(batch.getId(), "batch.id cannot be null");
        client.put(BATCH_PATH.replaceFirst(PLACEHOLDER, batch.getId().toString()), null, batch);
    }
}
