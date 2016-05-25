package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.callfire.api.client.api.common.model.request.QueryParamIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SendTextsRequest extends CallfireModel {
    @QueryParamIgnore
    protected List<TextRecipient> recipients;

    protected Long campaignId;
    protected String defaultMessage;
    protected String fields;

    public static Builder create() {
        return new Builder();
    }

    public List<TextRecipient> getRecipients() {
        return recipients;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getFields() {
        return fields;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("campaignId", campaignId)
            .append("defaultMessage", defaultMessage)
            .append("fields", fields)
            .toString();
    }

    @SuppressWarnings("unchecked")
    public static class Builder extends AbstractBuilder<SendTextsRequest> {

        private Builder() {
            super(new SendTextsRequest());
        }

        public Builder recipients(List<TextRecipient> recipients) {
            request.recipients = recipients;
            return this;
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public Builder defaultMessage(String defaultMessage) {
            request.defaultMessage = defaultMessage;
            return this;
        }

        public Builder fields(String fields) {
            request.fields = fields;
            return this;
        }
    }
}
