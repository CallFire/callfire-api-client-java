package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Account;
import com.callfire.api.client.model.BillingPlanUsage;
import com.fasterxml.jackson.core.type.TypeReference;

import static com.callfire.api.client.ApiEndpoints.ME_ACCOUNT_PATH;
import static com.callfire.api.client.ApiEndpoints.ME_BILLING_PATH;

/**
 * Represents rest endpoint /me
 */
public class MeEndpoint {

    private RestApiClient client;

    public MeEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Method queries account info like id, email, name, etc...
     * <p>
     * GET /me/account
     *
     * @return user's account
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Account getAccount() throws CallfireApiException, CallfireClientException {
        return client.get(ME_ACCOUNT_PATH, new TypeReference<Account>() {
        });
    }

    /**
     * Get Plan usage statistics
     * <p>
     * GET /me/billing/plan-usage
     *
     * @return BillingPlanUsage object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public BillingPlanUsage getBillingPlanUsage() throws CallfireApiException, CallfireClientException {
        return client.get(ME_BILLING_PATH, new TypeReference<BillingPlanUsage>() {
        });
    }

}
