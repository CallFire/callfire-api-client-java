package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.request.QueryParamIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class SendTextsRequest extends SendCallsTextsRequest {
    @QueryParamIgnore protected List<TextRecipient> recipients;
    protected String defaultMessage;

    public static Builder create() {
        return new Builder();
    }

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
