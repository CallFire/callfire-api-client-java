package com.callfire.api.client.model.request;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Contains common fields for finder endpoints
 */
public abstract class FindRequest extends BaseModel {
    private Long limit;
    private Long offset;
    private String fields;

    /**
     * Get max number of records per page to return. If items.size() < limit assume no more items.
     *
     * @return limit number
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * Get offset to start of page
     *
     * @return offset
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Get limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    public String getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("limit", limit)
            .append("offset", offset)
            .append("fields", fields)
            .toString();
    }

    /**
     * Abstract builder for find requests
     *
     * @param <T> type of builder
     */
    public static abstract class FindRequestBuilder<T extends FindRequestBuilder> {

        /**
         * Set max number of items returned.
         *
         * @param limit max number of items
         * @return builder object
         */
        public T setLimit(Long limit) {
            getRequest().limit = limit;
            return getChild();
        }

        /**
         * Offset from start of paging source
         *
         * @param offset offset value
         * @return builder object
         */
        public T setOffset(Long offset) {
            getRequest().offset = offset;
            return getChild();
        }

        /**
         * Set limit fields returned. Example fields=id,items(name,agents(id))
         *
         * @param fields fields to return
         * @return builder object
         */
        public T setFields(String fields) {
            getRequest().fields = fields;
            return getChild();
        }

        /**
         * Build request
         *
         * @return find request pojo
         */
        public abstract FindRequest build();

        protected abstract T getChild();

        protected abstract FindRequest getRequest();
    }
}
