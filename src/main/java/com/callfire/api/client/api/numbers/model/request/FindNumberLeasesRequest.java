package com.callfire.api.client.api.numbers.model.request;

/**
 * Request object for searching number leases which incapsulates
 * different query pairs
 */
public class FindNumberLeasesRequest extends FindByRegionDataRequest {
    private String labelName;

    private FindNumberLeasesRequest() {
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
    public static class Builder extends RegionDataBuilder<Builder, FindNumberLeasesRequest> {

        protected Builder() {
            super(new FindNumberLeasesRequest());
        }

        public Builder labelName(String labelName) {
            request.labelName = labelName;
            return this;
        }
    }
}
