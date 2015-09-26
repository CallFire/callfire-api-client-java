package com.callfire.api.client.api.common.model.request;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Common get request with id
 */
public class GetByIdRequest extends FindRequest {
    @QueryParamIgnore
    private Long id;

    private GetByIdRequest() {
    }

    public Long getId() {
        return id;
    }

    public static Builder create() {
        return new Builder();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .toString();
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
