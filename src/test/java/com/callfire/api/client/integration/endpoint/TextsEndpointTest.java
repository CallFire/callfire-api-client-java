package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.Text;
import com.callfire.api.client.model.request.FindTextsRequest;
import com.callfire.api.client.model.request.FindTextsRequest.FindTextsRequestBuilder;
import org.junit.Test;

import java.util.Arrays;

import static com.callfire.api.client.model.Text.State;
import static com.callfire.api.client.model.TextRecord.TextResult;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * integration tests for /texts api endpoint
 */
public class TextsEndpointTest extends AbstractIntegrationTest {

    @Test
    public void testGetParticularText() throws Exception {
        CallfireClient client = getCallfireClient();

        FindTextsRequest request = FindTextsRequestBuilder.create()
            .setStates(Arrays.asList(State.FINISHED, State.READY))
            .setResults(Arrays.asList(TextResult.SENT, TextResult.RECEIVED))
            .build();
        Page<Text> page = client.getTextsEndpoint().findTexts(request);
        assertFalse(page.getItems().isEmpty());

        System.out.println(page);

        Text text = client.getTextsEndpoint().getText(page.getItems().get(0).getId(), "id,fromNumber,state");
        assertNotNull(text.getId());
        assertNotNull(text.getFromNumber());
        assertNotNull(text.getState());
        assertNull(text.getToNumber());
    }
}
