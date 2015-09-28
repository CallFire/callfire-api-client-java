package com.callfire.api.client.api.numbers.model.request;

/**
 * Request object for searching number lease configs which incapsulates
 * different query pairs
 */
public class FindNumberLeaseConfigsRequest extends FindByRegionDataRequest {
    private String labelName;

    private FindNumberLeaseConfigsRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    public String getLabelName() {
        return labelName;
    }

    /**
     * Builder class
     */
    public static class Builder extends RegionDataBuilder<Builder, FindNumberLeaseConfigsRequest> {

        private Builder() {
            super(new FindNumberLeaseConfigsRequest());
        }

        public Builder labelName(String labelName) {
            request.labelName = labelName;
            return this;
        }
    }
}
