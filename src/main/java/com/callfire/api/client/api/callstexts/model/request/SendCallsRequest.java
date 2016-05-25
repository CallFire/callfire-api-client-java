package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.campaigns.model.Voice;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.callfire.api.client.api.common.model.request.QueryParamIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SendCallsRequest extends CallfireModel {
    @QueryParamIgnore
    protected List<CallRecipient> recipients;

    protected Long campaignId;
    protected String defaultLiveMessage;
    protected String defaultMachineMessage;
    protected Long defaultLiveMessageSoundId;
    protected Long defaultMachineMessageSoundId;
    protected Voice defaultVoice;
    protected String fields;

    public static Builder create() {
        return new Builder();
    }

    public List<CallRecipient> getRecipients() {
        return recipients;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public String getDefaultLiveMessage() {
        return defaultLiveMessage;
    }

    public String getDefaultMachineMessage() {
        return defaultMachineMessage;
    }

    public Long getDefaultLiveMessageSoundId() {
        return defaultLiveMessageSoundId;
    }

    public Long getDefaultMachineMessageSoundId() {
        return defaultMachineMessageSoundId;
    }

    public Voice getDefaultVoice() {
        return defaultVoice;
    }

    public String getFields() {
        return fields;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("campaignId", campaignId)
            .append("defaultLiveMessage", defaultLiveMessage)
            .append("defaultMachineMessage", defaultMachineMessage)
            .append("defaultLiveMessageSoundId", defaultLiveMessageSoundId)
            .append("defaultMachineMessageSoundId", defaultMachineMessageSoundId)
            .append("defaultVoice", defaultVoice)
            .append("fields", fields)
            .toString();
    }

    @SuppressWarnings("unchecked")
    public static class Builder extends AbstractBuilder<SendCallsRequest> {

        private Builder() {
            super(new SendCallsRequest());
        }

        public Builder recipients(List<CallRecipient> recipients) {
            request.recipients = recipients;
            return this;
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
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

        public Builder fields(String fields) {
            request.fields = fields;
            return this;
        }
    }
}
