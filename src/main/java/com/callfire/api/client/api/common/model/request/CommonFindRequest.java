package com.callfire.api.client.api.common.model.request;

import static lombok.AccessLevel.PROTECTED;

import lombok.NoArgsConstructor;

/**
 * Common find request with limit, offset and fields properties
 */
@NoArgsConstructor(access = PROTECTED)
public class CommonFindRequest extends FindRequest {

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
