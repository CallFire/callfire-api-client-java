package com.callfire.api.client.api.integration.webhooks;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.webhooks.model.Subscription;
import com.callfire.api.client.api.webhooks.model.request.FindSubscriptionRequest;
import org.junit.Test;

import static com.callfire.api.client.api.webhooks.model.Subscription.NotificationFormat.JSON;
import static com.callfire.api.client.api.webhooks.model.Subscription.NotificationFormat.XML;
import static com.callfire.api.client.api.webhooks.model.Subscription.TriggerEvent.CAMPAIGN_FINISHED;
import static org.junit.Assert.*;

/**
 * integration tests for /subscriptions api endpoint
 */
public class SubscriptionsApiTest extends AbstractIntegrationTest {

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        String endpoint = "http://your-site.com/endpoint";

        Subscription subscription = new Subscription();
        subscription.setTriggerEvent(CAMPAIGN_FINISHED);
        subscription.setNotificationFormat(JSON);
        subscription.setEndpoint(endpoint);
        ResourceId resourceId1 = callfireClient.getSubscriptionsApi().createSubscription(subscription);
        assertNotNull(resourceId1.getId());
        subscription.setNotificationFormat(XML);
        ResourceId resourceId2 = callfireClient.getSubscriptionsApi().createSubscription(subscription);

        FindSubscriptionRequest findRequest = FindSubscriptionRequest.create()
            .limit(30L)
            .format(JSON)
            .fields("items(id,endpoint)")
            .build();
        Page<Subscription> page = callfireClient.getSubscriptionsApi().findSubscriptions(findRequest);
        assertEquals(Long.valueOf(1), page.getTotalCount());
        assertEquals(endpoint, page.getItems().get(0).getEndpoint());
        assertNotNull(page.getItems().get(0).getId());
        assertNull(page.getItems().get(0).getTriggerEvent());
        assertNull(page.getItems().get(0).getNotificationFormat());

        subscription = page.getItems().get(0);
        subscription.setEndpoint(subscription.getEndpoint() + "1");
        callfireClient.getSubscriptionsApi().updateSubscription(subscription);
        Subscription updated = callfireClient.getSubscriptionsApi().getSubscription(resourceId1.getId());
        assertEquals(subscription.getEndpoint(), updated.getEndpoint());

        callfireClient.getSubscriptionsApi().deleteSubscription(resourceId1.getId());
        callfireClient.getSubscriptionsApi().deleteSubscription(resourceId2.getId());

        page = callfireClient.getSubscriptionsApi().findSubscriptions(FindSubscriptionRequest.create().build());
        assertEquals(Long.valueOf(0), page.getTotalCount());
        assertTrue(page.getItems().isEmpty());
    }
}
