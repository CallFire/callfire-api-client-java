package com.callfire.api.client.integration.numbers;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.numbers.NumberLeasesApi;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeasesRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.junit.Test;

import static com.callfire.api.client.api.numbers.model.NumberConfig.ConfigType.TRACKING;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * integration tests for /numbers/leases api endpoint
 */
public class NumberLeasesApiTest extends AbstractIntegrationTest {
    @Test
    public void testFindNumberLeases() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeasesRequest request = FindNumberLeasesRequest.create()
                .limit(2L)
                .build();
        Page<NumberLease> leases = callfireClient.numberLeasesApi().find(request);
        assertEquals(2, leases.getItems().size());
        assertTrue(leases.getItems().get(0).getLabels().size() > 0);

        System.out.println(leases);
    }

    @Test
    public void testGetNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = getDid3();
        NumberLease lease = callfireClient.numberLeasesApi().get(number);
        assertNotNull(lease.getRegion());
        assertEquals(number, lease.getNumber());
        assertEquals(2, lease.getLabels().size());
        assertThat(lease.getRegion().getCity(), containsString("LOS ANGELES"));

        System.out.println(lease);
    }

    @Test
    public void testUpdateNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = getDid3();
        NumberLeasesApi api = callfireClient.numberLeasesApi();
        NumberLease lease = api.get(number);
        assertNotNull(lease.getRegion());
        lease.setNumber(number);
        lease.setTextFeatureStatus(NumberLease.FeatureStatus.DISABLED);
        lease.setCallFeatureStatus(NumberLease.FeatureStatus.DISABLED);

        api.update(lease);
        lease = api.get(number, "number,callFeatureStatus,textFeatureStatus");
        assertNotNull(lease.getNumber());
        assertEquals(NumberLease.FeatureStatus.DISABLED, lease.getCallFeatureStatus());
        assertEquals(NumberLease.FeatureStatus.DISABLED, lease.getTextFeatureStatus());

        lease.setTextFeatureStatus(NumberLease.FeatureStatus.ENABLED);
        lease.setCallFeatureStatus(NumberLease.FeatureStatus.ENABLED);
        api.update(lease);

        System.out.println(lease);
    }

    @Test
    public void testFindNumberLeaseConfigs() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeaseConfigsRequest request = FindNumberLeaseConfigsRequest.create()
                .limit(2L)
                .build();
        Page<NumberConfig> configs = callfireClient.numberLeasesApi().findConfigs(request);
        assertEquals(2, configs.getItems().size());

        System.out.println(configs);
    }

    @Test
    public void testGetNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberConfig config = callfireClient.numberLeasesApi().getConfig(getDid3());
        assertEquals(TRACKING, config.getConfigType());
        assertNotNull(config.getCallTrackingConfig());

        System.out.println(config);
    }

    @Test
    public void testUpdateNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = getDid3();
        NumberLeasesApi api = callfireClient.numberLeasesApi();
        NumberConfig config = api.getConfig(number);
        assertNull(config.getIvrInboundConfig());
        assertEquals(TRACKING, config.getConfigType());

        api.updateConfig(config);
        config = api.getConfig(number, "callTrackingConfig,configType");
        assertNotNull(config.getCallTrackingConfig());
        assertNull(config.getNumber());
        assertEquals(TRACKING, config.getConfigType());

        System.out.println(config);
    }

}
