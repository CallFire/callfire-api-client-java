package com.callfire.api.client.api.integration.numbers;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.numbers.NumberLeasesApi;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeasesRequest;
import org.junit.Test;

import static com.callfire.api.client.api.numbers.model.NumberConfig.ConfigType.IVR;
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
            .limit(2L)
            .build();
        Page<NumberLease> leases = callfireClient.getNumberLeasesApi().find(request);
        assertEquals(2, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = "19206596476";
        NumberLease lease = callfireClient.getNumberLeasesApi().get(number);
        assertNotNull(lease.getRegion());
        assertEquals(number, lease.getNumber());
        assertThat(lease.getRegion().getCity(), containsString("APPLETON"));

        System.out.println(lease);
    }

    @Test
    public void testUpdateNumberLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = "19206596476";
        NumberLeasesApi api = callfireClient.getNumberLeasesApi();
        NumberLease lease = api.get(number);
        assertNotNull(lease.getRegion());
        Boolean autoRenewSaved = lease.getAutoRenew();
        lease.setAutoRenew(!autoRenewSaved);
        lease.setNumber(number);

        api.update(lease);
        lease = api.get(number, "autoRenew,tollFree");
        assertNull(lease.getNumber());
        assertNotEquals(autoRenewSaved, lease.getAutoRenew());

        System.out.println(lease);
    }

    @Test
    public void testFindNumberLeaseConfigs() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeaseConfigsRequest request = FindNumberLeaseConfigsRequest.create()
            .limit(2L)
            .build();
        Page<NumberConfig> configs = callfireClient.getNumberLeasesApi().findConfigs(request);
        assertEquals(2, configs.getItems().size());

        System.out.println(configs);
    }

    @Test
    public void testGetNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        NumberConfig config = callfireClient.getNumberLeasesApi().getConfig("19206596476");
        assertEquals(IVR, config.getConfigType());
        assertNotNull(config.getIvrInboundConfig());

        System.out.println(config);
    }

    @Test
    public void testUpdateNumberLeaseConfig() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String number = "19206596476";
        NumberLeasesApi api = callfireClient.getNumberLeasesApi();
        NumberConfig config = api.getConfig(number);
        assertNull(config.getCallTrackingConfig());
        assertEquals(IVR, config.getConfigType());

        api.updateConfig(config);
        config = api.getConfig(number, "ivrInboundConfig,configType");
        assertNotNull(config.getIvrInboundConfig());
        assertNull(config.getNumber());
        assertEquals(IVR, config.getConfigType());
        assertNotNull(config.getIvrInboundConfig().getDialplanXml());

        System.out.println(config);
    }

}
