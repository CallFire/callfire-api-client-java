package com.callfire.api.client.model.request;

/**
 * Request object for searching numbers in tollfree catalog. It incapsulates
 * different query pairs
 */
public class FindNumbersTollfreeRequest extends FindRequest {

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
    public static class Builder extends AbstractBuilder<Builder, FindNumbersTollfreeRequest> {
        private Builder() {
            super(new FindNumbersTollfreeRequest());
        }
    }
}
