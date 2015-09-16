package com.callfire.api.client.integration.endpoint.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.endpoint.campaigns.TextAutoRepliesEndpoint;
import com.callfire.api.client.integration.endpoint.AbstractIntegrationTest;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.campaigns.TextAutoReply;
import com.callfire.api.client.model.request.FindTextAutoRepliesRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * integration tests for /campaings/text-auto-replys api endpoint
 */
public class TextAutoRepliesEndpointTest extends AbstractIntegrationTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        TextAutoRepliesEndpoint endpoint = callfireClient.getCampaignsEndpoint().getTextAutoRepliesEndpoint();

        TextAutoReply textAutoReply = new TextAutoReply();
        textAutoReply.setNumber(getDid1());
        textAutoReply.setMessage("test message");
        textAutoReply.setMatch("test match");
        ResourceId resourceId = endpoint.createTextAutoReply(textAutoReply);
        assertNotNull(resourceId.getId());

        FindTextAutoRepliesRequest request = FindTextAutoRepliesRequest.FindTextAutoRepliesBuilder.create()
            .setNumber(getDid1())
            .build();
        Page<TextAutoReply> textAutoReplies = endpoint.findTextAutoReplies(request);
        assertEquals(Long.valueOf(1), textAutoReplies.getTotalCount());
        assertEquals(1, textAutoReplies.getItems().size());
        TextAutoReply savedTextAutoReply = textAutoReplies.getItems().get(0);
        assertEquals(resourceId.getId(), savedTextAutoReply.getId());
        assertEquals(textAutoReply.getNumber(), savedTextAutoReply.getNumber());
        assertEquals(textAutoReply.getMessage(), savedTextAutoReply.getMessage());
        assertEquals(textAutoReply.getMatch(), savedTextAutoReply.getMatch());

        savedTextAutoReply = endpoint.getTextAutoReply(resourceId.getId(), "number,message");
        assertNull(savedTextAutoReply.getId());
        assertNull(savedTextAutoReply.getKeyword());
        assertEquals(textAutoReply.getNumber(), savedTextAutoReply.getNumber());
        assertEquals(textAutoReply.getMessage(), savedTextAutoReply.getMessage());

        endpoint.deleteTextAutoReply(resourceId.getId());

        expect404NotFoundCallfireApiException(ex);
        endpoint.getTextAutoReply(resourceId.getId());
    }
}
