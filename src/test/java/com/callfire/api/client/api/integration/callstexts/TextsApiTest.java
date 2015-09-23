package com.callfire.api.client.api.integration.callstexts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.request.FindTextsRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Test;

import java.util.Arrays;

import static com.callfire.api.client.api.callstexts.model.Text.State;
import static com.callfire.api.client.api.callstexts.model.TextRecord.TextResult;
import static org.junit.Assert.*;

/**
 * integration tests for /texts api endpoint
 */
public class TextsApiTest extends AbstractIntegrationTest {

    @Test
    public void testGetParticularText() throws Exception {
        CallfireClient client = getCallfireClient();

        FindTextsRequest request = FindTextsRequest.create()
            .states(Arrays.asList(State.FINISHED, State.READY))
            .results(Arrays.asList(TextResult.SENT, TextResult.RECEIVED))
            .build();
        Page<Text> page = client.getTextsApi().findTexts(request);
        assertFalse(page.getItems().isEmpty());

        System.out.println(page);

        Text text = client.getTextsApi().getText(page.getItems().get(0).getId(), "id,fromNumber,state");
        assertNotNull(text.getId());
        assertNotNull(text.getFromNumber());
        assertNotNull(text.getState());
        assertNull(text.getToNumber());
    }
}
