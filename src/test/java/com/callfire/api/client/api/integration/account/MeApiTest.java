package com.callfire.api.client.api.integration.account;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.ApiCredentials;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import com.callfire.api.client.api.account.model.CallerId;
import com.callfire.api.client.api.account.model.request.CallerIdVerificationRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Ignore;
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
public class MeApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testGetAccount() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        Account account = callfireClient.meApi().getAccount();

        assertEquals(account.getId(), Long.valueOf(1L));
        assertThat(account.getEmail(), stringContainsInOrder(Arrays.asList("@", ".")));
        assertTrue(StringUtils.isNoneBlank(account.getName()));
        assertTrue(StringUtils.isNoneBlank(account.getFirstName()));
        assertTrue(StringUtils.isNoneBlank(account.getLastName()));
        assertTrue(account.getPermissions().contains("ACCOUNT_HOLDER"));
    }

    @Test
    public void testGetBillingPlanUsage() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        BillingPlanUsage billingPlanUsage = callfireClient.meApi().getBillingPlanUsage();

        assertNotNull(billingPlanUsage.getIntervalStart());
        assertNotNull(billingPlanUsage.getIntervalEnd());
        assertNotNull(billingPlanUsage.getRemainingPayAsYouGoCredits());
        assertNotNull(billingPlanUsage.getRemainingPlanCredits());
        assertNotNull(billingPlanUsage.getTotalRemainingCredits());
    }

    @Test
    public void testGetCallerIds() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        List<CallerId> callerIds = callfireClient.meApi().getCallerIds();
        System.out.println(callerIds);
        assertThat(callerIds, hasItem(Matchers.<CallerId>hasProperty("phoneNumber", equalTo("12132212289"))));
    }

    @Test
    @Ignore("requires environment setup")
    public void testSendVerificationCode() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        callfireClient.meApi().sendVerificationCode("12132212289");

        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", is(400))));
        ex.expect(hasProperty("apiErrorMessage", hasProperty("message", containsString("that is already verified"))));
        callfireClient.meApi().sendVerificationCode(getCallerId());
    }

    @Test
    public void testVerifyCallerId() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        CallerIdVerificationRequest request = CallerIdVerificationRequest.create()
            .callerId(getCallerId())
            .verificationCode("1234")
            .build();
        Boolean verified = callfireClient.meApi().verifyCallerId(request);
        //   assertTrue(verified);
        request = CallerIdVerificationRequest.create()
            .callerId(getCallerId().replace("84", "85"))
            .verificationCode("1234")
            .build();
        verified = callfireClient.meApi().verifyCallerId(request);
        //  assertFalse(verified);
    }

    @Test
    @Ignore("unsupported yet")
    public void testCreateGetDeleteApiCredentials() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        ApiCredentials credentials = new ApiCredentials();
        credentials.setName("test1");
        //        ApiCredentials created = callfireClient.meApi().createApiCredentials(credentials);
        ApiCredentials created = null;
        System.out.println(created);

        assertEquals(credentials.getName(), created.getName());
        assertTrue(created.getEnabled());
        assertNotNull(created.getId());

        //        created = callfireClient.meApi().getApiCredentials(created.getId(), "name,enabled");
        assertEquals(credentials.getName(), created.getName());
        assertTrue(created.getEnabled());
        assertNull(created.getId());
        assertNull(created.getPassword());

        //        callfireClient.meApi().deleteApiCredentials(created.getId());

        expect404NotFoundCallfireApiException(ex);
        //        callfireClient.meApi().getApiCredentials(created.getId(), "name,enabled");
    }

    @Test
    @Ignore("unsupported yet")
    public void testFindApiCredentials() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        CommonFindRequest request = CommonFindRequest.create()
            .limit(2L)
            .build();
        //        Page<ApiCredentials> credentials = callfireClient.meApi().findApiCredentials(request);
        Page<ApiCredentials> credentials = null;
        assertEquals(2, credentials.getItems().size());
        assertNotNull(credentials.getItems().get(0).getId());
        assertTrue(credentials.getItems().get(0).getEnabled());
    }
}
