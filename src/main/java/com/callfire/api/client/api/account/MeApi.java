package com.callfire.api.client.api.account;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.ApiCredentials;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import com.callfire.api.client.api.account.model.CallerId;
import com.callfire.api.client.api.account.model.request.CallerIdVerificationRequest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.*;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /me
 *
 * @since 1.0
 */
public class MeApi {
    private static final String ME_ACCOUNT_PATH = "/me/account";
    private static final String ME_BILLING_PATH = "/me/billing/plan-usage";
    private static final String ME_API_CREDS_PATH = "/me/api/credentials";
    private static final String ME_API_CREDS_ITEM_PATH = "/me/api/credentials/{}";
    private static final String ME_CALLERIDS_PATH = "/me/callerids";
    private static final String ME_CALLERIDS_CODE_PATH = "/me/callerids/{}";
    private static final String ME_CALLERIDS_VERIFY_PATH = "/me/callerids/{}/verification-code";
    private static final TypeReference<Account> ACCOUNT_TYPE = new TypeReference<Account>() {
    };
    private static final TypeReference<ApiCredentials> API_CREDS_TYPE = new TypeReference<ApiCredentials>() {
    };
    private static final TypeReference<Page<ApiCredentials>> API_CREDS_PAGE_TYPE = new TypeReference<Page<ApiCredentials>>() {
    };
    private static final TypeReference<BillingPlanUsage> BILLING_PLAN_USAGE_TYPE = new TypeReference<BillingPlanUsage>() {
    };
    private static final TypeReference<ListHolder<CallerId>> LIST_OF_CALLERID_TYPE = new TypeReference<ListHolder<CallerId>>() {
    };

    private RestApiClient client;

    public MeApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Method queries account info like id, email, name, etc...
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
     * GET /me/callerids
     *
     * @return list of callerId numbers
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<CallerId> getCallerIds() {
        return client.get(ME_CALLERIDS_PATH, LIST_OF_CALLERID_TYPE).getItems();
    }

    /**
     * Send generated verification code to callerid number.
     * After receiving verification code on phone call POST /callerids/{callerid}/verification-code
     * to verify number.
     * POST /me/callerids/{callerid}
     *
     * @param callerid callerid number
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void sendVerificationCode(String callerid) {
        Validate.notBlank(callerid, "callerid cannot be blank");
        client.post(ME_CALLERIDS_CODE_PATH.replaceFirst(PLACEHOLDER, callerid), VOID_TYPE);
    }

    /**
     * Verify callerId by providing calling number and verificationCode received on phone.
     * POST /me/callerids/{callerid}/verification-code
     *
     * @param request request object
     * @return true or false depending on whether verification was successful or not.
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Boolean verifyCallerId(CallerIdVerificationRequest request) {
        Validate.notBlank(request.getCallerId(), "callerid cannot be blank");
        String path = ME_CALLERIDS_VERIFY_PATH.replaceFirst(PLACEHOLDER, request.getCallerId());
        return client.post(path, BOOLEAN_TYPE, request);
    }

    /**
     * Creates API credentials. ApiCredentials.name property required
     * Performs POST /me/api/credentials request
     *
     * @param credentials API credentials to create
     * @return {@link ApiCredentials} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ApiCredentials createApiCredentials(ApiCredentials credentials) {
        return client.post(ME_API_CREDS_PATH, API_CREDS_TYPE, credentials);
    }

    /**
     * Find API credentials associated with current account
     * Performs GET /me/api/credentials request
     *
     * @param request request with properties to filter
     * @return {@link ApiCredentials} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<ApiCredentials> findApiCredentials(CommonFindRequest request) {
        return client.get(ME_API_CREDS_PATH, API_CREDS_PAGE_TYPE, request);
    }

    /**
     * Get API credentials by id
     * GET /me/api/credentials/{id}
     *
     * @param id id of credentials
     * @return {@link ApiCredentials}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ApiCredentials getApiCredentials(Long id) {
        return getApiCredentials(id, null);
    }

    /**
     * Get API credentials by id
     * GET /me/api/credentials/{id}
     *
     * @param id     id of credentials
     * @param fields limit fields returned. Example fields=id,name
     * @return {@link ApiCredentials}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ApiCredentials getApiCredentials(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(ME_API_CREDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), API_CREDS_TYPE, queryParams);
    }

    /**
     * Delete API credentials by id
     * DELETE /me/api/credentials/{id}
     *
     * @param id id of credentials
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void deleteApiCredentials(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(ME_API_CREDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

}
