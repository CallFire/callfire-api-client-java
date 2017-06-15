package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddRecipientsRequest<T extends Recipient> extends CallfireModel {
    @JsonIgnore
    private Long campaignId;
    @JsonIgnore
    private List<T> recipients = new ArrayList<>();
    private Boolean strictValidation;
    private String fields;


    private AddRecipientsRequest() {
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

    public List<T> getRecipients() {
        return recipients;
    }

    public Boolean getStrictValidation() {
        return strictValidation;
    }

    public String getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("strictValidation", strictValidation)
            .append("recipients", recipients)
            .append("fields", fields)
            .toString();
    }

    public static class Builder<T extends Recipient> extends AbstractBuilder<AddRecipientsRequest<T>> {
        private Builder() {
            super(new AddRecipientsRequest<T>());
        }

        public Builder<T> recipients(List<T> recipients) {
            request.recipients = recipients;
            return this;
        }

        public Builder<T> fields(String fields) {
            request.fields = fields;
            return this;
        }

        public Builder<T> campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public Builder<T> strictValidation(Boolean strictValidation) {
            request.strictValidation = strictValidation;
            return this;
        }

        @Override
        public AddRecipientsRequest build() {
            Validate.notNull(request.campaignId, "request.campaignId cannot be null");
            Validate.notNull(request.recipients, "request.recipients cannot be null");
            return super.build();
        }
    }
}
