package com.callfire.api.client.model.request;

/**
 * Request object for searching numbers which incapsulates
 * different query pairs
 */
public class FindNumbersLocalRequest extends FindByRegionDataRequest {

    private FindNumbersLocalRequest() {
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
    public static class Builder extends RegionDataBuilder<Builder, FindNumbersLocalRequest> {

        private Builder() {
            super(new FindNumbersLocalRequest());
        }
    }
}
