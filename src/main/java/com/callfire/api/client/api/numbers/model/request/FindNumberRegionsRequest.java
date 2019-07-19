package com.callfire.api.client.api.numbers.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Request object for searching number regions which incapsulates
 * different query pairs
 */
@NoArgsConstructor(access = PRIVATE)
public class FindNumberRegionsRequest extends FindByRegionDataRequest {

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
