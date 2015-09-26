package com.callfire.api.client.api.integration.keywords;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.keywords.KeywordLeasesApi;
import com.callfire.api.client.api.keywords.model.KeywordLease;
import org.junit.Test;

import static com.callfire.api.client.api.keywords.model.KeywordLease.Status.ACTIVE;
import static org.junit.Assert.*;

/**
 * integration tests for /keyword/leases api endpoint
 */
public class KeywordLeasesApiTest extends AbstractIntegrationTest {
    @Test
    public void testFindKeywordLeases() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        CommonFindRequest request = CommonFindRequest.create()
            .limit(1L)
            .build();
        Page<KeywordLease> leases = callfireClient.getKeywordLeasesApi().find(request);
        assertEquals(1, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetKeywordLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        KeywordLease lease = callfireClient.getKeywordLeasesApi().get("callfire");
        assertNotNull(lease.getKeyword());
        assertEquals(ACTIVE, lease.getStatus());

        System.out.println(lease);
    }

    @Test
    public void testUpdateKeywordLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String keyword = "callfire";
        KeywordLeasesApi api = callfireClient.getKeywordLeasesApi();
        KeywordLease lease = api.get(keyword);
        Boolean autoRenewSaved = lease.getAutoRenew();
        assertNotNull(lease.getKeyword());
        lease.setAutoRenew(!autoRenewSaved);

        api.update(lease);
        lease = api.get(keyword, "autoRenew");
        assertNull(lease.getKeyword());
        assertNotEquals(autoRenewSaved, lease.getAutoRenew());

        System.out.println(lease);
    }
}
