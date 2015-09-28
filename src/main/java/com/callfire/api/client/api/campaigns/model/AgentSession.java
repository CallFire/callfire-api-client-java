package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class AgentSession extends CallfireModel {
    private Long id;
    private Long agentId;
    private Long campaignId;
    private AgentState agentState;
    private Integer callCount;
    private Date start;
    private Date lastUpdate;

    public enum AgentState {
        DISCONNECTED, CONNECTED, AVAILABLE, TALKING_CONTACT, DIALING_ASSISTED_TRANSFER,
        TALKING_CONTACT_AND_TRANSFER, TALKING_TRANSFER, POSTCALL, PAUSE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }

    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("agentId", agentId)
            .append("campaignId", campaignId)
            .append("agentState", agentState)
            .append("callCount", callCount)
            .append("start", start)
            .append("lastUpdate", lastUpdate)
            .toString();
    }
}
