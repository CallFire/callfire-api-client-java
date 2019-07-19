package com.callfire.api.client.api.common.model.request;

import static lombok.AccessLevel.PROTECTED;

import org.apache.commons.lang3.Validate;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Abstract find request with id
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class FindByIdRequest extends FindRequest {
    @QueryParamIgnore protected Long id;

    /**
     * Request builder
     */
    @SuppressWarnings("unchecked")
    public static class FindByIdBuilder<B extends FindByIdBuilder, R extends FindByIdRequest> extends FindRequestBuilder<B, R> {

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
