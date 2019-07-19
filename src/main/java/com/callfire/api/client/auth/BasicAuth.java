package com.callfire.api.client.auth;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;

import com.callfire.api.client.CallfireClientException;

/**
 * Implementation of basic auth scheme
 */
public class BasicAuth implements Authentication {
    private UsernamePasswordCredentials credentials;

    /**
     * Constructs basic auth from provided credentials
     *
     * @param username api username
     * @param password api password
     */
    public BasicAuth(String username, String password) {
        credentials = new UsernamePasswordCredentials(username, password);
    }

    @Override
    public HttpUriRequest apply(HttpUriRequest request) {
        try {
            request.addHeader(new BasicScheme().authenticate(credentials, request, null));
            return request;
        } catch (AuthenticationException e) {
            throw new CallfireClientException(e);
        }
    }
}
