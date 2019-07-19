package com.callfire.api.client.integration.campaigns;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.BatchesApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /campaigns/batches api endpoint
 */
public class BatchesApiTest extends AbstractIntegrationTest {

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getApiUserName(), getApiUserPassword());
        BatchesApi api = callfireClient.batchesApi();

        Batch batch = api.get(1L);
        System.out.println(batch);

        Boolean enabled = batch.getEnabled();
        batch.setEnabled(!enabled);
        api.update(batch);
        Batch updatedBatch = api.get(1L);
        assertNotEquals(updatedBatch.getEnabled(), enabled);
    }
}
