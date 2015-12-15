package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.Voice;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CallRecipient extends Recipient {
    /** If dialplanXml != null then IVR Broadcast. */
    private String dialplanXml;
    private String liveMessage;
    private Long liveMessageSoundId;
    private String machineMessage;
    private Long machineMessageSoundId;
    private Voice voice;

    public String getDialplanXml() {
        return dialplanXml;
    }

    public void setDialplanXml(String dialplanXml) {
        this.dialplanXml = dialplanXml;
    }

    public String getLiveMessage() {
        return liveMessage;
    }

    public void setLiveMessage(String liveMessage) {
        this.liveMessage = liveMessage;
    }

    public Long getLiveMessageSoundId() {
        return liveMessageSoundId;
    }

    public void setLiveMessageSoundId(Long liveMessageSoundId) {
        this.liveMessageSoundId = liveMessageSoundId;
    }

    public String getMachineMessage() {
        return machineMessage;
    }

    public void setMachineMessage(String machineMessage) {
        this.machineMessage = machineMessage;
    }

    public Long getMachineMessageSoundId() {
        return machineMessageSoundId;
    }

    public void setMachineMessageSoundId(Long machineMessageSoundId) {
        this.machineMessageSoundId = machineMessageSoundId;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("dialplanXml", dialplanXml)
            .append("liveMessage", liveMessage)
            .append("liveMessageSoundId", liveMessageSoundId)
            .append("machineMessage", machineMessage)
            .append("machineMessageSoundId", machineMessageSoundId)
            .append("voice", voice)
            .toString();
    }
}
