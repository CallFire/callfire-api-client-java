package com.callfire.api.client.auth;

import com.callfire.api.client.CallfireClientException;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Provides authentication interface to client for different auth types
 */
public interface Authentication {
    /**
     * Apply authentication to http request
     *
     * @param request HTTP request
     * @return updated http request
     * @throws CallfireClientException in case error has occurred in client
     */
    HttpUriRequest apply(HttpUriRequest request);
}
