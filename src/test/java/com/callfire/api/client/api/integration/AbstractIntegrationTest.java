package com.callfire.api.client.api.integration;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.hamcrest.Matchers;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.BASE_PATH_PROPERTY;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasProperty;

/**
 * Base class for all integration tests
 */
public class AbstractIntegrationTest {
    private String username;
    private String password;
    private String callerId;
    private String did1;
    private String basePath;
    private List<Long> campaignIds = new ArrayList<>();

    public AbstractIntegrationTest() {
        username = System.getProperty("testApiUsername");
        password = System.getProperty("testApiPassword");
        callerId = System.getProperty("testCallerId");
        did1 = System.getProperty("testDid1");
        basePath = System.getProperty("basePath");
        if (StringUtils.isEmpty(basePath)) {
            basePath = "http://localhost/api/v2";
        }
        CallfireClient.getClientConfig().put(BASE_PATH_PROPERTY, basePath);

        String campaignIdsPropery = System.getProperty("testCampaignIds");
        if (campaignIdsPropery != null) {
            for (String s : asList(campaignIdsPropery.split(","))) {
                campaignIds.add(Long.valueOf(s));
            }
        }

        this.username = "";
        this.password = "";
        this.callerId = "12132212384";
        this.did1 = "12132046770";
        this.campaignIds.add(6476244003L); // ccc
        this.campaignIds.add(6476216003L); // ccc
        this.campaignIds.add(7073356003L); // ivr
        this.campaignIds.add(6952176003L); // voice

        Validate.notEmpty(username, "Username cannot be empty, set it with -DtestApiUsername option");
        Validate.notEmpty(password, "Password cannot be empty, set it with -DtestApiPassword option");

        Validate.notEmpty(callerId, "CallerID is not set, set it with -DtestCallerId option");
    }

    public CallfireClient getCallfireClient() {
        return new CallfireClient(getUsername(), getPassword());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCallerId() {
        return callerId;
    }

    public String getDid1() {
        return did1;
    }

    public List<Long> getCampaignIds() {
        return campaignIds;
    }

    protected void expect404NotFoundCallfireApiException(ExpectedException ex) {
        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", Matchers.is(404))));
    }

    protected List<Recipient> makeRecipients() {
        Recipient recipient1 = new Recipient();
        recipient1.setPhoneNumber("12131234567");
        Recipient recipient2 = new Recipient();
        recipient2.setPhoneNumber("12131234568");

        return asList(recipient1, recipient2);
    }

    protected List<TextRecipient> makeTextRecipients() {
        TextRecipient recipient1 = new TextRecipient();
        recipient1.setPhoneNumber("12131234567");
        recipient1.setMessage("msg1");
        TextRecipient recipient2 = new TextRecipient();
        recipient2.setPhoneNumber("12131234568");
        recipient2.setMessage("msg1");

        return asList(recipient1, recipient2);
    }

    protected Long getLiveSoundId() {
        return 1L;
    }

    protected Long getVoiceBroadcastId() {
        return 1L;
    }

    protected Long getTextBroadcastId() {
        return 3L;
    }

    protected Long getIvrBroadcastId() {
        return 7L;
    }
}
