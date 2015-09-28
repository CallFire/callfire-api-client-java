package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * integration tests for /campaigns/ivrs api endpoint
 */
public class IvrBroadcastsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient client = getCallfireClient();

        client.getCccsApi();
    }

    @Test
    public void testStartStopArchiveCampaign() throws Exception {

    }

    @Test
    public void testAgentsCrudOperations() throws Exception {
        CallfireClient client = getCallfireClient();
        client.getCccsApi().getAgentGroups(1L, "");

    }

    @Test
    public void testAgentGroupsCrudOperations() throws Exception {
        CallfireClient client = getCallfireClient();
        client.getCccsApi().getAgentGroups(1L, "");

    }
}
