package com.callfire.api.client.api.numbers;

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.numbers.model.Number;
import com.callfire.api.client.api.numbers.model.Region;
import com.callfire.api.client.api.numbers.model.request.FindNumberRegionsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumbersLocalRequest;
import com.callfire.api.client.api.numbers.model.request.FindTollfreeNumbersRequest;

public class NumbersApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/numbers/numbersApi";

    @Test
    public void testFindNumbersLocal() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findNumbersLocal.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindNumbersLocalRequest request = FindNumbersLocalRequest.create()
            .limit(1L)
            .offset(2L)
            .zipcode("1234")
            .state("LA")
            .build();
        List<Number> numbers = client.numbersApi().findNumbersLocal(request);
        assertThat(jsonConverter.serialize(new ListHolder<>(numbers)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=1", "zipcode=1234", "state=LA");
    }

    @Test
    public void testFindNumberRegions() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findNumberRegions.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindNumberRegionsRequest request = FindNumberRegionsRequest.create()
            .limit(1L)
            .offset(2L)
            .zipcode("1234")
            .state("LA")
            .build();
        Page<Region> regions = client.numbersApi().findNumberRegions(request);
        assertThat(jsonConverter.serialize(regions), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=1", "zipcode=1234", "state=LA");
    }

    @Test
    public void testFindNumbersTollfree() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findNumbersTollfree.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CommonFindRequest request = CommonFindRequest.create()
            .limit(1L)
            .offset(2L)
            .build();
        List<Number> numbers = client.numbersApi().findNumbersTollfree(request);
        assertThat(jsonConverter.serialize(new ListHolder<>(numbers)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=1", "offset=2");
    }

    @Test
    public void testFindNumbersTollfreeWithPattern() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findNumbersTollfree.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindTollfreeNumbersRequest request = FindTollfreeNumbersRequest.create()
            .limit(1L)
            .pattern("86*")
            .build();
        List<Number> numbers = client.numbersApi().findNumbersTollfree(request);
        assertThat(jsonConverter.serialize(new ListHolder<>(numbers)), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "pattern=86*", "limit=1");
    }

}
