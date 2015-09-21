package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.endpoint.NumberLeasesEndpoint;
import com.callfire.api.client.model.CallTrackingConfig;
import com.callfire.api.client.model.NumberConfig;
import com.callfire.api.client.model.NumberLease;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.model.request.FindNumberLeasesRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static com.callfire.api.client.model.NumberConfig.ConfigType.IVR;
import static com.callfire.api.client.model.NumberConfig.ConfigType.TRACKING;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * integration tests for /numbers/leases api endpoint
 */
public class NumberLeasesEndpointTest extends AbstractIntegrationTest {
    @Test
    public void testFindNumberLeases() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeasesRequest request = FindNumberLeasesRequest.create()
            .setLimit(2L)
            .build();
        Page<NumberLease> leases = callfireClient.getNumbersEndpoint().getNumberLeasesEndpoint()
            .findNumberLeases(request);
        assertEquals(2, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberLease lease = callfireClient.getNumbersEndpoint().getNumberLeasesEndpoint().getNumberLease("14352003506");
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
        NumberLeasesEndpoint leasesEndpoint = callfireClient.getNumbersEndpoint().getNumberLeasesEndpoint();
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
        Page<NumberConfig> configs = callfireClient.getNumbersEndpoint().getNumberLeasesEndpoint()
            .findNumberLeaseConfigs(request);
        assertEquals(2, configs.getItems().size());

        System.out.println(configs);
    }

    @Test
    public void testGetNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberConfig config = callfireClient.getNumbersEndpoint().getNumberLeasesEndpoint()
            .getNumberLeaseConfig("14352003506");
        assertEquals(IVR, config.getConfigType());
        assertNotNull(config.getIvrInboundConfig());

        System.out.println(config);
    }

    @Test
    public void testUpdateNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = "14352003506";
        NumberLeasesEndpoint leasesEndpoint = callfireClient.getNumbersEndpoint().getNumberLeasesEndpoint();
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
