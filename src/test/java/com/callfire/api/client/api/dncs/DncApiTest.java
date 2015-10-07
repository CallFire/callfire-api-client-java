package com.callfire.api.client.api.dncs;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.request.FindDncContactsRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.*;

public class DncApiTest extends AbstractApiTest {

    protected static final String RESPONSES_PATH = "/dncs/dncApi/response/";
    protected static final String REQUESTS_PATH = "/dncs/dncApi/request/";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + RESPONSES_PATH + "findDncList.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindDncContactsRequest request = FindDncContactsRequest.create()
            .limit(1L)
            .offset(5L)
            .fields(FIELDS)
            .prefix("1")
            .dncListId(TEST_ID)
            .dncListName("test")
            .callDnc(true)
            .textDnc(true)
            .build();
        Page<DoNotContact> doNotContactList = client.dncApi().find(request);
        assertThat(jsonConverter.serialize(doNotContactList), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
        assertThat(arg.getURI().toString(), containsString("dncListId=" + TEST_ID));
        assertThat(arg.getURI().toString(), containsString("dncListName=test"));
        assertThat(arg.getURI().toString(), containsString("callDnc=true"));
        assertThat(arg.getURI().toString(), containsString("textDnc=true"));
    }

    @Test
    public void testUpdate() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + REQUESTS_PATH + "updateDnc.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        DoNotContact dnc = new DoNotContact();
        dnc.setCall(true);
        dnc.setListId(TEST_ID);
        dnc.setNumber(TEST_ID.toString());
        dnc.setText(true);
        client.dncApi().update(dnc);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }
}
