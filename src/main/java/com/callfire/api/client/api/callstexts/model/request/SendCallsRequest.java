package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.campaigns.model.Voice;
import com.callfire.api.client.api.common.model.request.QueryParamIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class SendCallsRequest extends SendCallsTextsRequest {
    @QueryParamIgnore protected List<CallRecipient> recipients;

    protected String defaultLiveMessage;
    protected String defaultMachineMessage;
    protected Long defaultLiveMessageSoundId;
    protected Long defaultMachineMessageSoundId;
    protected Voice defaultVoice;

    public static Builder create() {
        return new Builder();
    }

    public static class Builder extends SendCallsTextsBuilder<Builder, SendCallsRequest> {

        private Builder() {
            super(new SendCallsRequest());
        }

        public Builder recipients(List<CallRecipient> recipients) {
            request.recipients = recipients;
            return this;
        }

        public Builder defaultLiveMessage(String defaultLiveMessage) {
            request.defaultLiveMessage = defaultLiveMessage;
            return this;
        }

        public Builder defaultMachineMessage(String defaultMachineMessage) {
            request.defaultMachineMessage = defaultMachineMessage;
            return this;
        }

        public Builder defaultLiveMessageSoundId(Long defaultLiveMessageSoundId) {
            request.defaultLiveMessageSoundId = defaultLiveMessageSoundId;
            return this;
        }

        public Builder defaultMachineMessageSoundId(Long defaultMachineMessageSoundId) {
            request.defaultMachineMessageSoundId = defaultMachineMessageSoundId;
            return this;
        }

        public Builder defaultVoice(Voice defaultVoice) {
            request.defaultVoice = defaultVoice;
            return this;
        }
    }
}
