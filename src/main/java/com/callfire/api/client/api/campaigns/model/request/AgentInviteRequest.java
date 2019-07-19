package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class AgentInviteRequest extends CallfireModel {
    private List<Long> agentsIds;
    private List<String> agentEmails;
    private Boolean sendEmail;
    @JsonIgnore private Long campaignId;

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
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
