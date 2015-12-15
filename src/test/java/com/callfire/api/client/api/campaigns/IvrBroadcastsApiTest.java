package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.IvrBroadcast;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindIvrBroadcastsRequest;
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

public class IvrBroadcastsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/ivrBroadcastsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findIvrBroadcasts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindIvrBroadcastsRequest request = FindIvrBroadcastsRequest.create()
            .limit(5L)
            .offset(0L)
            .name("name")
            .inbound(true)
            .build();
        Page<IvrBroadcast> broadcasts = client.ivrBroadcastsApi().find(request);
        assertThat(jsonConverter.serialize(broadcasts), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "name=name", "inbound=true");
    }

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createIvrBroadcast.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createIvrBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        IvrBroadcast ivrBroadcast = new IvrBroadcast();
        ivrBroadcast.setName("Example API IVR");
        ivrBroadcast.setFromNumber("12135551189");
        Recipient r1 = new Recipient();
        r1.setPhoneNumber("12135551100");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551101");
        ivrBroadcast.setRecipients(asList(r1, r2));
        ivrBroadcast.setDialplanXml(
            "<dialplan name=\"Root\"><play type=\"tts\">Congratulations! You have successfully configured a CallFire I V R.</play></dialplan>");
        ResourceId id = client.ivrBroadcastsApi().create(ivrBroadcast, true);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("start=true"));
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getIvrBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        IvrBroadcast ivrBroadcast = client.ivrBroadcastsApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(ivrBroadcast), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.ivrBroadcastsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.ivrBroadcastsApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateIvrBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        IvrBroadcast ivrBroadcast = new IvrBroadcast();
        ivrBroadcast.setId(11L);
        ivrBroadcast.setName("Example API IVR updated");
        ivrBroadcast.setDialplanXml(
            "<dialplan name=\"Root\"><record background=\"true\"/><play type=\"tts\">Congratulations! You have successfully configured a CallFire I V R.</play></dialplan>");
        client.ivrBroadcastsApi().update(ivrBroadcast);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        IvrBroadcast ivrBroadcast = new IvrBroadcast();
        client.ivrBroadcastsApi().update(ivrBroadcast);
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
        Page<Batch> batches = client.ivrBroadcastsApi().getBatches(request);
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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        Recipient r = new Recipient();
        r.setPhoneNumber("12135551100");
        AddBatchRequest request = AddBatchRequest.create()
            .campaignId(15L)
            .name("contact batch name1")
            .recipients(Collections.singletonList(r))
            .scrubDuplicates(true)
            .build();
        ResourceId id = client.ivrBroadcastsApi().addBatch(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
    }

    @Test
    public void testGetCalls() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCalls.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        GetByIdRequest request = GetByIdRequest.create()
            .offset(0L)
            .fields(FIELDS)
            .id(11L)
            .build();
        Page<Call> calls = client.ivrBroadcastsApi().getCalls(request);
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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.ivrBroadcastsApi().start(null);
        client.ivrBroadcastsApi().start(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/start"));
    }

    @Test
    public void testStop() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.ivrBroadcastsApi().stop(null);
        client.ivrBroadcastsApi().stop(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/stop"));
    }

    @Test
    public void testArchive() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.ivrBroadcastsApi().archive(null);
        client.ivrBroadcastsApi().archive(10L);

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
        r1.setPhoneNumber("12135551101");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551102");
        List<Call> calls = client.ivrBroadcastsApi().addRecipients(15L, asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(calls)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));

        client.ivrBroadcastsApi().addRecipients(15L, asList(r1, r2), FIELDS);
        assertUriContainsQueryParams(captor.getAllValues().get(1).getURI(), ENCODED_FIELDS);
    }
}
