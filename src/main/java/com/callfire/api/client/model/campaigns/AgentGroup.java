package com.callfire.api.client.model.campaigns;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class AgentGroup extends BaseModel {
    private Long id;
    private String name;
    private List<Agent> agents = new ArrayList<Agent>();
    private List<Long> campaignIds = new ArrayList<Long>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Long> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<Long> campaignIds) {
        this.campaignIds = campaignIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("name", name)
            .append("agents", agents)
            .append("campaignIds", campaignIds)
            .toString();
    }
}
