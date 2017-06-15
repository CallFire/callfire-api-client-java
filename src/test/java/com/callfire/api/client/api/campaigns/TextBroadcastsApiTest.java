package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.campaigns.model.*;
import com.callfire.api.client.api.campaigns.model.request.*;
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

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TextBroadcastsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = "/campaigns/textBroadcastsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findTextBroadcasts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindTextBroadcastsRequest request = FindTextBroadcastsRequest.create()
            .limit(5L)
            .name("name")
            .label("label")
            .running(true)
            .build();
        Page<TextBroadcast> broadcasts = client.textBroadcastsApi().find(request);
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(broadcasts), true);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "label=label", "name=name", "running=true");
    }

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createTextBroadcast.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createTextBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextBroadcast textBroadcast = new TextBroadcast();
        textBroadcast.setName("Example API SMS");
        textBroadcast.setFromNumber("19206596476");
        textBroadcast.setMessage("Hello World!");
        TextRecipient r1 = new TextRecipient();
        r1.setPhoneNumber("13233832214");
        TextRecipient r2 = new TextRecipient();
        r2.setPhoneNumber("13233832215");
        textBroadcast.setRecipients(asList(r1, r2));
        textBroadcast.setResumeNextDay(false);
        ResourceId id = client.textBroadcastsApi().create(textBroadcast, true);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("start=true"));
    }

    @Test
    public void testCreateWithRequest() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createTextBroadcast.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createTextBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextBroadcast textBroadcast = new TextBroadcast();
        textBroadcast.setName("Example API SMS");
        textBroadcast.setFromNumber("19206596476");
        textBroadcast.setMessage("Hello World!");
        TextRecipient r1 = new TextRecipient();
        r1.setPhoneNumber("13233832214");
        TextRecipient r2 = new TextRecipient();
        r2.setPhoneNumber("13233832215");
        textBroadcast.setRecipients(asList(r1, r2));
        textBroadcast.setResumeNextDay(false);

        CreateBroadcastRequest<TextBroadcast> request = CreateBroadcastRequest.create()
            .broadcast(textBroadcast)
            .start(true)
            .strictValidation(true)
            .build();

        ResourceId id = client.textBroadcastsApi().create(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("start=true"));
        assertThat(arg.getURI().toString(), containsString("strictValidation=true"));
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getTextBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        TextBroadcast textBroadcast = client.textBroadcastsApi().get(11L, FIELDS);
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(textBroadcast), true);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.textBroadcastsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textBroadcastsApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateTextBroadcast.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        TextBroadcast textBroadcast = new TextBroadcast();
        textBroadcast.setId(11L);
        textBroadcast.setName("Example API SMS updated");
        textBroadcast.setMessage("a new test message");
        textBroadcast.setResumeNextDay(false);
        client.textBroadcastsApi().update(textBroadcast);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));

        client.textBroadcastsApi().update(textBroadcast, true);
        arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
        assertThat(arg.getURI().toString(), containsString("strictValidation=true"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textBroadcastsApi().update(new TextBroadcast());
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
        Page<Batch> batches = client.textBroadcastsApi().getBatches(request);
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

        Recipient r1 = new Recipient();
        r1.setPhoneNumber("12135551122");
        Recipient r2 = new Recipient();
        r2.setPhoneNumber("12135551123");
        AddBatchRequest request = AddBatchRequest.create()
            .campaignId(15L)
            .name("contact batch for text")
            .recipients(asList(r1, r2))
            .scrubDuplicates(true)
            .strictValidation(true)
            .build();
        ResourceId id = client.textBroadcastsApi().addBatch(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
        assertThat(arg.getURI().toString(), containsString("strictValidation=true"));
    }

    @Test
    public void testFindTexts() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getTexts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindBroadcastTextsRequest request = FindBroadcastTextsRequest.create()
            .offset(0L)
            .fields(FIELDS)
            .id(11L)
            .batchId(10L)
            .build();
        Page<Text> texts = client.textBroadcastsApi().findTexts(request);
        assertThat(jsonConverter.serialize(texts), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11/texts"));
        assertThat(arg.getURI().toString(), not(containsString("id=")));
        assertUriContainsQueryParams(arg.getURI(), "offset=0", "batchId=10", ENCODED_FIELDS);
    }

    @Test
    public void getStats() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getStats.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        TextBroadcastStats stats = client.textBroadcastsApi().getStats(11L);
        JSONAssert.assertEquals(expectedJson, jsonConverter.serialize(stats), true);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11/stats"));

        Date begin = new Date();
        Date end = new Date();
        client.textBroadcastsApi().getStats(11L, FIELDS, begin, end);
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
    public void testStart() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textBroadcastsApi().start(null);
        client.textBroadcastsApi().start(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/start"));
    }

    @Test
    public void testStop() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textBroadcastsApi().stop(null);
        client.textBroadcastsApi().stop(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/stop"));
    }

    @Test
    public void testArchive() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.textBroadcastsApi().archive(null);
        client.textBroadcastsApi().archive(10L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString("/10/archive"));
    }

    @Test
    public void testAddRecipients() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/addRecipients.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/addRecipients.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextRecipient r1 = new TextRecipient();
        r1.setPhoneNumber("12135551100");
        TextRecipient r2 = new TextRecipient();
        r2.setPhoneNumber("12135551101");
        List<Text> texts = client.textBroadcastsApi().addRecipients(15L, asList(r1, r2));
        assertThat(jsonConverter.serialize(new ListHolder<>(texts)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
        assertThat(arg.getURI().toString(), not(containsString(ENCODED_FIELDS)));

        client.textBroadcastsApi().addRecipients(15L, asList(r1, r2), FIELDS);
        assertUriContainsQueryParams(captor.getAllValues().get(1).getURI(), ENCODED_FIELDS);
    }

    @Test
    public void testAddRecipientsWithRequest() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/addRecipients.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/addRecipients.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextRecipient r1 = new TextRecipient();
        r1.setPhoneNumber("12135551100");
        TextRecipient r2 = new TextRecipient();
        r2.setPhoneNumber("12135551101");

        AddRecipientsRequest request = AddRecipientsRequest.create()
            .recipients(asList(r1, r2))
            .campaignId(15L)
            .strictValidation(true)
            .fields(FIELDS)
            .build();

        List<Text> texts = client.textBroadcastsApi().addRecipients(request);
        assertThat(jsonConverter.serialize(new ListHolder<>(texts)), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
        assertThat(arg.getURI().toString(), containsString("/15"));
        assertUriContainsQueryParams(captor.getAllValues().get(0).getURI(), ENCODED_FIELDS);
        assertThat(arg.getURI().toString(), containsString("strictValidation=true"));
    }
}
