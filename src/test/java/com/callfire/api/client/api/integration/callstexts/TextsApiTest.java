package com.callfire.api.client.api.integration.callstexts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.request.FindTextsRequest;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Test;

import java.util.List;

import static com.callfire.api.client.api.callstexts.model.Text.State;
import static com.callfire.api.client.api.callstexts.model.TextRecord.TextResult;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * integration tests for /texts api endpoint
 */
public class TextsApiTest extends AbstractIntegrationTest {

    @Test
    public void testFindAndGetParticularTexts() throws Exception {
        CallfireClient client = getCallfireClient();

        FindTextsRequest request = FindTextsRequest.create()
            .states(asList(State.FINISHED, State.READY))
            .results(asList(TextResult.SENT, TextResult.RECEIVED))
            .limit(2L)
            .build();
        Page<Text> page = client.getTextsApi().find(request);
        assertFalse(page.getItems().isEmpty());

        System.out.println(page);

        Text text = client.getTextsApi().get(page.getItems().get(0).getId(), "id,fromNumber,state");
        assertNotNull(text.getId());
        assertNotNull(text.getFromNumber());
        assertNotNull(text.getState());
        assertNull(text.getToNumber());
    }

    @Test
    public void testSendText() throws Exception {
        CallfireClient client = getCallfireClient();

        TextRecipient recipient1 = new TextRecipient();
        recipient1.setMessage("msg");
        recipient1.setPhoneNumber(getCallerId());
        TextRecipient recipient2 = new TextRecipient();
        recipient2.setMessage("msg");
        recipient2.setPhoneNumber(getCallerId());
        List<Text> texts = client.getTextsApi()
            .send(asList(recipient1, recipient2), null, "items(id,fromNumber,state)");

        System.out.println(texts);

        assertEquals(2, texts.size());
        assertNotNull(texts.get(0).getId());
        assertNull(texts.get(0).getCampaignId());
        assertEquals(State.READY, texts.get(0).getState());
    }
}
