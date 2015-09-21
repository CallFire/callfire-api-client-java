package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.endpoint.KeywordLeasesEndpoint;
import com.callfire.api.client.model.KeywordLease;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.request.FindKeywordLeasesRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static com.callfire.api.client.model.KeywordLease.Status.ACTIVE;
import static org.junit.Assert.*;

/**
 * integration tests for /keyword/leases api endpoint
 */
public class KeywordLeasesEndpointTest extends AbstractIntegrationTest {
    @Test
    public void testFindKeywordLeases() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindKeywordLeasesRequest request = FindKeywordLeasesRequest.create()
            .setLimit(1L)
            .build();
        Page<KeywordLease> leases = callfireClient.getKeywordsEndpoint().getKeywordLeasesEndpoint()
            .findKeywordLeases(request);
        assertEquals(1, leases.getItems().size());

        System.out.println(leases);
    }

    @Test
    public void testGetKeywordLease() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        KeywordLease lease = callfireClient.getKeywordsEndpoint().getKeywordLeasesEndpoint()
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
        KeywordLeasesEndpoint leasesEndpoint = callfireClient.getKeywordsEndpoint().getKeywordLeasesEndpoint();
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
