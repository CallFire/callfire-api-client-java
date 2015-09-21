package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Agent extends CallfireModel {
    private Long id;
    private Boolean enabled;
    private String name;
    private String email;
    private Long lastLogin;
    private Long activeSessionId;
    private List<Long> campaignIds = new ArrayList<Long>();
    private List<Long> groupIds = new ArrayList<Long>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getActiveSessionId() {
        return activeSessionId;
    }

    public void setActiveSessionId(Long activeSessionId) {
        this.activeSessionId = activeSessionId;
    }

    public List<Long> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<Long> campaignIds) {
        this.campaignIds = campaignIds;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("enabled", enabled)
            .append("name", name)
            .append("email", email)
            .append("lastLogin", lastLogin)
            .append("activeSessionId", activeSessionId)
            .append("campaignIds", campaignIds)
            .append("groupIds", groupIds)
            .toString();
    }
}
