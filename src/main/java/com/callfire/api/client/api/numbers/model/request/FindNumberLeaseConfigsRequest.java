package com.callfire.api.client.api.numbers.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for searching number lease configs which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindNumberLeaseConfigsRequest extends FindByRegionDataRequest {
    private String labelName;

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
