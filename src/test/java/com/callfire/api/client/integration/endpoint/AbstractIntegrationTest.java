package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for all integration tests
 */
public class AbstractIntegrationTest {
    private String username;
    private String password;
    private String callerId;
    private List<Long> campaignIds = new ArrayList<>();

    public AbstractIntegrationTest() {
        username = System.getProperty("testApiUsername");
        password = System.getProperty("testApiPassword");
        callerId = System.getProperty("testCallerId");

        String campaignIdsPropery = System.getProperty("testCampaignIds");
        if (campaignIdsPropery != null) {
            List<String> list = Arrays.asList(campaignIdsPropery.split(","));
            campaignIds.addAll(list.stream().mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));
        }

        // TODO remove credentials
        this.username = "dcbcb63e595e";
        this.password = "d15efb7ed79dcd02";
        this.callerId = "12132212384";
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

    public List<Long> getCampaignIds() {
        return campaignIds;
    }
}
