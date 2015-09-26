package com.callfire.api.client.api.common.model.request;

/**
 * Common get request with limit, offset and fields properties
 */
public class CommonGetRequest extends GetRequest {

    private CommonGetRequest() {
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Request builder
     */
    public static class Builder extends GetRequestBuilder<Builder, CommonGetRequest> {

        private Builder() {
            super(new CommonGetRequest());
        }
    }
}
