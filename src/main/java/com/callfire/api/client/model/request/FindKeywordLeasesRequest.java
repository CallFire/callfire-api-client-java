package com.callfire.api.client.model.request;

/**
 * Request object for searching keyword leases which incapsulates
 * different query pairs
 */
public class FindKeywordLeasesRequest extends FindRequest {

    protected FindKeywordLeasesRequest() {
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
    public static class Builder extends AbstractBuilder<Builder, FindKeywordLeasesRequest> {

        protected Builder() {
            super(new FindKeywordLeasesRequest());
        }
    }
}
