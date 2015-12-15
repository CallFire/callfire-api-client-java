package com.callfire.api.client.api.numbers;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.numbers.model.CallTrackingConfig;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberConfig.ConfigType;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeasesRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NumberLeasesApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/numbers/numberLeasesApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findNumberLeases.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindNumberLeasesRequest request = FindNumberLeasesRequest.create()
            .limit(5L)
            .offset(0L)
            .state("LA")
            .labelName("label")
            .build();
        Page<NumberLease> leases = client.numberLeasesApi().find(request);
        assertThat(jsonConverter.serialize(leases), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "state=LA", "labelName=label");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getNumberLease.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        NumberLease lease = client.numberLeasesApi().get("12345678901", FIELDS);
        assertThat(jsonConverter.serialize(lease), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.numberLeasesApi().get("12345678901");
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullNumber() throws Exception {
        ex.expectMessage("number cannot be blank");
        ex.expect(NullPointerException.class);
        client.numberLeasesApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateNumberLease.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        NumberLease lease = new NumberLease();
        lease.setNumber("12345678901");
        lease.setAutoRenew(false);
        client.numberLeasesApi().update(lease);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/numbers/leases/12345678901"));
    }

    @Test
    public void testUpdateNullNumber() throws Exception {
        ex.expectMessage("number cannot be blank");
        ex.expect(NullPointerException.class);
        client.numberLeasesApi().update(new NumberLease());
    }

    @Test
    public void testFindConfigs() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findNumberLeaseConfigs.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindNumberLeaseConfigsRequest request = FindNumberLeaseConfigsRequest.create()
            .limit(5L)
            .offset(0L)
            .state("LA")
            .labelName("label")
            .build();
        Page<NumberConfig> configs = client.numberLeasesApi().findConfigs(request);
        assertThat(jsonConverter.serialize(configs), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "state=LA", "labelName=label");
    }

    @Test
    public void testGetConfig() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getNumberLeaseConfig.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        NumberConfig config = client.numberLeasesApi().getConfig("12345678901", FIELDS);
        assertThat(jsonConverter.serialize(config), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.numberLeasesApi().getConfig("12345678901");
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetConfigNullNumber() throws Exception {
        ex.expectMessage("number cannot be blank");
        ex.expect(NullPointerException.class);
        client.numberLeasesApi().getConfig(null);

        ex.expectMessage("number cannot be blank");
        ex.expect(NullPointerException.class);
        client.numberLeasesApi().getConfig(null, FIELDS);
    }

    @Test
    public void testUpdateConfig() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateNumberLeaseConfig.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        NumberConfig config = new NumberConfig();
        config.setNumber("12345678901");
        config.setConfigType(ConfigType.TRACKING);
        CallTrackingConfig callTrackingConfig = new CallTrackingConfig();
        callTrackingConfig.setScreen(false);
        callTrackingConfig.setRecorded(true);
        callTrackingConfig.setTransferNumbers(asList("12135551122", "12135551189"));
        config.setCallTrackingConfig(callTrackingConfig);
        client.numberLeasesApi().updateConfig(config);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/numbers/leases/configs/12345678901"));
    }

    @Test
    public void testUpdateConfigNullNumber() throws Exception {
        ex.expectMessage("number cannot be blank");
        ex.expect(NullPointerException.class);
        client.numberLeasesApi().updateConfig(new NumberConfig());
    }
}
