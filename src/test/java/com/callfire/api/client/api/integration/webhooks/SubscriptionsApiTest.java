package com.callfire.api.client.api.integration.webhooks;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.webhooks.SubscriptionsApi;
import com.callfire.api.client.api.webhooks.model.Subscription;
import com.callfire.api.client.api.webhooks.model.request.FindSubscriptionsRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.callfire.api.client.api.webhooks.model.Subscription.NotificationFormat.JSON;
import static com.callfire.api.client.api.webhooks.model.Subscription.NotificationFormat.SOAP;
import static com.callfire.api.client.api.webhooks.model.Subscription.TriggerEvent.CAMPAIGN_FINISHED;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

/**
 * integration tests for /subscriptions api endpoint
 */
public class SubscriptionsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        SubscriptionsApi api = callfireClient.getSubscriptionsApi();
        String endpoint = "http://your-site.com/endpoint";

        Subscription subscription = new Subscription();
        subscription.setTriggerEvent(CAMPAIGN_FINISHED);
        subscription.setNotificationFormat(JSON);
        subscription.setEndpoint(endpoint);
        ResourceId resourceId1 = api.create(subscription);
        assertNotNull(resourceId1.getId());
        subscription.setNotificationFormat(SOAP);
        ResourceId resourceId2 = api.create(subscription);

        FindSubscriptionsRequest findRequest = FindSubscriptionsRequest.create()
            .limit(30L)
            .format(SOAP)
            .fields("items(id,notificationFormat)")
            .build();
        Page<Subscription> page = api.find(findRequest);
        assertThat(page.getItems(), hasSize(greaterThan(0)));
        assertEquals(SOAP, page.getItems().get(0).getNotificationFormat());
        assertNotNull(page.getItems().get(0).getId());
        assertNull(page.getItems().get(0).getTriggerEvent());
        assertNull(page.getItems().get(0).getEndpoint());

        subscription = page.getItems().get(0);
        subscription.setEndpoint(subscription.getEndpoint() + "1");
        api.update(subscription);
        Subscription updated = api.get(subscription.getId());
        assertEquals(subscription.getEndpoint(), updated.getEndpoint());

        api.delete(resourceId1.getId());
        api.delete(resourceId2.getId());

        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId1.getId());
        expect404NotFoundCallfireApiException(ex);
        api.get(resourceId2.getId());
    }
}
