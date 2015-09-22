package com.callfire.api.client.api.keywords.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

/**
 * Request object for searching keyword leases which incapsulates
 * different query pairs
 */
public class FindKeywordLeasesRequest extends GetRequest {

    private FindKeywordLeasesRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class
     */
    public static class Builder extends GetRequestBuilder<Builder, FindKeywordLeasesRequest> {

        private Builder() {
            super(new FindKeywordLeasesRequest());
        }
    }
}
