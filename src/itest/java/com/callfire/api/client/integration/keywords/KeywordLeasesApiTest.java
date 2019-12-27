package com.callfire.api.client.integration.keywords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.keywords.KeywordLeasesApi;
import com.callfire.api.client.api.keywords.model.KeywordLease;
import com.callfire.api.client.api.keywords.model.LeaseStatus;
import com.callfire.api.client.integration.AbstractIntegrationTest;

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
        Page<KeywordLease> leases = callfireClient.keywordLeasesApi().find(request);
        assertEquals(1, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetKeywordLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        KeywordLease lease = callfireClient.keywordLeasesApi().get("TEST_KEYWORD");
        assertNotNull(lease.getKeyword());
        assertTrue(lease.getStatus().equals(LeaseStatus.ACTIVE) || lease.getStatus().equals(LeaseStatus.PENDING));

        System.out.println(lease);
    }

    @Test
    public void testUpdateKeywordLease() {
        CallfireClient callfireClient = getCallfireClient();

        String keyword = "TEST_KEYWORD";
        KeywordLeasesApi api = callfireClient.keywordLeasesApi();
        KeywordLease lease = api.get(keyword);
        assertNotNull(lease.getKeyword());
        lease.setAutoRenew(false);

        api.update(lease);
        lease = api.get(keyword, "keyword,status,autoRenew");
        assertNotNull(lease.getKeyword());
        assertEquals(false, lease.getAutoRenew());
        assertEquals(LeaseStatus.PENDING, lease.getStatus());

        api.update(lease);
        System.out.println(lease);
    }
}
