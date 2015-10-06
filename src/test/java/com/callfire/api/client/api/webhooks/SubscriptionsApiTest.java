package com.callfire.api.client.api.webhooks;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.webhooks.model.Subscription;
import com.callfire.api.client.api.webhooks.model.request.FindSubscriptionsRequest;
import org.apache.http.client.methods.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.callfire.api.client.api.webhooks.model.Subscription.NotificationFormat.JSON;
import static com.callfire.api.client.api.webhooks.model.Subscription.TriggerEvent.CAMPAIGN_FINISHED;
import static com.callfire.api.client.api.webhooks.model.Subscription.TriggerEvent.CAMPAIGN_STARTED;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SubscriptionsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/webhooks/subscriptionsApi";

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createSubscription.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createSubscription.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        Subscription subscription = new Subscription();
        subscription.setEnabled(true);
        subscription.setEndpoint("http://www.example.com/endpoint");
        subscription.setNotificationFormat(JSON);
        subscription.setBroadcastId(14L);
        subscription.setTriggerEvent(CAMPAIGN_STARTED);
        ResourceId id = client.subscriptionsApi().create(subscription);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findSubscriptions.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindSubscriptionsRequest request = FindSubscriptionsRequest.create()
            .limit(5L)
            .offset(0L)
            .campaignId(10L)
            .format(JSON)
            .build();
        Page<Subscription> subscriptions = client.subscriptionsApi().find(request);
        assertThat(jsonConverter.serialize(subscriptions), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "campaignId=10", "format=JSON");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getSubscription.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Subscription subscription = client.subscriptionsApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(subscription), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.subscriptionsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.subscriptionsApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateSubscription.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        Subscription subscription = new Subscription();
        subscription.setId(11L);
        subscription.setTriggerEvent(CAMPAIGN_FINISHED);
        client.subscriptionsApi().update(subscription);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.subscriptionsApi().update(new Subscription());
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.subscriptionsApi().delete(11L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testDeleteNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.subscriptionsApi().delete(null);
    }
}
