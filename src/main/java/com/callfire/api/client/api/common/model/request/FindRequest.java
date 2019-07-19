package com.callfire.api.client.api.common.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * Contains common fields for finder endpoints
 */
@Getter
public abstract class FindRequest extends CallfireModel {

    /**
     * Max number of records per page to return. If items.size() less than limit assume no more items.
     * If value not set, default is 100
     *
     * @return limit number
     */
    protected Long limit;

    /**
     * Offset to start of page, if value not set, default is 0
     *
     * @return offset
     */
    protected Long offset;

    /**
     * Limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    protected String fields;

    /**
     * Abstract builder for find requests
     *
     * @param <B> type of builder
     */
    @SuppressWarnings("unchecked")
    public static abstract class FindRequestBuilder<B extends FindRequestBuilder, R extends FindRequest> extends AbstractBuilder<R> {

        protected FindRequestBuilder(R request) {
            super(request);
        }

        /**
         * Set max number of items returned.
         *
         * @param limit max number of items
         * @return builder object
         */
        public B limit(Long limit) {
            request.limit = limit;
            return (B) this;
        }

        /**
         * Offset from start of paging source
         *
         * @param offset offset value
         * @return builder object
         */
        public B offset(Long offset) {
            request.offset = offset;
            return (B) this;
        }

        /**
         * Set limit fields returned. Example fields=id,items(name,agents(id))
         *
         * @param fields fields to return
         * @return builder object
         */
        public B fields(String fields) {
            request.fields = fields;
            return (B) this;
        }
    }
}
