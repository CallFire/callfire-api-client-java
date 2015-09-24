package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /campaigns/batches
 */
public class BatchesApi {
    private static final String BATCH_PATH = "/campaigns/batches/{}";
    private static final TypeReference<Batch> BATCH_TYPE = new TypeReference<Batch>() {
    };

    private RestApiClient client;

    public BatchesApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Get campaign batch by id
     *
     * @param id id of batch
     * @return requested batch
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Batch getCampaignBatch(Long id) {
        return getCampaignBatch(id, null);
    }

    /**
     * Get campaign batch by id
     *
     * @param id     id of batch
     * @param fields limit fields returned. Example fields=id,name
     * @return requested batch
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Batch getCampaignBatch(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(BATCH_PATH.replaceFirst(PLACEHOLDER, id.toString()), BATCH_TYPE, queryParams);
    }

    /**
     * Update campaign batch
     *
     * @param batch campaign batch to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void updateCampaignBatch(Batch batch) {
        Validate.notNull(batch.getId(), "batch.id cannot be null");
        client.put(BATCH_PATH.replaceFirst(PLACEHOLDER, batch.getId().toString()), BATCH_TYPE, batch);
    }
}
