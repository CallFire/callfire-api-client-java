package com.callfire.api.client.api.account;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.account.model.NumberOrder;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OrdersApiTest extends AbstractApiTest {

    @Spy
    private HttpClient mockHttpClient = client.getRestApiClient().getHttpClient();
    @Mock
    private CloseableHttpResponse mockHttpResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testOrderKeywords() throws Exception {
        String expectedJson = getJsonPayload("/responses/account/ordersApi/orderKeywords.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        KeywordPurchaseRequest request = KeywordPurchaseRequest.create()
            .keywords(asList("KW1", "KW2"))
            .build();

        ResourceId id = client.ordersApi().orderKeywords(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertEquals(jsonConverter.serialize(request), extractHttpEntity(arg));
    }

    @Test
    public void testOrderNumbers() throws Exception {
        String expectedJson = getJsonPayload("/responses/account/ordersApi/orderNumbers.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        NumberPurchaseRequest request = NumberPurchaseRequest.create()
            .city("City")
            .numbers(asList("1234567890", "1234567888"))
            .zipcode("1234")
            .state("LA")
            .build();

        ResourceId id = client.ordersApi().orderNumbers(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertEquals(jsonConverter.serialize(request), extractHttpEntity(arg));
    }

    @Test
    public void testGetOrder() throws Exception {
        String expectedJson = getJsonPayload("/responses/account/ordersApi/getOrder.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        NumberOrder numberOrder = client.ordersApi().getOrder(1L);
        HttpUriRequest arg = captor.getValue();
        assertThat(arg.getURI().toString(), not(containsString("fields")));

        numberOrder = client.ordersApi().getOrder(1L, FIELDS);
        assertThat(jsonConverter.serialize(numberOrder), equalToIgnoringWhiteSpace(expectedJson));

        arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));
    }

}
