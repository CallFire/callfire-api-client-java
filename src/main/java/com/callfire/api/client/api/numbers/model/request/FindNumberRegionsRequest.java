package com.callfire.api.client.api.numbers.model.request;

/**
 * Request object for searching number regions which incapsulates
 * different query pairs
 */
public class FindNumberRegionsRequest extends FindByRegionDataRequest {

    private FindNumberRegionsRequest() {
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
    public static class Builder extends RegionDataBuilder<Builder, FindNumberRegionsRequest> {

        private Builder() {
            super(new FindNumberRegionsRequest());
        }
    }
}
