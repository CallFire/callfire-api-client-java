package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.BatchesApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * integration tests for /campaigns/batches api endpoint
 */
public class BatchesApiTest extends AbstractIntegrationTest {

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
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
