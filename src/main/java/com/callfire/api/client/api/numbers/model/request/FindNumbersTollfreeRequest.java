package com.callfire.api.client.api.numbers.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

/**
 * Request object for searching numbers in tollfree catalog. It incapsulates
 * different query pairs
 */
public class FindNumbersTollfreeRequest extends GetRequest {

    private FindNumbersTollfreeRequest() {
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
    public static class Builder extends GetRequestBuilder<Builder, FindNumbersTollfreeRequest> {
        private Builder() {
            super(new FindNumbersTollfreeRequest());
        }
    }
}
