package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Action.State;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
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

public class CallsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/callstexts/callsApi";

    @Test
    public void testSendCalls() throws Exception {
        String requestJson = getJsonPayload(JSON_PATH + "/request/sendCalls.json");
        String responseJson = getJsonPayload(JSON_PATH + "/response/sendCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        CallRecipient r1 = new CallRecipient();
        r1.setPhoneNumber("12135551100");
        r1.setLiveMessage("Why hello there!");
        CallRecipient r2 = new CallRecipient();
        r2.setPhoneNumber("12135551101");
        r2.setLiveMessage("And hello to you too.");
        List<Call> calls = client.callsApi().send(asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(calls)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));

        calls = client.callsApi().send(asList(r1, r2), 100L, FIELDS);
        arg = captor.getValue();
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("campaignId=100"));
    }

    @Test
    public void testFindCalls() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindCallsRequest request = FindCallsRequest.create()
            .limit(5L)
            .offset(0L)
            .states(asList(State.CALLBACK, State.DISABLED))
            .id(asList(1L, 2L, 3L))
            .build();
        Page<Call> calls = client.callsApi().find(request);
        assertThat(jsonConverter.serialize(calls), equalToIgnoringWhiteSpace(expectedJson));

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
    public void testGetCall() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCall.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Call call = client.callsApi().get(1L);
        HttpUriRequest arg = captor.getValue();
        assertThat(arg.getURI().toString(), not(containsString("fields")));

        call = client.callsApi().get(1L, FIELDS);
        assertThat(jsonConverter.serialize(call), equalToIgnoringWhiteSpace(expectedJson));

        arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testGetCallNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().get(null);
    }
}
