package com.callfire.api.client;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Elementary integration tests
 */
public class SimpleIntegrationTest {

    @Test
    @Ignore
    public void queryCallfireThroughProxyWithBasicAuth() throws Exception {
        CallfireClient.getClientConfig().put(ClientConstants.PROXY_ADDRESS_PROPERTY, "localhost:3128");
        CallfireClient.getClientConfig().put(ClientConstants.PROXY_CREDENTIALS_PROPERTY, "proxyuser:proxypass");
        CallfireClient client = new CallfireClient("", "");
        System.out.println("Account" + client.meApi().getAccount());
    }
}
