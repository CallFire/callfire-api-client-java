package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AgentInviteRequest extends CallfireModel {
    private List<Long> agentsIds;
    private List<String> agentEmails;
    private Boolean sendEmail;
    @JsonIgnore
    private Long campaignId;

    private AgentInviteRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public List<Long> getAgentsIds() {
        return agentsIds;
    }

    public List<String> getAgentEmails() {
        return agentEmails;
    }

    public Boolean getSendEmail() {
        return sendEmail;
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

    public static class Builder extends AbstractBuilder<AgentInviteRequest> {
        private Builder() {
            super(new AgentInviteRequest());
        }

        public Builder sendEmail(Boolean sendEmail) {
            request.sendEmail = sendEmail;
            return this;
        }

        public Builder agentEmails(List<String> agentEmails) {
            request.agentEmails = agentEmails;
            return this;
        }

        public Builder agentsIds(List<Long> agentsIds) {
            request.agentsIds = agentsIds;
            return this;
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }
    }
}
