package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import org.apache.commons.lang3.Validate;
import org.hamcrest.Matchers;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasProperty;

/**
 * Base class for all integration tests
 */
public class AbstractIntegrationTest {
    private String username;
    private String password;
    private String callerId;
    private String did1;
    private List<Long> campaignIds = new ArrayList<>();

    public AbstractIntegrationTest() {
        username = System.getProperty("testApiUsername");
        password = System.getProperty("testApiPassword");
        callerId = System.getProperty("testCallerId");
        did1 = System.getProperty("testDid1");

        String campaignIdsPropery = System.getProperty("testCampaignIds");
        if (campaignIdsPropery != null) {
            List<String> list = Arrays.asList(campaignIdsPropery.split(","));
            campaignIds.addAll(list.stream().mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));
        }

        // TODO remove credentials
        this.username = "dcbcb63e595e";
        this.password = "d15efb7ed79dcd02";
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
}
