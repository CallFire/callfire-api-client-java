package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.Stats;
import com.callfire.api.client.model.request.CallerIdVerificationRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * integration tests for /me api endpoint
 */
public class AdminEndpointTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testGetCallerIds() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        List<String> callerIds = callfireClient.getAdminEndpoint().getCallerIds();
        assertThat(callerIds, contains("12132212384"));
    }

    @Test
    public void testSendVerificationCode() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        callfireClient.getAdminEndpoint().sendVerificationCodeToCallerId(getCallerId().replace("84", "85"));

        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", is(400))));
        ex.expect(hasProperty("apiErrorMessage", hasProperty("message", containsString("that is already verified"))));
        String callerIds = callfireClient.getAdminEndpoint().sendVerificationCodeToCallerId(getCallerId());

    }

    @Test
    public void testVerifyCallerId() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        CallerIdVerificationRequest request = new CallerIdVerificationRequest();
        request.setVerificationCode("1234");
        Boolean verified = callfireClient.getAdminEndpoint().verifyCallerId(getCallerId(), request);
        assertTrue(verified);
        verified = callfireClient.getAdminEndpoint().verifyCallerId(getCallerId().replace("84", "85"), request);
        assertFalse(verified);
    }

    @Test
    public void testGetStats() throws Exception {
        CallfireClient callfireClient = new CallfireClient(getUsername(), getPassword());
        Stats stats = callfireClient.getAdminEndpoint().getStats();
        assertTrue(StringUtils.isNoneBlank(stats.getName()));
    }
}
