package com.callfire.api.client.api.integration.webhooks;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.webhooks.WebhooksApi;
import com.callfire.api.client.api.webhooks.model.Webhook;
import com.callfire.api.client.api.webhooks.model.request.FindWebhooksRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

/**
 * integration tests for /webhooks api endpoint
 */
public class WebhooksApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        WebhooksApi api = callfireClient.webhooksApi();

        Webhook webhook = new Webhook();
        webhook.setCallback("test_callback");
        webhook.setResource("test_resource");
        webhook.setName("test_name1");
        ResourceId resourceId1 = api.create(webhook);
        assertNotNull(resourceId1.getId());
        webhook.setName("test_name2");
        ResourceId resourceId2 = api.create(webhook);

        FindWebhooksRequest findRequest = FindWebhooksRequest.create()
            .limit(30L)
            .name("test_name1")
            .fields("items(id,callback)")
            .build();
        Page<Webhook> page = api.find(findRequest);
        assertThat(page.getItems(), hasSize(equalTo(1)));
        assertEquals("test_callback", page.getItems().get(0).getCallback());
        assertNotNull(page.getItems().get(0).getId());
        assertNull(page.getItems().get(0).getName());
        assertNull(page.getItems().get(0).getResource());

        webhook = page.getItems().get(0);
        webhook.setResource(webhook.getResource() + "1");
        api.update(webhook);
        Webhook updated = api.get(webhook.getId());
        assertEquals(webhook.getResource(), updated.getResource());

        api.delete(resourceId1.getId());
        api.delete(resourceId2.getId());

        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId1.getId());
        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId2.getId());
    }
}
