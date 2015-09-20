package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.NumberOrder;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.model.request.NumberPurchaseRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * integration tests for /orders api endpoint
 */
public class OrdersEndpointTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testOrderKeywords() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        KeywordPurchaseRequest request = KeywordPurchaseRequest.create()
            .setKeywords(Arrays.asList("TEST1", "TEST2"))
            .build();
        ResourceId resourceId = callfireClient.getOrdersEndpoint().orderKeywords(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.getOrdersEndpoint().getKeywordOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.getOrdersEndpoint().getKeywordOrder(1L);
    }

    @Test
    public void testOrderNumbers() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberPurchaseRequest request = NumberPurchaseRequest.create()
            .setNumbers(Arrays.asList("12132005646"))
            .build();
        ResourceId resourceId = callfireClient.getOrdersEndpoint().orderNumbers(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.getOrdersEndpoint().getNumberOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.getOrdersEndpoint().getNumberOrder(1L);
    }
}
