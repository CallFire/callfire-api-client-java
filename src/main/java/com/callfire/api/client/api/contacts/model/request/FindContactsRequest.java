package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

import java.util.List;

/**
 * Request object for GET /contacts which incapsulates
 * different query pairs
 */
public class FindContactsRequest extends GetRequest {
    private Long contactListId;
    private String propertyName;
    private String propertyValue;
    private List<String> number;
    private List<Long> id;

    protected FindContactsRequest() {
    }

    public Long getContactListId() {
        return contactListId;
    }

    /**
     * Get name of contact property to search by
     *
     * @return name of contact property to search by
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Get value of contact property to search by
     *
     * @return value of contact property to search by
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    public List<String> getNumber() {
        return number;
    }

    public List<Long> getId() {
        return id;
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
    public static class Builder extends AbstractBuilder<Builder, FindContactsRequest> {
        private Builder() {
            super(new FindContactsRequest());
        }

        public Builder setContactListId(Long contactListId) {
            request.contactListId = contactListId;
            return this;
        }

        /**
         * Set name of contact property to search by
         *
         * @param propertyName name of contact property to search by
         * @return builder self-reference
         */
        public Builder setPropertyName(String propertyName) {
            request.propertyName = propertyName;
            return this;
        }

        /**
         * Set value of contact property to search by
         *
         * @param propertyValue value of contact property to search by
         * @return builder self-reference
         */
        public Builder setPropertyValue(String propertyValue) {
            request.propertyValue = propertyValue;
            return this;
        }

        public Builder setNumber(List<String> number) {
            request.number = number;
            return this;
        }

        public Builder setId(List<Long> id) {
            request.id = id;
            return this;
        }
    }
}
