package com.callfire.api.client;

import org.apache.http.client.methods.RequestBuilder;

/**
 * Extend abstract filter to control outgoing http requests
 */
public abstract class RequestFilter implements Comparable<RequestFilter> {
    public static final int DEFAULT_ORDER = 10;

    public abstract void filter(RequestBuilder requestBuilder);

    protected int order() {
        return DEFAULT_ORDER;
    }

    @Override
    public int compareTo(RequestFilter o) {
        return order() > o.order() ? 1 : -1;
    }
}
