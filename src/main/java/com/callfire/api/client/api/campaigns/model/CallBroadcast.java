package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * CallBroadcast that represents an IVR or Voice broadcast. If 'dialplanXml' field
 * is set then broadcast is IVR, otherwise Voice.
 */
public class CallBroadcast extends RetriableBroadcast {
    private List<Recipient> recipients;
    /**
     * IVR xml document describing dialplan. If dialplanXml != null then this is IVR broadcast
     */
    private String dialplanXml;
    /**
     * Voice broadcast sounds
     */
    private CallBroadcastSounds sounds;
    private AnsweringMachineConfig answeringMachineConfig;
    private Integer maxActiveTransfers;

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public String getDialplanXml() {
        return dialplanXml;
    }

    public void setDialplanXml(String dialplanXml) {
        this.dialplanXml = dialplanXml;
    }

    public CallBroadcastSounds getSounds() {
        return sounds;
    }

    public void setSounds(CallBroadcastSounds sounds) {
        this.sounds = sounds;
    }

    public AnsweringMachineConfig getAnsweringMachineConfig() {
        return answeringMachineConfig;
    }

    public void setAnsweringMachineConfig(AnsweringMachineConfig answeringMachineConfig) {
        this.answeringMachineConfig = answeringMachineConfig;
    }

    public Integer getMaxActiveTransfers() {
        return maxActiveTransfers;
    }

    public void setMaxActiveTransfers(Integer maxActiveTransfers) {
        this.maxActiveTransfers = maxActiveTransfers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("recipients", recipients)
            .append("dialplanXml", dialplanXml)
            .append("sounds", sounds)
            .append("answeringMachineConfig", answeringMachineConfig)
            .append("maxActiveTransfers", maxActiveTransfers)
            .toString();
    }
}
