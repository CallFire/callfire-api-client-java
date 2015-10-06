package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Action.State;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.request.FindTextsRequest;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TextsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/callstexts/textsApi";

    @Test
    public void testSendTexts() throws Exception {
        String requestJson = getJsonPayload(JSON_PATH + "/request/sendTexts.json");
        String responseJson = getJsonPayload(JSON_PATH + "/response/sendTexts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        TextRecipient r1 = new TextRecipient();
        r1.setPhoneNumber("12135551100");
        r1.setMessage("Hello World!");
        TextRecipient r2 = new TextRecipient();
        r2.setPhoneNumber("12135551101");
        r2.setMessage("Testing 1 2 3");
        List<Text> texts = client.textsApi().send(asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(texts)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));

        texts = client.textsApi().send(asList(r1, r2), 100L, FIELDS);
        arg = captor.getValue();
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("campaignId=100"));
    }

    @Test
    public void testFindTexts() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findTexts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindTextsRequest request = FindTextsRequest.create()
            .limit(5L)
            .offset(0L)
            .states(asList(State.CALLBACK, State.DISABLED))
            .id(asList(1L, 2L, 3L))
            .build();
        Page<Text> texts = client.textsApi().find(request);
        assertThat(jsonConverter.serialize(texts), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=5"));
        assertThat(arg.getURI().toString(), containsString("offset=0"));
        assertThat(arg.getURI().toString(), containsString("states=" + encode("CALLBACK,DISABLED")));
        assertThat(arg.getURI().toString(), containsString("id=1"));
        assertThat(arg.getURI().toString(), containsString("id=2"));
        assertThat(arg.getURI().toString(), containsString("id=3"));
    }

    @Test
    public void testGetText() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getText.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Text text = client.textsApi().get(1L);
        HttpUriRequest arg = captor.getValue();
        assertThat(arg.getURI().toString(), not(containsString("fields")));

        text = client.textsApi().get(1L, FIELDS);
        assertThat(jsonConverter.serialize(text), equalToIgnoringWhiteSpace(expectedJson));

        arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testGetTextNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textsApi().get(null);
    }
}
