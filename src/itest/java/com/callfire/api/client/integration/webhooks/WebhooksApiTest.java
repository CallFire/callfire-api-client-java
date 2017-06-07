package com.callfire.api.client.integration.webhooks;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.webhooks.WebhooksApi;
import com.callfire.api.client.api.webhooks.model.ResourceType;
import com.callfire.api.client.api.webhooks.model.Webhook;
import com.callfire.api.client.api.webhooks.model.WebhookResource;
import com.callfire.api.client.api.webhooks.model.request.FindWebhooksRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * integration tests for /webhooks api endpoint
 */
public class WebhooksApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getApiUserName(), getApiUserPassword());
        WebhooksApi api = callfireClient.webhooksApi();

        Webhook webhook = new Webhook();
        webhook.setCallback("test_callback");
        webhook.setResource(ResourceType.TEXT_BROADCAST);
        ResourceType.ResourceEvent[] ev = {ResourceType.ResourceEvent.STARTED};
        webhook.setEvents(new TreeSet<>(Arrays.asList(ev)));
        webhook.setName("test_name1");
        webhook.setSingleUse(true);
        ResourceId resourceId1 = api.create(webhook);
        assertNotNull(resourceId1.getId());
        webhook.setName("test_name2");
        ResourceId resourceId2 = api.create(webhook);
        webhook.setResource(ResourceType.CONTACT_LIST);
        ResourceType.ResourceEvent[] ev2 = {ResourceType.ResourceEvent.VALIDATION_FINISHED};
        webhook.setEvents(new TreeSet<>(Arrays.asList(ev2)));
        ResourceId resourceId3 = api.create(webhook);
        assertNotNull(resourceId3.getId());

        FindWebhooksRequest findRequest = FindWebhooksRequest.create()
            .limit(30L)
            .name("test_name1")
            .fields("items(id,callback,name,resource,events,singleUse)")
            .build();
        Page<Webhook> page = api.find(findRequest);
        assertTrue(page.getItems().size() > 0);
        assertEquals("test_callback", page.getItems().get(0).getCallback());
        assertEquals("test_name1", page.getItems().get(0).getName());
        assertNotNull(page.getItems().get(0).getId());
        assertEquals(ResourceType.TEXT_BROADCAST, page.getItems().get(0).getResource());
        assertEquals(1, page.getItems().get(0).getEvents().size());
        assertTrue(page.getItems().get(0).getSingleUse());

        findRequest = FindWebhooksRequest.create()
            .limit(30L)
            .name("test_name1")
            .build();
        page = api.find(findRequest);

        webhook = page.getItems().get(0);
        webhook.setName("test_name2");
        webhook.setSingleUse(false);
        api.update(webhook);
        Webhook updated = api.get(webhook.getId());
        assertEquals(webhook.getResource(), updated.getResource());
        assertFalse(page.getItems().get(0).getSingleUse());

        api.delete(resourceId1.getId());
        api.delete(resourceId2.getId());
        api.delete(resourceId3.getId());

        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId1.getId());
        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId2.getId());
    }

    @Test
    public void testResourceTypeOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getApiUserName(), getApiUserPassword());
        WebhooksApi api = callfireClient.webhooksApi();
        List<WebhookResource> resources = api.findWebhookResources();
        assertNotNull(resources);
        assertEquals(resources.size(), 9);
        resources = api.findWebhookResources("items(resource)");
        assertNotNull(resources.get(0).getResource());
        assertEquals(resources.get(0).getSupportedEvents().size(), 0);

        WebhookResource resource = api.findWebhookResource(ResourceType.CALL_BROADCAST);
        assertNotNull(resource);
        assertNotNull(resource.getSupportedEvents());
        assertEquals(resource.getResource(), ResourceType.CALL_BROADCAST.toString());

        resource = api.findWebhookResource(ResourceType.CALL_BROADCAST, "resource");
        assertNotNull(resource.getResource());
        assertEquals(resource.getSupportedEvents().size(), 0);
    }
}
