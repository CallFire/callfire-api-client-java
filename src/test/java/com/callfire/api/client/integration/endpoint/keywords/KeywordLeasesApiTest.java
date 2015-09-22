package com.callfire.api.client.integration.endpoint.keywords;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.keywords.KeywordLeasesApi;
import com.callfire.api.client.api.keywords.model.KeywordLease;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.keywords.model.request.FindKeywordLeasesRequest;
import com.callfire.api.client.integration.endpoint.AbstractIntegrationTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static com.callfire.api.client.api.keywords.model.KeywordLease.Status.ACTIVE;
import static org.junit.Assert.*;

/**
 * integration tests for /keyword/leases api endpoint
 */
public class KeywordLeasesApiTest extends AbstractIntegrationTest {
    @Test
    public void testFindKeywordLeases() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindKeywordLeasesRequest request = FindKeywordLeasesRequest.create()
            .limit(1L)
            .build();
        Page<KeywordLease> leases = callfireClient.getKeywordsApi().getKeywordLeasesApi()
            .findKeywordLeases(request);
        assertEquals(1, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetKeywordLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        KeywordLease lease = callfireClient.getKeywordsApi().getKeywordLeasesApi()
            .getKeywordLease("callfire");
        assertNotNull(lease.getLeaseBegin());
        assertTrue(lease.getAutoRenew());
        assertEquals(ACTIVE, lease.getStatus());

        System.out.println(lease);
    }

    @Test
    public void testUpdateKeywordLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        String keyword = "callfire";
        Date newDate = DateUtils.addMonths(new Date(), 1);
        KeywordLeasesApi leasesEndpoint = callfireClient.getKeywordsApi().getKeywordLeasesApi();
        KeywordLease lease = leasesEndpoint.getKeywordLease(keyword);
        assertNotNull(lease.getLeaseBegin());
        assertNotEquals(newDate, lease.getLeaseEnd());
        lease.setLeaseEnd(newDate);

        // TODO fix test
//        leasesEndpoint.updateKeywordLease(keyword, lease);
//        lease = leasesEndpoint.getKeywordLease(keyword, "leaseEnd");
//        assertNull(lease.getKeyword());
//        assertEquals(newDate, lease.getLeaseEnd());

        System.out.println(lease);
    }
}
