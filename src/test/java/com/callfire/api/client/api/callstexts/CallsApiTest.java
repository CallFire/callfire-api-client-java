package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Action.State;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.callstexts.model.request.SendCallsRequest;
import com.callfire.api.client.api.campaigns.model.CallRecording;
import com.callfire.api.client.api.campaigns.model.Voice;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.skyscreamer.jsonassert.JSONAssert;

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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        CallRecipient r1 = new CallRecipient();
        r1.setPhoneNumber("12135551100");
        r1.setLiveMessage("Why hello there!");
        r1.setTransferDigit("1");
        r1.setTransferMessage("testMessage");
        r1.setTransferNumber("12135551101");
        CallRecipient r2 = new CallRecipient();
        r2.setPhoneNumber("12135551101");
        r2.setLiveMessage("And hello to you too.");
        List<Call> calls = client.callsApi().send(asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(calls)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));
    }

    @Test
    public void testSendCallsUsingRequest() throws Exception {
        String requestJson = getJsonPayload(JSON_PATH + "/request/sendCalls.json");
        String responseJson = getJsonPayload(JSON_PATH + "/response/sendCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        CallRecipient r1 = new CallRecipient();
        r1.setPhoneNumber("12135551100");
        r1.setLiveMessage("Why hello there!");
        r1.setTransferDigit("1");
        r1.setTransferMessage("testMessage");
        r1.setTransferNumber("12135551101");
        CallRecipient r2 = new CallRecipient();
        r2.setPhoneNumber("12135551101");
        r2.setLiveMessage("And hello to you too.");
        SendCallsRequest request = SendCallsRequest.create()
            .recipients(asList(r1, r2))
            .campaignId(100L)
            .defaultLiveMessage("defaultLiveMessage")
            .defaultMachineMessage("defaultMachineMessage")
            .defaultLiveMessageSoundId(123L)
            .defaultMachineMessageSoundId(321L)
            .defaultVoice(Voice.FEMALE1)
            .fields(FIELDS)
            .build();
        List<Call> calls = client.callsApi().send(request);
        assertThat(jsonConverter.serialize(new ListHolder<>(calls)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("campaignId=100"));
        assertThat(arg.getURI().toString(), containsString("defaultLiveMessage=defaultLiveMessage"));
        assertThat(arg.getURI().toString(), containsString("defaultMachineMessage=defaultMachineMessage"));
        assertThat(arg.getURI().toString(), containsString("defaultLiveMessageSoundId=123"));
        assertThat(arg.getURI().toString(), containsString("defaultMachineMessageSoundId=321"));
        assertThat(arg.getURI().toString(), containsString("defaultVoice=FEMALE1"));
    }

    @Test
    public void testFindCalls() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindCallsRequest request = FindCallsRequest.create()
            .limit(5L)
            .offset(0L)
            .states(asList(State.CALLBACK, State.DISABLED))
            .id(asList(1L, 2L, 3L))
            .batchId(100L)
            .label("12135551102")
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
        assertThat(arg.getURI().toString(), containsString("batchId=100"));
    }

    @Test
    public void testGetCall() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCall.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

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

    @Test
    public void testGetCallRecordingsWithNullCallId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecordings(null);
    }

    @Test
    public void testGetCallRecordings() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallRecordings.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        List<CallRecording> callRecordings = client.callsApi().getCallRecordings(1234L);
        assertThat(jsonConverter.serialize(new ListHolder<>(callRecordings)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/1234"));
    }

    @Test
    public void testGetCallRecordingsWithAdditionalParams() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallRecordings.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        List<CallRecording> callRecordings = client.callsApi().getCallRecordings(1234L, FIELDS);
        assertThat(jsonConverter.serialize(new ListHolder<>(callRecordings)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/1234"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testGetCallRecordingByNameWithNullCallId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecordingByName(null, null);
    }

    @Test
    public void testGetCallRecordingByNameWithNullCallRecordingName() throws Exception {
        ex.expectMessage("recordingName cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecordingByName(10L, null);
    }

    @Test
    public void testGetCallRecordingByName() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallRecording.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CallRecording rec = client.callsApi().getCallRecordingByName(10L, "testName");

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(rec), true);
        assertThat(arg.getURI().toString(), containsString("/10/recordings/testName"));
    }

    @Test
    public void testGetCallRecordingByNameWithAdditionalParams() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallRecording.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CallRecording rec = client.callsApi().getCallRecordingByName(10L, "testName", FIELDS);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(rec), true);
        assertThat(arg.getURI().toString(), containsString("/10/recordings/testName"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testGetCallRecordingMp3ByNameWithNullCallId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecordingMp3ByName(null, null);
    }

    @Test
    public void testGetCallRecordingMp3ByNameWithNullCallRecordingName() throws Exception {
        ex.expectMessage("recordingName cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecordingMp3ByName(10L, null);
    }

    @Test
    public void testGetCallRecordingWithNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecording(null);
    }

    @Test
    public void testGetCallRecording() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallRecording.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CallRecording rec = client.callsApi().getCallRecording(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(rec), true);
        assertThat(arg.getURI().toString(), containsString("/10"));
    }

    @Test
    public void testGetCallRecordingWithAdditionalParams() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallRecording.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CallRecording rec = client.callsApi().getCallRecording(10L, FIELDS);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(rec), true);
        assertThat(arg.getURI().toString(), containsString("/10"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

    @Test
    public void testGetCallRecordingMp3WithNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callsApi().getCallRecordingMp3(null);
    }
}
