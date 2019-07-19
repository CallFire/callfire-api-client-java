package com.callfire.api.client.api.numbers.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Request object for searching numbers which incapsulates
 * different query pairs
 */
@NoArgsConstructor(access = PRIVATE)
public class FindNumbersLocalRequest extends FindByRegionDataRequest {

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
