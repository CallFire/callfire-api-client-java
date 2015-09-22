package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.NumberOrder;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * integration tests for /orders api endpoint
 */
public class OrdersApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testOrderKeywords() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        KeywordPurchaseRequest request = KeywordPurchaseRequest.create()
            .keywords(Arrays.asList("TEST1", "TEST2"))
            .build();
        ResourceId resourceId = callfireClient.getOrdersApi().orderKeywords(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.getOrdersApi().getKeywordOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.getOrdersApi().getKeywordOrder(1L);
    }

    @Test
    public void testOrderNumbers() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberPurchaseRequest request = NumberPurchaseRequest.create()
            .numbers(Arrays.asList("12132005646"))
            .build();
        ResourceId resourceId = callfireClient.getOrdersApi().orderNumbers(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.getOrdersApi().getNumberOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.getOrdersApi().getNumberOrder(1L);
    }
}
