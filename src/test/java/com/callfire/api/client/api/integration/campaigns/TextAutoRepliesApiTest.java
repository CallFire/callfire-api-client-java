package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.TextAutoRepliesApi;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.campaigns.model.TextAutoReply;
import com.callfire.api.client.api.campaigns.model.request.FindTextAutoRepliesRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * integration tests for /campaings/text-auto-replys api endpoint
 */
public class TextAutoRepliesApiTest extends AbstractIntegrationTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        TextAutoRepliesApi api = callfireClient.textAutoRepliesApi();

        TextAutoReply textAutoReply = new TextAutoReply();
        textAutoReply.setNumber("19206596476");
        textAutoReply.setMessage("test message");
        textAutoReply.setMatch("test match");
        ResourceId resourceId = api.create(textAutoReply);
        assertNotNull(resourceId.getId());

        FindTextAutoRepliesRequest request = FindTextAutoRepliesRequest.create()
            .number("19206596476")
            .build();
        Page<TextAutoReply> textAutoReplies = api.find(request);
        System.out.println(textAutoReplies);

        assertEquals(Long.valueOf(1), textAutoReplies.getTotalCount());
        assertEquals(1, textAutoReplies.getItems().size());
        TextAutoReply savedTextAutoReply = textAutoReplies.getItems().get(0);
        assertEquals(resourceId.getId(), savedTextAutoReply.getId());
        assertEquals(textAutoReply.getNumber(), savedTextAutoReply.getNumber());
        assertEquals(textAutoReply.getMessage(), savedTextAutoReply.getMessage());
        assertEquals(textAutoReply.getMatch(), savedTextAutoReply.getMatch());

        savedTextAutoReply = api.get(resourceId.getId(), "number,message");
        System.out.println(savedTextAutoReply);

        assertNull(savedTextAutoReply.getId());
        assertNull(savedTextAutoReply.getKeyword());
        assertEquals(textAutoReply.getNumber(), savedTextAutoReply.getNumber());
        assertEquals(textAutoReply.getMessage(), savedTextAutoReply.getMessage());

        api.delete(resourceId.getId());

        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId.getId());
    }
}
