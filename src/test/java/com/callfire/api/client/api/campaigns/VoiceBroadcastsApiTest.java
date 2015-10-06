package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.VoiceBroadcast;
import com.callfire.api.client.api.campaigns.model.VoiceBroadcast.AnsweringMachineConfig;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindVoiceBroadcastsRequest;
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

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class VoiceBroadcastsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/voiceBroadcastsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findVoiceBroadcasts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindVoiceBroadcastsRequest request = FindVoiceBroadcastsRequest.create()
            .limit(5L)
            .name("name")
            .label("label")
            .running(true)
            .build();
        Page<VoiceBroadcast> broadcasts = client.voiceBroadcastsApi().find(request);
        assertThat(jsonConverter.serialize(broadcasts), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "label=label", "name=name", "running=true");
    }

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createVoiceBroadcast.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createVoiceBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        VoiceBroadcast voiceBroadcast = new VoiceBroadcast();
        voiceBroadcast.setName("Example API VB");
        voiceBroadcast.setFromNumber("12135551189");
        voiceBroadcast.setAnsweringMachineConfig(AnsweringMachineConfig.AM_AND_LIVE);
        voiceBroadcast.setLiveSoundText("Hello! This is a live answer text to speech recording");
        voiceBroadcast.setMachineSoundText("This is an answering machine text to speech recording");
        Recipient r1 = new Recipient();
        r1.setPhoneNumber("2135551133");
        voiceBroadcast.setRecipients(Collections.singletonList(r1));
        ResourceId id = client.voiceBroadcastsApi().create(voiceBroadcast, true);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("start=true"));
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getVoiceBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        VoiceBroadcast VoiceBroadcast = client.voiceBroadcastsApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(VoiceBroadcast), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.voiceBroadcastsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.voiceBroadcastsApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateVoiceBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        VoiceBroadcast voiceBroadcast = new VoiceBroadcast();
        voiceBroadcast.setId(11L);
        voiceBroadcast.setName("Example API VB updated");
        voiceBroadcast.setLiveSoundText("Hello! This is an updated VB config tts");
        voiceBroadcast.setAnsweringMachineConfig(AnsweringMachineConfig.LIVE_IMMEDIATE);
        client.voiceBroadcastsApi().update(voiceBroadcast);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        VoiceBroadcast VoiceBroadcast = new VoiceBroadcast();
        client.voiceBroadcastsApi().update(VoiceBroadcast);
    }

    @Test
    public void testGetBatches() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getBatches.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .offset(0L)
            .fields(FIELDS)
            .id(11L)
            .build();
        Page<Batch> batches = client.voiceBroadcastsApi().getBatches(request);
        assertThat(jsonConverter.serialize(batches), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
        assertThat(arg.getURI().toString(), not(containsString("id=")));
        assertUriContainsQueryParams(arg.getURI(), "offset=0", ENCODED_FIELDS);
    }

    @Test
    public void testAddBatch() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/addBatch.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/addBatch.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        Recipient r1 = new Recipient();
        r1.setPhoneNumber("12135551100");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551101");
        AddBatchRequest request = AddBatchRequest.create()
            .campaignId(15L)
            .name("batch name")
            .recipients(asList(r1, r2))
            .build();
        ResourceId id = client.voiceBroadcastsApi().addBatch(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
    }

    @Test
    public void testGetCalls() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .offset(0L)
            .fields(FIELDS)
            .id(11L)
            .build();
        Page<Call> calls = client.voiceBroadcastsApi().getCalls(request);
        assertThat(jsonConverter.serialize(calls), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11/calls"));
        assertThat(arg.getURI().toString(), not(containsString("id=")));
        assertUriContainsQueryParams(arg.getURI(), "offset=0", ENCODED_FIELDS);
    }

    @Test
    public void testStart() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.voiceBroadcastsApi().start(null);
        client.voiceBroadcastsApi().start(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/start"));
    }

    @Test
    public void testStop() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.voiceBroadcastsApi().stop(null);
        client.voiceBroadcastsApi().stop(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/stop"));
    }

    @Test
    public void testArchive() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.voiceBroadcastsApi().archive(null);
        client.voiceBroadcastsApi().archive(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/archive"));
    }

    @Test
    public void testAddRecipients() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/addRecipients.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/addRecipients.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        Recipient r1 = new Recipient();
        r1.setPhoneNumber("12135551100");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551101");
        List<Call> calls = client.voiceBroadcastsApi().addRecipients(15L, asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(calls)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));

        client.voiceBroadcastsApi().addRecipients(15L, asList(r1, r2), FIELDS);
        assertUriContainsQueryParams(captor.getAllValues().get(1).getURI(), ENCODED_FIELDS);
    }
}
