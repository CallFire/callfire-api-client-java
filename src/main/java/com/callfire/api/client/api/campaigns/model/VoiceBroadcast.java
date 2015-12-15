package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * VoiceBroadcast
 *
 * @deprecated use {@link CallBroadcast} instead
 */
@Deprecated
public class VoiceBroadcast extends RetriableBroadcast {
    private List<Recipient> recipients = new ArrayList<>();
    private String liveSoundText;
    private AnsweringMachineConfig answeringMachineConfig;
    private Voice liveSoundTextVoice;
    private Long liveSoundId;
    private String machineSoundText;

    private Voice machineSoundTextVoice;
    private Long machineSoundId;
    private String transferSoundText;

    private Voice transferSoundTextVoice;
    private Long transferSoundId;
    private String transferDigit;
    private String transferNumber;
    private String dncSoundText;

    private Voice dncSoundTextVoice;
    private Long dncSoundId;
    private String dncDigit;
    private Integer maxActiveTransfers;

    public String getLiveSoundText() {
        return liveSoundText;
    }

    public void setLiveSoundText(String liveSoundText) {
        this.liveSoundText = liveSoundText;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public AnsweringMachineConfig getAnsweringMachineConfig() {
        return answeringMachineConfig;
    }

    public void setAnsweringMachineConfig(AnsweringMachineConfig answeringMachineConfig) {
        this.answeringMachineConfig = answeringMachineConfig;
    }

    public Voice getLiveSoundTextVoice() {
        return liveSoundTextVoice;
    }

    public void setLiveSoundTextVoice(Voice liveSoundTextVoice) {
        this.liveSoundTextVoice = liveSoundTextVoice;
    }

    public Long getLiveSoundId() {
        return liveSoundId;
    }

    public void setLiveSoundId(Long liveSoundId) {
        this.liveSoundId = liveSoundId;
    }

    public String getMachineSoundText() {
        return machineSoundText;
    }

    public void setMachineSoundText(String machineSoundText) {
        this.machineSoundText = machineSoundText;
    }

    public Voice getMachineSoundTextVoice() {
        return machineSoundTextVoice;
    }

    public void setMachineSoundTextVoice(Voice machineSoundTextVoice) {
        this.machineSoundTextVoice = machineSoundTextVoice;
    }

    public Long getMachineSoundId() {
        return machineSoundId;
    }

    public void setMachineSoundId(Long machineSoundId) {
        this.machineSoundId = machineSoundId;
    }

    public String getTransferSoundText() {
        return transferSoundText;
    }

    public void setTransferSoundText(String transferSoundText) {
        this.transferSoundText = transferSoundText;
    }

    public Voice getTransferSoundTextVoice() {
        return transferSoundTextVoice;
    }

    public void setTransferSoundTextVoice(Voice transferSoundTextVoice) {
        this.transferSoundTextVoice = transferSoundTextVoice;
    }

    public Long getTransferSoundId() {
        return transferSoundId;
    }

    public void setTransferSoundId(Long transferSoundId) {
        this.transferSoundId = transferSoundId;
    }

    public String getTransferDigit() {
        return transferDigit;
    }

    public void setTransferDigit(String transferDigit) {
        this.transferDigit = transferDigit;
    }

    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        this.transferNumber = transferNumber;
    }

    public String getDncSoundText() {
        return dncSoundText;
    }

    public void setDncSoundText(String dncSoundText) {
        this.dncSoundText = dncSoundText;
    }

    public Voice getDncSoundTextVoice() {
        return dncSoundTextVoice;
    }

    public void setDncSoundTextVoice(Voice dncSoundTextVoice) {
        this.dncSoundTextVoice = dncSoundTextVoice;
    }

    public Long getDncSoundId() {
        return dncSoundId;
    }

    public void setDncSoundId(Long dncSoundId) {
        this.dncSoundId = dncSoundId;
    }

    public String getDncDigit() {
        return dncDigit;
    }

    public void setDncDigit(String dncDigit) {
        this.dncDigit = dncDigit;
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
            .append("liveSoundText", liveSoundText)
            .append("recipients", recipients)
            .append("answeringMachineConfig", answeringMachineConfig)
            .append("liveSoundTextVoice", liveSoundTextVoice)
            .append("liveSoundId", liveSoundId)
            .append("machineSoundText", machineSoundText)
            .append("machineSoundTextVoice", machineSoundTextVoice)
            .append("machineSoundId", machineSoundId)
            .append("transferSoundText", transferSoundText)
            .append("transferSoundTextVoice", transferSoundTextVoice)
            .append("transferSoundId", transferSoundId)
            .append("transferDigit", transferDigit)
            .append("transferNumber", transferNumber)
            .append("dncSoundText", dncSoundText)
            .append("dncSoundTextVoice", dncSoundTextVoice)
            .append("dncSoundId", dncSoundId)
            .append("dncDigit", dncDigit)
            .append("maxActiveTransfers", maxActiveTransfers)
            .toString();
    }
}
