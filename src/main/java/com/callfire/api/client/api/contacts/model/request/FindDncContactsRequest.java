package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

/**
 * Request object for GET /contacts which incapsulates
 * different query pairs
 */
public class FindDncContactsRequest extends GetRequest {
    private String prefix;
    private Long dncListId;
    private String dncListName;
    private Boolean callDnc;
    private Boolean textDnc;

    private FindDncContactsRequest() {
    }

    /**
     * Get prefix (1-10 digits) of numbers
     *
     * @return prefix (1-10 digits) of numbers
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Get DNC list id
     *
     * @return DNC list id
     */
    public Long getDncListId() {
        return dncListId;
    }

    /**
     * Get DNC list name
     *
     * @return DNC list name
     */
    public String getDncListName() {
        return dncListName;
    }

    /**
     * Get filter by call property
     *
     * @return true if filter by call set
     */
    public Boolean getCallDnc() {
        return callDnc;
    }

    /**
     * Get filter by text property
     *
     * @return true if filter by text set
     */
    public Boolean getTextDnc() {
        return textDnc;
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
     * Builder class for find method
     */
    public static class Builder extends GetRequestBuilder<Builder, FindDncContactsRequest> {
        private Builder() {
            super(new FindDncContactsRequest());
        }

        /**
         * Set prefix (1-10 digits) of numbers
         *
         * @param prefix prefix (1-10 digits) of numbers
         * @return builder self-reference
         */
        public Builder prefix(String prefix) {
            request.prefix = prefix;
            return this;
        }

        /**
         * Set DNC list id to filter lists
         *
         * @param dncListId DNC list id to filter lists
         * @return builder self-reference
         */
        public Builder dncListId(Long dncListId) {
            request.dncListId = dncListId;
            return this;
        }

        /**
         * Set DNC list name to filter lists
         *
         * @param dncListName DNC list name to filter lists
         * @return builder self-reference
         */
        public Builder dncListName(String dncListName) {
            request.dncListName = dncListName;
            return this;
        }

        /**
         * Set filter by call
         *
         * @param callDnc filter by call
         * @return builder self-reference
         */
        public Builder callDnc(Boolean callDnc) {
            request.callDnc = callDnc;
            return this;
        }

        /**
         * Set filter by text
         *
         * @param textDnc filter by text
         * @return builder self-reference
         */
        public Builder textDnc(Boolean textDnc) {
            request.textDnc = textDnc;
            return this;
        }
    }
}
