package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.BatchesApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * integration tests for /campaigns/batches api endpoint
 */
public class BatchesApiTest extends AbstractIntegrationTest {

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        BatchesApi api = callfireClient.getCampaignsApi().getBatchesApi();

        Batch batch = api.getCampaignBatch(1L);
        System.out.println(batch);
        assertTrue(batch.getEnabled());

        batch.setEnabled(false);
        api.updateCampaignBatch(batch);
        batch = api.getCampaignBatch(1L);
        assertFalse(batch.getEnabled());
    }
}
