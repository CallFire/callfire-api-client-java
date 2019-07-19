package com.callfire.api.client.integration.account;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.account.model.NumberOrder;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;

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

        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", is(400))));
        ex.expect(hasProperty("apiErrorMessage", hasProperty("message", equalTo("no valid credit card on file"))));
        ResourceId resourceId = callfireClient.ordersApi().orderKeywords(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.ordersApi().getOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.ordersApi().getOrder(1L);
    }

    @Test
    public void testOrderNumbers() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberPurchaseRequest request = NumberPurchaseRequest.create()
            .numbers(asList(getDid1()))
            .build();

        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", is(400))));
        ex.expect(hasProperty("apiErrorMessage", hasProperty("message", equalTo("no valid credit card on file"))));
        ResourceId resourceId = callfireClient.ordersApi().orderNumbers(request);
        assertNotNull(resourceId.getId());

        NumberOrder order = callfireClient.ordersApi().getOrder(resourceId.getId());
        System.out.println(order);

        expect404NotFoundCallfireApiException(ex);
        callfireClient.ordersApi().getOrder(1L);
    }
}
