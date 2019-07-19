package com.callfire.api.client;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.callfire.api.client.api.account.model.Account;

import lombok.extern.slf4j.Slf4j;

/**
 * Elementary integration tests
 */
@Slf4j
public class SimpleIntegrationTest {

    @Test
    @Ignore("temporary ignored")
    public void queryCallfireThroughProxyWithBasicAuth() {
        CallfireClient.getClientConfig().put(ClientConstants.PROXY_ADDRESS_PROPERTY, "localhost:3128");
        CallfireClient.getClientConfig().put(ClientConstants.PROXY_CREDENTIALS_PROPERTY, "proxyuser:proxypass");
        CallfireClient client = new CallfireClient("", "");

        Account account = client.meApi().getAccount();
        assertThat(account, notNullValue());
        log.info("Account: {}", account);
    }
}
