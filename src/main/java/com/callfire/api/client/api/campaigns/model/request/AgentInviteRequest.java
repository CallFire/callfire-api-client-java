package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AgentInviteRequest extends CallfireModel {
    private List<Long> agentsIds;
    private List<String> agentEmails;
    private Boolean sendEmail;
    @JsonIgnore
    private Long campaignId;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public List<Long> getAgentsIds() {
        return agentsIds;
    }

    public void setAgentsIds(List<Long> agentsIds) {
        this.agentsIds = agentsIds;
    }

    public List<String> getAgentEmails() {
        return agentEmails;
    }

    public void setAgentEmails(List<String> agentEmails) {
        this.agentEmails = agentEmails;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("agentsIds", agentsIds)
            .append("agentEmails", agentEmails)
            .append("sendEmail", sendEmail)
            .append("campaignId", campaignId)
            .toString();
    }
}
