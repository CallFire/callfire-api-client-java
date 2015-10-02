package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddBatchRequest extends CallfireModel {
    @JsonIgnore
    private Long campaignId;
    private String name;
    private Long contactListId;
    private Boolean scrubDuplicates;
    private List<Recipient> recipients = new ArrayList<>();

    private AddBatchRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public String getName() {
        return name;
    }

    public Long getContactListId() {
        return contactListId;
    }

    /**
     * Remove duplicate recipients in batch
     *
     * @return remove duplicate recipients in batch
     */
    public Boolean getScrubDuplicates() {
        return scrubDuplicates;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("name", name)
            .append("contactListId", contactListId)
            .append("scrubDuplicates", scrubDuplicates)
            .append("recipients", recipients)
            .toString();
    }

    public static class Builder extends AbstractBuilder<AddBatchRequest> {
        private Builder() {
            super(new AddBatchRequest());
        }

        /**
         * Set remove duplicate recipients in batch
         *
         * @param scrubDuplicates if true remove duplicate recipients in batch
         * @return builder self reference
         */
        public Builder scrubDuplicates(Boolean scrubDuplicates) {
            request.scrubDuplicates = scrubDuplicates;
            return this;
        }

        public Builder recipients(List<Recipient> recipients) {
            request.recipients = recipients;
            return this;
        }

        public Builder contactListId(Long contactListId) {
            request.contactListId = contactListId;
            return this;
        }

        public Builder name(String name) {
            request.name = name;
            return this;
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        @Override
        public AddBatchRequest build() {
            Validate.notNull(request.campaignId, "request.campaignId cannot be null");
            return super.build();
        }
    }
}
