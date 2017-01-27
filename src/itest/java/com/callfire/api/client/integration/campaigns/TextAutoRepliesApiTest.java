package com.callfire.api.client.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.TextAutoRepliesApi;
import com.callfire.api.client.api.campaigns.model.TextAutoReply;
import com.callfire.api.client.api.campaigns.model.request.FindTextAutoRepliesRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.integration.AbstractIntegrationTest;
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
        CallfireClient callfireClient = new CallfireClient(getApiUserName(), getApiUserPassword());
        TextAutoRepliesApi api = callfireClient.textAutoRepliesApi();

        TextAutoReply textAutoReply = new TextAutoReply();
        textAutoReply.setNumber(getDid3());
        textAutoReply.setMessage("test message");
        textAutoReply.setMatch("test match");
        ResourceId resourceId = api.create(textAutoReply);
        assertNotNull(resourceId.getId());

        FindTextAutoRepliesRequest request = FindTextAutoRepliesRequest.create()
            .number(getDid3())
            .build();
        Page<TextAutoReply> textAutoReplies = api.find(request);
        System.out.println(textAutoReplies);
        assertTrue(textAutoReplies.getTotalCount() > 0);
        assertTrue(textAutoReplies.getItems().size() > 0);

        TextAutoReply savedTextAutoReply = api.get(resourceId.getId(), "number,message");
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
