package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.AnsweringMachineConfig;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.CallBroadcast;
import com.callfire.api.client.api.campaigns.model.CallBroadcastSounds;
import com.callfire.api.client.api.campaigns.model.CallBroadcastStats;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastCallsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindCallBroadcastsRequest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CallBroadcastsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = "/campaigns/callBroadcastsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findCallBroadcasts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindCallBroadcastsRequest request = FindCallBroadcastsRequest.create()
            .limit(5L)
            .name("name")
            .label("label")
            .running(true)
            .build();
        Page<CallBroadcast> broadcasts = client.callBroadcastsApi().find(request);
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(broadcasts), true);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "label=label", "name=name", "running=true");
    }

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createCallBroadcast.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createCallBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        CallBroadcast callBroadcast = new CallBroadcast();
        callBroadcast.setName("Example API VB");
        callBroadcast.setFromNumber("12135551189");
        callBroadcast.setAnsweringMachineConfig(AnsweringMachineConfig.AM_AND_LIVE);
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText("Hello! This is a live answer text to speech recording");
        sounds.setMachineSoundText("This is an answering machine text to speech recording");
        callBroadcast.setSounds(sounds);
        Recipient r1 = new Recipient();
        r1.setPhoneNumber("2135551133");
        callBroadcast.setRecipients(Collections.singletonList(r1));
        ResourceId id = client.callBroadcastsApi().create(callBroadcast, true);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        JSONAssert.assertEquals(requestJson, extractHttpEntity(arg), true);
        assertThat(arg.getURI().toString(), containsString("start=true"));
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCallBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CallBroadcast callBroadcast = client.callBroadcastsApi().get(11L, FIELDS);
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(callBroadcast), true);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.callBroadcastsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callBroadcastsApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateCallBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        CallBroadcast callBroadcast = new CallBroadcast();
        callBroadcast.setId(11L);
        callBroadcast.setName("Example API VB updated");
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setMachineSoundId(1258704003L);
        sounds.setLiveSoundText("Hello! This is an updated VB config tts");
        callBroadcast.setSounds(sounds);
        callBroadcast.setAnsweringMachineConfig(AnsweringMachineConfig.LIVE_IMMEDIATE);
        client.callBroadcastsApi().update(callBroadcast);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        JSONAssert.assertEquals(expectedJson, extractHttpEntity(arg), true);
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callBroadcastsApi().update(new CallBroadcast());
    }

    @Test
    public void testGetBatches() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getBatches.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .offset(0L)
            .fields(FIELDS)
            .id(11L)
            .build();
        Page<Batch> batches = client.callBroadcastsApi().getBatches(request);
        assertThat(jsonConverter.serialize(batches), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
        assertThat(arg.getURI().toString(), not(containsString("id=")));
        assertUriContainsQueryParams(arg.getURI(), "offset=0", ENCODED_FIELDS);
    }

    @Test
    public void getStats() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getStats.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CallBroadcastStats stats = client.callBroadcastsApi().getStats(11L);
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(stats), true);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11/stats"));

        Date begin = new Date();
        Date end = new Date();
        client.callBroadcastsApi().getStats(11L, FIELDS, begin, end);
        assertThat(arg.getURI().toString(), containsString("/11/stats"));
        assertThat(captor.getAllValues().get(1).getURI().toString(), containsString("/11/stats"));
        assertThat(captor.getAllValues().get(1).getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(captor.getAllValues().get(1).getURI().toString(), containsString("begin=" + begin.getTime()));
        assertThat(captor.getAllValues().get(1).getURI().toString(), containsString("end=" + end.getTime()));
    }

    @Test
    public void getStatsNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textBroadcastsApi().getStats(null);
    }

    @Test
    public void testAddBatch() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/addBatch.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/addBatch.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        Recipient r1 = new Recipient();
        r1.setPhoneNumber("12135551100");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551101");
        AddBatchRequest request = AddBatchRequest.create()
            .campaignId(15L)
            .name("batch name")
            .recipients(asList(r1, r2))
            .build();
        ResourceId id = client.callBroadcastsApi().addBatch(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
    }

    @Test
    public void testFindCalls() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindBroadcastCallsRequest request = FindBroadcastCallsRequest.create()
            .offset(0L)
            .fields(FIELDS)
            .batchId(10L)
            .id(11L)
            .build();
        Page<Call> calls = client.callBroadcastsApi().findCalls(request);
        assertThat(jsonConverter.serialize(calls), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11/calls"));
        assertThat(arg.getURI().toString(), not(containsString("id=")));
        assertUriContainsQueryParams(arg.getURI(), "offset=0", "batchId=10", ENCODED_FIELDS);
    }

    @Test
    public void testStart() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callBroadcastsApi().start(null);
        client.callBroadcastsApi().start(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/start"));
    }

    @Test
    public void testStop() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callBroadcastsApi().stop(null);
        client.callBroadcastsApi().stop(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/stop"));
    }

    @Test
    public void testArchive() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.callBroadcastsApi().archive(null);
        client.callBroadcastsApi().archive(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/archive"));
    }

    @Test
    public void testAddRecipients() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/addRecipients.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/addRecipients.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        Recipient r1 = new Recipient();
        r1.setPhoneNumber("12135551100");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551101");
        List<Call> calls = client.callBroadcastsApi().addRecipients(15L, asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(calls)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));

        client.callBroadcastsApi().addRecipients(15L, asList(r1, r2), FIELDS);
        assertUriContainsQueryParams(captor.getAllValues().get(1).getURI(), ENCODED_FIELDS);
    }
}
