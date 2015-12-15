package com.callfire.api.client.api.keywords;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.keywords.model.Keyword;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.*;

public class KeywordsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/keywords/keywordsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findKeywords.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        List<Keyword> keywords = client.keywordsApi().find(asList("CALLFIRE", "TEST"));
        assertThat(jsonConverter.serialize(new ListHolder<>(keywords)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "keywords=CALLFIRE", "keywords=TEST");
    }

    @Test
    public void testIsAvailable() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse("true");

        Boolean available = client.keywordsApi().isAvailable("TEST");
        assertTrue(available);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
    }
}
