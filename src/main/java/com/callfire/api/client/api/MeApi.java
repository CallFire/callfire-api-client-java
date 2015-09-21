package com.callfire.api.client.api;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Account;
import com.callfire.api.client.model.BillingPlanUsage;
import com.callfire.api.client.model.request.CallerIdVerificationRequest;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;

/**
 * Represents rest endpoint /me
 */
public class MeApi {
    private static final String ME_ACCOUNT_PATH = "/me/account";
    private static final String ME_BILLING_PATH = "/me/billing/plan-usage";
    private static final String ME_CALLERIDS_PATH = "/me/callerids";
    private static final String ME_CALLERIDS_CODE_PATH = "/me/callerids/{}";
    private static final String ME_CALLERIDS_VERIFY_PATH = "/me/callerids/{}/verification-code";

    private RestApiClient client;

    public MeApi(RestApiClient client) {
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

    /**
     * Get callerIds associated with account.
     * <p>
     * GET /me/callerids
     *
     * @return List<String> list with callerIds
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<String> getCallerIds() throws CallfireApiException, CallfireClientException {
        return client.get(ME_CALLERIDS_PATH, new TypeReference<List<String>>() {
        });
    }

    /**
     * Send generated verification code to callerid number.
     * After receiving verification code on phone call POST /callerids/{callerid}/verification-code
     * to verify number.
     * <p>
     * POST /me/callerids/{callerid}
     *
     * @param callerid callerid number
     * @return String
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public String sendVerificationCodeToCallerId(String callerid) throws CallfireApiException, CallfireClientException {
        String path = ME_CALLERIDS_CODE_PATH.replaceFirst(PLACEHOLDER, callerid);
        return client.post(path, new TypeReference<String>() {
        });
    }

    /**
     * Verify callerId by providing calling number and verificationCode received on phone.
     * <p>
     * POST /me/callerids/{callerid}/verification-code
     *
     * @param callerid callerid number
     * @param request  request object
     * @return true or false depending on whether verification was successful or not.
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Boolean verifyCallerId(String callerid, CallerIdVerificationRequest request)
        throws CallfireApiException, CallfireClientException {
        String path = ME_CALLERIDS_VERIFY_PATH.replaceFirst(PLACEHOLDER, callerid);
        return client.post(path, new TypeReference<Boolean>() {
        }, request);
    }
}
