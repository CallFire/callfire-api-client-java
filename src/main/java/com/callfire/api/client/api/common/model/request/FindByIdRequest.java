package com.callfire.api.client.api.common.model.request;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract find request with id
 */
public abstract class FindByIdRequest extends FindRequest {
    @QueryParamIgnore
    protected Long id;

    protected FindByIdRequest() {
    }

    public Long getId() {
        return id;
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
    @SuppressWarnings("unchecked")
    public static class FindByIdBuilder<B extends FindByIdBuilder, R extends FindByIdRequest>
        extends FindRequestBuilder<B, R> {

        protected FindByIdBuilder(R request) {
            super(request);
        }

        public B id(Long id) {
            request.id = id;
            return (B) this;
        }

        @Override
        public R build() {
            Validate.notNull(request.id, "request.id cannot be null");
            return super.build();
        }
    }
}
