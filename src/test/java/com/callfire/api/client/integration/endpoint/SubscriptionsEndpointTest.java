package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.Subscription;
import com.callfire.api.client.model.request.FindSubscriptionRequest;
import org.junit.Test;

import static com.callfire.api.client.model.Subscription.NotificationFormat.JSON;
import static com.callfire.api.client.model.Subscription.NotificationFormat.XML;
import static com.callfire.api.client.model.Subscription.TriggerEvent.CAMPAIGN_FINISHED;
import static org.junit.Assert.*;

/**
 * integration tests for /subscriptions api endpoint
 */
public class SubscriptionsEndpointTest extends AbstractIntegrationTest {

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        String endpoint = "http://your-site.com/endpoint";

        Subscription subscription = new Subscription();
        subscription.setTriggerEvent(CAMPAIGN_FINISHED);
        subscription.setNotificationFormat(JSON);
        subscription.setEndpoint(endpoint);
        ResourceId resourceId1 = callfireClient.getSubscriptionsEndpoint().create(subscription);
        assertNotNull(resourceId1.getId());
        subscription.setNotificationFormat(XML);
        ResourceId resourceId2 = callfireClient.getSubscriptionsEndpoint().create(subscription);

        FindSubscriptionRequest findRequest = FindSubscriptionRequest.create()
            .setLimit(30L)
            .setFormat(JSON)
            .setFields("items(id,endpoint)")
            .build();
        Page<Subscription> page = callfireClient.getSubscriptionsEndpoint().find(findRequest);
        assertEquals(Long.valueOf(1), page.getTotalCount());
        assertEquals(endpoint, page.getItems().get(0).getEndpoint());
        assertNotNull(page.getItems().get(0).getId());
        assertNull(page.getItems().get(0).getTriggerEvent());
        assertNull(page.getItems().get(0).getNotificationFormat());

        subscription = page.getItems().get(0);
        subscription.setEndpoint(subscription.getEndpoint() + "1");
        callfireClient.getSubscriptionsEndpoint().update(subscription);
        Subscription updated = callfireClient.getSubscriptionsEndpoint().get(resourceId1.getId());
        assertEquals(subscription.getEndpoint(), updated.getEndpoint());

        callfireClient.getSubscriptionsEndpoint().delete(resourceId1.getId());
        callfireClient.getSubscriptionsEndpoint().delete(resourceId2.getId());

        page = callfireClient.getSubscriptionsEndpoint().find(FindSubscriptionRequest.create().build());
        assertEquals(Long.valueOf(0), page.getTotalCount());
        assertTrue(page.getItems().isEmpty());
    }
}
