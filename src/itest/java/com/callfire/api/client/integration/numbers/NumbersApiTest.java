package com.callfire.api.client.integration.numbers;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.numbers.model.Number;
import com.callfire.api.client.api.numbers.model.Region;
import com.callfire.api.client.api.numbers.model.request.FindNumberRegionsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumbersLocalRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * integration tests for /numbers api endpoint
 */
public class NumbersApiTest extends AbstractIntegrationTest {

    @Test
    public void testFindTollfreeNumbers() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        CommonFindRequest request = CommonFindRequest.create()
            .limit(2L)
            .build();
        List<Number> numbers = callfireClient.numbersApi().findNumbersTollfree(request);
        assertEquals(2, numbers.size());

        System.out.println(numbers);
    }

    @Test
    public void testFindNumbersLocal() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumbersLocalRequest request = FindNumbersLocalRequest.create()
            .limit(2L)
            .state("LA")
            .build();
        List<Number> numbers = callfireClient.numbersApi().findNumbersLocal(request);
        assertEquals(2, numbers.size());
        assertThat(numbers.get(0).getNationalFormat(), startsWith("(225)"));

        System.out.println(numbers);
    }

    @Test
    public void testFindNumberRegions() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberRegionsRequest request = FindNumberRegionsRequest.create()
            .limit(2L)
            .state("IL")
            .zipcode("60640")
            .build();
        Page<Region> regions = callfireClient.numbersApi().findNumberRegions(request);
        assertEquals(2, regions.getItems().size());
        assertThat(regions.getItems().get(0).getCity(), containsString("CHICAGO"));
        assertTrue(regions.getItems().get(0).getPrefix().contains("177"));
        System.out.println(regions);
    }
}
