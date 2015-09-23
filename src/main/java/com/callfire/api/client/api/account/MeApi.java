package com.callfire.api.client.api.account;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import com.callfire.api.client.api.account.model.request.CallerIdVerificationRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;

import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.BOOLEAN_TYPE;
import static com.callfire.api.client.ClientConstants.Type.LIST_OF_STRINGS_TYPE;
import static com.callfire.api.client.ClientConstants.Type.STRING_TYPE;

/**
 * Represents rest endpoint /me
 */
public class MeApi {
    private static final String ME_ACCOUNT_PATH = "/me/account";
    private static final String ME_BILLING_PATH = "/me/billing/plan-usage";
    private static final String ME_CALLERIDS_PATH = "/me/callerids";
    private static final String ME_CALLERIDS_CODE_PATH = "/me/callerids/{}";
    private static final String ME_CALLERIDS_VERIFY_PATH = "/me/callerids/{}/verification-code";
    private static final TypeReference<Account> ACCOUNT_TYPE = new TypeReference<Account>() {
    };
    private static final TypeReference<BillingPlanUsage> BILLING_PLAN_USAGE_TYPE = new TypeReference<BillingPlanUsage>() {
    };

    private RestApiClient client;

    public MeApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Method queries account info like id, email, name, etc...
     * <p/>
     * GET /me/account
     *
     * @return user's account
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Account getAccount() {
        return client.get(ME_ACCOUNT_PATH, ACCOUNT_TYPE);
    }

    /**
     * Get Plan usage statistics
     * <p/>
     * GET /me/billing/plan-usage
     *
     * @return BillingPlanUsage object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public BillingPlanUsage getBillingPlanUsage() {
        return client.get(ME_BILLING_PATH, BILLING_PLAN_USAGE_TYPE);
    }

    /**
     * Get callerIds associated with account.
     * <p/>
     * GET /me/callerids
     *
     * @return List<String> list with callerIds
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<String> getCallerIds() {
        return client.get(ME_CALLERIDS_PATH, LIST_OF_STRINGS_TYPE);
    }

    /**
     * Send generated verification code to callerid number.
     * After receiving verification code on phone call POST /callerids/{callerid}/verification-code
     * to verify number.
     * <p/>
     * POST /me/callerids/{callerid}
     *
     * @param callerid callerid number
     * @return String
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public String sendVerificationCodeToCallerId(String callerid) {
        Validate.notBlank(callerid, "callerid cannot be blank");
        return client.post(ME_CALLERIDS_CODE_PATH.replaceFirst(PLACEHOLDER, callerid), STRING_TYPE);
    }

    /**
     * Verify callerId by providing calling number and verificationCode received on phone.
     * <p/>
     * POST /me/callerids/{callerid}/verification-code
     *
     * @param request  request object
     * @return true or false depending on whether verification was successful or not.
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Boolean verifyCallerId(CallerIdVerificationRequest request) {
        Validate.notBlank(request.getCallerId(), "callerid cannot be blank");
        String path = ME_CALLERIDS_VERIFY_PATH.replaceFirst(PLACEHOLDER, request.getCallerId());
        return client.post(path, BOOLEAN_TYPE, request);
    }
}
