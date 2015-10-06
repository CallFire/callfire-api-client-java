package com.callfire.api.client.api.keywords;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.keywords.model.KeywordLease;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class KeywordLeasesApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/keywords/keywordLeasesApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findKeywordLeases.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        CommonFindRequest request = CommonFindRequest.create()
            .limit(5L)
            .offset(0L)
            .build();
        Page<KeywordLease> leases = client.keywordLeasesApi().find(request);
        assertThat(jsonConverter.serialize(leases), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getKeywordLease.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        KeywordLease keywordLease = client.keywordLeasesApi().get("CALLFIRE", FIELDS);
        assertThat(jsonConverter.serialize(keywordLease), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.keywordLeasesApi().get("CF");
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullKeyword() throws Exception {
        ex.expectMessage("keyword cannot be blank");
        ex.expect(NullPointerException.class);
        client.keywordLeasesApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateKeywordLease.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        KeywordLease keywordLease = new KeywordLease();
        keywordLease.setKeyword("TEST");
        keywordLease.setAutoRenew(false);
        client.keywordLeasesApi().update(keywordLease);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/keywords/leases/TEST"));
    }

    @Test
    public void testUpdateNullKeyword() throws Exception {
        ex.expectMessage("keyword cannot be blank");
        ex.expect(NullPointerException.class);
        client.keywordLeasesApi().update(new KeywordLease());
    }
}
