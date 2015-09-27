package com.callfire.api.client.api.integration.account;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.account.model.NumberOrder;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Arrays.asList;
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
            .keywords(asList("TEST1", "TEST2"))
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
            .numbers(asList("12132212289"))
            .build();
        ResourceId resourceId = callfireClient.getOrdersApi().orderNumbers(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.getOrdersApi().getNumberOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.getOrdersApi().getNumberOrder(1L);
    }
}
