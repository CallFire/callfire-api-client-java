package com.callfire.api.client.api.webhooks;

import com.callfire.api.client.ModelValidationException;
import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.webhooks.model.ResourceType;
import com.callfire.api.client.api.webhooks.model.Webhook;
import com.callfire.api.client.api.webhooks.model.request.FindWebhooksRequest;
import org.apache.http.client.methods.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent;
import static com.callfire.api.client.api.webhooks.model.ResourceType.TEXT_BROADCAST;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WebhooksApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/webhooks/webhooksApi";

    @Test
    public void testWebhookValidate() throws Exception {
        Webhook webhook = new Webhook();
        webhook.setId(1L);
        webhook.setResource(ResourceType.VOICE_BROADCAST);
        webhook.getEvents().add(ResourceEvent.STARTED);
        webhook.getEvents().add(ResourceEvent.FINISHED);
        webhook.getEvents().add(ResourceEvent.UNKNOWN);
        ex.expect(ModelValidationException.class);
        ex.expectMessage("Event [unknown] is unsupported for voiceCampaign resource, " +
            "supported events are: [");
        client.webhooksApi().update(webhook);

        webhook.getEvents().remove(ResourceEvent.UNKNOWN);
        client.webhooksApi().update(webhook);
    }

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createWebhook.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        Webhook webhook = new Webhook();
        webhook.setName("API hook");
        webhook.setResource(TEXT_BROADCAST);
        webhook.getEvents().add(ResourceEvent.STOPPED);
        webhook.getEvents().add(ResourceEvent.STARTED);
        webhook.setCallback("http://cool.site.xyz/webhook");
        ResourceId id = client.webhooksApi().create(webhook);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findWebhooks.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindWebhooksRequest request = FindWebhooksRequest.create()
            .limit(5L)
            .offset(0L)
            .enabled(false)
            .resource(ResourceType.IVR_BROADCAST)
            .event(ResourceEvent.FINISHED)
            .build();
        Page<Webhook> webhooks = client.webhooksApi().find(request);
        assertThat(jsonConverter.serialize(webhooks), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "resource=ivrCampaign", "enabled=false",
            "event=finish");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Webhook webhook = client.webhooksApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(webhook), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.webhooksApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.webhooksApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        Webhook webhook = new Webhook();
        webhook.setId(11L);
        webhook.setName("API hook");
        webhook.setResource(TEXT_BROADCAST);
        webhook.getEvents().add(ResourceEvent.STOPPED);
        webhook.setCallback("https://callfire.com/stopTextsOnly");
        client.webhooksApi().update(webhook);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        Webhook webhook = new Webhook();
        client.webhooksApi().update(webhook);
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.webhooksApi().delete(11L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testDeleteNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.webhooksApi().delete(null);
    }
}
