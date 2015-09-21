package com.callfire.api.client.integration.endpoint.numbers;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.numbers.NumberLeasesApi;
import com.callfire.api.client.api.numbers.model.CallTrackingConfig;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeasesRequest;
import com.callfire.api.client.integration.endpoint.AbstractIntegrationTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static com.callfire.api.client.api.numbers.model.NumberConfig.ConfigType.IVR;
import static com.callfire.api.client.api.numbers.model.NumberConfig.ConfigType.TRACKING;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * integration tests for /numbers/leases api endpoint
 */
public class NumberLeasesApiTest extends AbstractIntegrationTest {
    @Test
    public void testFindNumberLeases() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeasesRequest request = FindNumberLeasesRequest.create()
            .setLimit(2L)
            .build();
        Page<NumberLease> leases = callfireClient.getNumbersApi().getNumberLeasesApi()
            .findNumberLeases(request);
        assertEquals(2, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberLease lease = callfireClient.getNumbersApi().getNumberLeasesApi().getNumberLease("14352003506");
        assertNotNull(lease.getRegion());
        assertTrue(lease.getAutoRenew());
        assertThat(lease.getRegion().getCity(), containsString("PARK CITY"));

        System.out.println(lease);
    }

    @Test
    public void testUpdateNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = "14352003506";
        Date newDate = DateUtils.addMonths(new Date(), 1);
        NumberLeasesApi leasesEndpoint = callfireClient.getNumbersApi().getNumberLeasesApi();
        NumberLease lease = leasesEndpoint.getNumberLease(number);
        assertNotNull(lease.getRegion());
        assertNotEquals(newDate, lease.getLeaseEnd());
        lease.setLeaseEnd(newDate);

        // TODO fix test
//        leasesEndpoint.updateNumberLease(number, lease);
//        lease = leasesEndpoint.getNumberLease(number, "leaseEnd");
//        assertNull(lease.getNumber());
//        assertEquals(newDate, lease.getLeaseEnd());

        System.out.println(lease);
    }

    @Test
    public void testFindNumberLeaseConfigs() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeaseConfigsRequest request = FindNumberLeaseConfigsRequest.create()
            .setLimit(2L)
            .build();
        Page<NumberConfig> configs = callfireClient.getNumbersApi().getNumberLeasesApi()
            .findNumberLeaseConfigs(request);
        assertEquals(2, configs.getItems().size());

        System.out.println(configs);
    }

    @Test
    public void testGetNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberConfig config = callfireClient.getNumbersApi().getNumberLeasesApi()
            .getNumberLeaseConfig("14352003506");
        assertEquals(IVR, config.getConfigType());
        assertNotNull(config.getIvrInboundConfig());

        System.out.println(config);
    }

    @Test
    public void testUpdateNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = "14352003506";
        NumberLeasesApi leasesEndpoint = callfireClient.getNumbersApi().getNumberLeasesApi();
        NumberConfig config = leasesEndpoint.getNumberLeaseConfig(number);
        assertNull(config.getCallTrackingConfig());
        assertEquals(IVR, config.getConfigType());
        config.setConfigType(TRACKING);
        CallTrackingConfig callTrackingConfig = new CallTrackingConfig();
        callTrackingConfig.setRecorded(true);
        config.setCallTrackingConfig(callTrackingConfig);

        // TODO fix tests
//        leasesEndpoint.updateNumberLeaseConfig(number, config);
//        config = leasesEndpoint.getNumberLeaseConfig(number, "callTrackingConfig");
//        assertNotNull(config.getCallTrackingConfig());
//        assertEquals(TRACKING, config.getConfigType());
//        assertTrue(config.getCallTrackingConfig().getRecorded());

        System.out.println(config);
    }

}
