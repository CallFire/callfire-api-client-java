package com.callfire.api.client.api.account;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.account.model.NumberOrder;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OrdersApiTest extends AbstractApiTest {

    @Test
    public void testOrderKeywords() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + "/account/ordersApi/request/orderKeywords.json");
        String responseJson = getJsonPayload(BASE_PATH + "/account/ordersApi/response/orderKeywords.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        KeywordPurchaseRequest request = KeywordPurchaseRequest.create()
            .keywords(asList("KW1", "KW2"))
            .build();

        ResourceId id = client.ordersApi().orderKeywords(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testOrderNumbers() throws Exception {
        String requestJson = getJsonPayload(BASE_PATH + "/account/ordersApi/request/orderNumbers.json");
        String responseJson = getJsonPayload(BASE_PATH + "/account/ordersApi/response/orderNumbers.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        NumberPurchaseRequest request = NumberPurchaseRequest.create()
            .localCount(2)
            .zipcode("90401")
            .build();

        ResourceId id = client.ordersApi().orderNumbers(request);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(jsonConverter.serialize(request), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testGetOrder() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/ordersApi/response/getOrder.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

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
