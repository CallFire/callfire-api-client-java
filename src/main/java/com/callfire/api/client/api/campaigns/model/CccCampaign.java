package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class CccCampaign extends Broadcast {
    private List<TransferNumber> transferNumbers;
    private Script script;
    private String smartDropSoundText;
    private Voice smartDropSoundTextVoice;
    private Long smartDropSoundId;
    private Boolean recorded;
    private Boolean allowAnyTransfer;
    private Integer multilineDialingRatio;
    private Boolean multilineDialingEnabled;
    private Integer scrubLevel;
    private List<Agent> agents;
    private List<AgentGroup> agentGroups;

    public List<TransferNumber> getTransferNumbers() {
        return transferNumbers;
    }

    public void setTransferNumbers(
        List<TransferNumber> transferNumbers) {
        this.transferNumbers = transferNumbers;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public String getSmartDropSoundText() {
        return smartDropSoundText;
    }

    public void setSmartDropSoundText(String smartDropSoundText) {
        this.smartDropSoundText = smartDropSoundText;
    }

    public Voice getSmartDropSoundTextVoice() {
        return smartDropSoundTextVoice;
    }

    public void setSmartDropSoundTextVoice(Voice smartDropSoundTextVoice) {
        this.smartDropSoundTextVoice = smartDropSoundTextVoice;
    }

    public Long getSmartDropSoundId() {
        return smartDropSoundId;
    }

    public void setSmartDropSoundId(Long smartDropSoundId) {
        this.smartDropSoundId = smartDropSoundId;
    }

    public Boolean getRecorded() {
        return recorded;
    }

    public void setRecorded(Boolean recorded) {
        this.recorded = recorded;
    }

    public Boolean getAllowAnyTransfer() {
        return allowAnyTransfer;
    }

    public void setAllowAnyTransfer(Boolean allowAnyTransfer) {
        this.allowAnyTransfer = allowAnyTransfer;
    }

    public Integer getMultilineDialingRatio() {
        return multilineDialingRatio;
    }

    public void setMultilineDialingRatio(Integer multilineDialingRatio) {
        this.multilineDialingRatio = multilineDialingRatio;
    }

    public Boolean getMultilineDialingEnabled() {
        return multilineDialingEnabled;
    }

    public void setMultilineDialingEnabled(Boolean multilineDialingEnabled) {
        this.multilineDialingEnabled = multilineDialingEnabled;
    }

    public Integer getScrubLevel() {
        return scrubLevel;
    }

    public void setScrubLevel(Integer scrubLevel) {
        this.scrubLevel = scrubLevel;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<AgentGroup> getAgentGroups() {
        return agentGroups;
    }

    public void setAgentGroups(List<AgentGroup> agentGroups) {
        this.agentGroups = agentGroups;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("transferNumbers", transferNumbers)
            .append("script", script)
            .append("smartDropSoundText", smartDropSoundText)
            .append("smartDropSoundTextVoice", smartDropSoundTextVoice)
            .append("smartDropSoundId", smartDropSoundId)
            .append("recorded", recorded)
            .append("allowAnyTransfer", allowAnyTransfer)
            .append("multilineDialingRatio", multilineDialingRatio)
            .append("multilineDialingEnabled", multilineDialingEnabled)
            .append("scrubLevel", scrubLevel)
            .append("agents", agents)
            .append("agentGroups", agentGroups)
            .toString();
    }
}
