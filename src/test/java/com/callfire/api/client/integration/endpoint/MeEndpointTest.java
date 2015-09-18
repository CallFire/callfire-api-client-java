package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.Account;
import com.callfire.api.client.model.BillingPlanUsage;
import com.callfire.api.client.model.request.CallerIdVerificationRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * integration tests for /me api endpoint
 */
public class MeEndpointTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

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

    @Test
    public void testGetCallerIds() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        List<String> callerIds = callfireClient.getMeEndpoint().getCallerIds();
        assertThat(callerIds, contains("12132212384"));
    }

    @Test
    public void testSendVerificationCode() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        callfireClient.getMeEndpoint().sendVerificationCodeToCallerId(getCallerId().replace("84", "85"));

        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", is(400))));
        ex.expect(hasProperty("apiErrorMessage", hasProperty("message", containsString("that is already verified"))));
        String callerIds = callfireClient.getMeEndpoint().sendVerificationCodeToCallerId(getCallerId());

    }

    @Test
    public void testVerifyCallerId() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        CallerIdVerificationRequest request = new CallerIdVerificationRequest();
        request.setVerificationCode("1234");
        Boolean verified = callfireClient.getMeEndpoint().verifyCallerId(getCallerId(), request);
        assertTrue(verified);
        verified = callfireClient.getMeEndpoint().verifyCallerId(getCallerId().replace("84", "85"), request);
        assertFalse(verified);
    }
}
