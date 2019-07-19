package com.callfire.api.client.integration;

import static com.callfire.api.client.ClientConstants.BASE_PATH_PROPERTY;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasProperty;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.hamcrest.Matchers;
import org.junit.rules.ExpectedException;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.TextRecipient;

/**
 * Base class for all integration tests
 */
public class AbstractIntegrationTest {
    private static String BASE_PATH = "https://api.callfire.com/v2";

    private String apiUserName;
    private String apiUserPassword;
    private String accountName;
    private String accountPassword;
    private String callerId;
    private String did1;
    private String did2;
    private String did3;

    public AbstractIntegrationTest() {
        System.out.println(BASE_PATH);

        apiUserName = System.getProperty("testApiUsername");
        apiUserPassword = System.getProperty("testApiPassword");
        accountName = System.getProperty("testAccountName");
        accountPassword = System.getProperty("testAccountPassword");
        callerId = System.getProperty("testCallerId");

        Validate.notEmpty(apiUserName, "Username cannot be empty, set it with -DtestApiUsername option");
        Validate.notEmpty(apiUserPassword, "Password cannot be empty, set it with -DtestApiPassword option");
        Validate.notEmpty(callerId, "CallerID is not set, set it with -DtestCallerId option");

        this.did1 = "12132212289";
        this.did2 = "14246525473";
        this.did3 = "12132041238";

        CallfireClient.getClientConfig().put(BASE_PATH_PROPERTY, BASE_PATH);
    }

    public CallfireClient getCallfireClient() {
        return new CallfireClient(getApiUserName(), getApiUserPassword());
    }

    public String getApiUserName() {
        return apiUserName;
    }

    public String getApiUserPassword() {
        return apiUserPassword;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public String getCallerId() {
        return callerId;
    }

    public String getDid1() {
        return did1;
    }

    public String getDid2() {
        return did2;
    }

    public String getDid3() {
        return did3;
    }

    protected void expect404NotFoundCallfireApiException(ExpectedException ex) {
        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", Matchers.is(404))));
    }

    protected List<Recipient> makeRecipients() {
        Recipient recipient1 = new Recipient();
        recipient1.setPhoneNumber(did2);
        Recipient recipient2 = new Recipient();
        recipient2.setPhoneNumber(did3);

        return asList(recipient1, recipient2);
    }

    protected List<TextRecipient> makeTextRecipients() {
        TextRecipient recipient1 = new TextRecipient();
        recipient1.setPhoneNumber(did2);
        recipient1.setMessage("msg1");
        TextRecipient recipient2 = new TextRecipient();
        recipient2.setPhoneNumber(did3);
        recipient2.setMessage("msg1");

        return asList(recipient1, recipient2);
    }

    protected Long getLiveSoundId() {
        return 1L;
    }

    protected Long getVoiceBroadcastId() {
        return 1L;
    }
}
