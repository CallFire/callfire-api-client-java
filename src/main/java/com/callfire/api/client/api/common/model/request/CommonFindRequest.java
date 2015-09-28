package com.callfire.api.client.api.common.model.request;

/**
 * Common find request with limit, offset and fields properties
 */
public class CommonFindRequest extends FindRequest {

    private CommonFindRequest() {
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Request builder
     */
    public static class Builder extends FindRequestBuilder<Builder, CommonFindRequest> {

        private Builder() {
            super(new CommonFindRequest());
        }
    }
}
