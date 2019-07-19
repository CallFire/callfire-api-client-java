package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class AddRecipientsRequest<T extends Recipient> extends CallfireModel {
    @JsonIgnore private Long campaignId;
    @JsonIgnore private List<T> recipients = new ArrayList<>();
    private Boolean strictValidation;
    private String fields;

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
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
