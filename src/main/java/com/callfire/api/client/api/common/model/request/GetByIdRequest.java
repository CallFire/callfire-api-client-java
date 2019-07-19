package com.callfire.api.client.api.common.model.request;

import static lombok.AccessLevel.PRIVATE;

import org.apache.commons.lang3.Validate;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Common get request with id
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class GetByIdRequest extends FindRequest {
    @QueryParamIgnore private Long id;

    public static Builder create() {
        return new Builder();
    }

    /**
     * Request builder
     */
    public static class Builder extends FindRequestBuilder<Builder, GetByIdRequest> {

        private Builder() {
            super(new GetByIdRequest());
        }

        public Builder id(Long id) {
            request.id = id;
            return this;
        }

        @Override
        public GetByIdRequest build() {
            Validate.notNull(request.id, "request.id cannot be null");
            return super.build();
        }
    }
}
