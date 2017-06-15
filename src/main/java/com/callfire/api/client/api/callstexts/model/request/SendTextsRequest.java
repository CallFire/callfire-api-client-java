package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.request.QueryParamIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SendTextsRequest extends SendCallsTextsRequest {
    @QueryParamIgnore
    protected List<TextRecipient> recipients;

    protected String defaultMessage;

    public static Builder create() {
        return new Builder();
    }

    public List<TextRecipient> getRecipients() {
        return recipients;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("defaultMessage", defaultMessage)
            .toString();
    }

    @SuppressWarnings("unchecked")
    public static class Builder extends SendCallsTextsBuilder<Builder, SendTextsRequest> {

        private Builder() {
            super(new SendTextsRequest());
        }

        public Builder recipients(List<TextRecipient> recipients) {
            request.recipients = recipients;
            return this;
        }

        public Builder defaultMessage(String defaultMessage) {
            request.defaultMessage = defaultMessage;
            return this;
        }
    }
}
