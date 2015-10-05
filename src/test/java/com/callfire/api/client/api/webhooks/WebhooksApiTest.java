package com.callfire.api.client.api.webhooks;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.webhooks.model.Webhook;
import com.callfire.api.client.api.webhooks.model.request.FindWebhooksRequest;
import org.apache.http.client.methods.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WebhooksApiTest extends AbstractApiTest {

    @Test
    public void testCreateWebhook() throws Exception {
        String responseJson = getJsonPayload(BASE_PATH + "/webhooks/webhooksApi/response/createWebhook.json");
        String requestJson = getJsonPayload(BASE_PATH + "/webhooks/webhooksApi/request/createWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        Webhook webhook = new Webhook();
        webhook.setName("API hook");
        webhook.setResource("textCampaign");
        webhook.setEvents(new HashSet<>(asList("start", "stop")));
        webhook.setCallback("http://cool.site.xyz/webhook");
        ResourceId id = client.webhooksApi().create(webhook);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testFindWebhooks() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/webhooks/webhooksApi/response/findWebhooks.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindWebhooksRequest request = FindWebhooksRequest.create()
            .limit(5L)
            .offset(0L)
            .enabled(false)
            .resource("resource")
            .build();
        Page<Webhook> webhooks = client.webhooksApi().find(request);
        assertThat(jsonConverter.serialize(webhooks), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=5"));
        assertThat(arg.getURI().toString(), containsString("offset=0"));
        assertThat(arg.getURI().toString(), containsString("resource=resource"));
        assertThat(arg.getURI().toString(), containsString("enabled=false"));
    }

    @Test
    public void testGetWebhook() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/webhooks/webhooksApi/response/getWebhook.json");
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
    public void testUpdateWebhook() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/webhooks/webhooksApi/request/updateWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        Webhook webhook = new Webhook();
        webhook.setId(11L);
        webhook.setName("API hook");
        webhook.setResource("textCampaign");
        webhook.setEvents(new HashSet<>(Collections.singletonList("stop")));
        webhook.setCallback("https://callfire.com/stopTextsOnly");
        client.webhooksApi().update(webhook);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testDeleteWebhook() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.webhooksApi().delete(11L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }
}
