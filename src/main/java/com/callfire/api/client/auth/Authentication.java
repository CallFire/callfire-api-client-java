package com.callfire.api.client.auth;

import org.apache.http.client.methods.HttpUriRequest;

import com.callfire.api.client.CallfireClientException;

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
