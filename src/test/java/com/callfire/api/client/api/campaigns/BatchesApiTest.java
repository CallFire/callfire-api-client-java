package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.campaigns.model.Batch;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class BatchesApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/batchesApi";

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.batchesApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateBatch.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        Batch batch = new Batch();
        batch.setId(11L);
        batch.setEnabled(false);
        client.batchesApi().update(batch);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        Batch batch = new Batch();
        client.batchesApi().update(batch);
    }
}
