package com.callfire.api.client.integration.callstexts;

import static com.callfire.api.client.api.callstexts.model.Text.State;
import static com.callfire.api.client.api.callstexts.model.TextRecord.TextResult;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.request.FindTextsRequest;
import com.callfire.api.client.api.callstexts.model.request.SendTextsRequest;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.integration.AbstractIntegrationTest;

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
        Page<Text> page = client.textsApi().find(request);
        assertFalse(page.getItems().isEmpty());

        System.out.println(page);

        Text text = client.textsApi().get(page.getItems().get(0).getId(), "id,fromNumber,state");
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
        recipient2.setPhoneNumber(getCallerId());
        recipient2.setFromNumber(getCallerId());

        List<Text> texts = client.textsApi()
            .send(asList(recipient1, recipient2), 7415135003L, "items(id,fromNumber,state)");

        System.out.println(texts);

        assertEquals(2, texts.size());
        assertNotNull(texts.get(0).getId());
        assertNull(texts.get(0).getCampaignId());
        assertTrue(State.READY.equals(texts.get(0).getState()) || State.FINISHED.equals(texts.get(0).getState()));

        recipient1.setMessage(null);
        SendTextsRequest request = SendTextsRequest.create()
            .recipients(asList(recipient1, recipient2))
            .campaignId(7415135003L)
            .fields("items(id,fromNumber,state)")
            .defaultMessage("defaultMessage")
            .strictValidation(true)
            .build();

        texts = client.textsApi().send(request);

        Text text = client.textsApi().get(texts.get(1).getId());
        assertEquals("defaultMessage", text.getMessage());
    }
}
