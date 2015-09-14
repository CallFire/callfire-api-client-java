package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.Account;
import com.callfire.api.client.model.BillingPlanUsage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.*;

/**
 * integration tests for /me api endpoint
 */
public class MeEndpointTest extends AbstractIntegrationTest {
    @Test
    public void testGetAccount() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        Account account = callfireClient.getMeEndpoint().getAccount();

        assertThat(account.getId(), greaterThan(140000003L));
        assertThat(account.getEmail(), stringContainsInOrder(Arrays.asList("@", ".")));
        assertTrue(StringUtils.isNoneBlank(account.getName()));
        assertTrue(StringUtils.isNoneBlank(account.getFirstName()));
        assertTrue(StringUtils.isNoneBlank(account.getLastName()));
        assertTrue(account.getPermissions().contains("ACCOUNT_HOLDER"));
    }

    @Test
    public void testGetBillingPlanUsage() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        BillingPlanUsage billingPlanUsage = callfireClient.getMeEndpoint().getBillingPlanUsage();

        assertNotNull(billingPlanUsage.getIntervalStart());
        assertNotNull(billingPlanUsage.getIntervalEnd());
        assertNotNull(billingPlanUsage.getRemainingPayAsYouGoCredits());
        assertNotNull(billingPlanUsage.getRemainingPlanCredits());
        assertNotNull(billingPlanUsage.getTotalRemainingCredits());
    }
}
