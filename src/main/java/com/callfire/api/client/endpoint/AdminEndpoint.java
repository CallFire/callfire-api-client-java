package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Stats;
import com.callfire.api.client.model.request.CallerIdVerificationRequest;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static com.callfire.api.client.ApiEndpoints.*;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;

/**
 * Represents rest endpoint /admin
 */
public class AdminEndpoint {
    private RestApiClient client;

    public AdminEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Get callerIds associated with account.
     * <p>
     * GET /admin/callerids
     *
     * @return List<String> list with callerIds
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<String> getCallerIds() throws CallfireApiException, CallfireClientException {
        return client.get(ADMIN_CALLERIDS_PATH, new TypeReference<List<String>>() {
        });
    }

    /**
     * Send generated verification code to callerid number.
     * After receiving verification code on phone call POST /callerids/{callerid}/verification-code
     * to verify number.
     * <p>
     * POST /admin/callerids/{callerid}
     *
     * @param callerid callerid number
     * @return String
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public String sendVerificationCodeToCallerId(String callerid) throws CallfireApiException, CallfireClientException {
        String path = ADMIN_CALLERIDS_CODE_PATH.replaceFirst(PLACEHOLDER, callerid);
        return client.post(path, new TypeReference<String>() {
        });
    }

    /**
     * Verify callerId by providing calling number and verificationCode received on phone.
     * <p>
     * POST /admin/callerids/{callerid}/verification-code
     *
     * @param callerid callerid number
     * @param request  request object
     * @return true or false depending on whether verification was successful or not.
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Boolean verifyCallerId(String callerid, CallerIdVerificationRequest request)
        throws CallfireApiException, CallfireClientException {
        String path = ADMIN_CALLERIDS_VERIFY_PATH.replaceFirst(PLACEHOLDER, callerid);
        return client.post(path, new TypeReference<Boolean>() {
        }, request);
    }

    /**
     * Get stats
     * <p>
     * GET /admin/stats
     *
     * @return Stats object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Stats getStats() throws CallfireApiException, CallfireClientException {
        return client.get(ADMIN_STATS_PATH, new TypeReference<Stats>() {
        });
    }
}
