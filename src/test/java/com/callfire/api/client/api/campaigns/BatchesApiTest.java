package com.callfire.api.client.api.campaigns;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.campaigns.model.Batch;

public class BatchesApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/batchesApi";

    @Test
    public void testGetNullId() {
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
    public void testUpdateNullId() {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        Batch batch = new Batch();
        client.batchesApi().update(batch);
    }
}
