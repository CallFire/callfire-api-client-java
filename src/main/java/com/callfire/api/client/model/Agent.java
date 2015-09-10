package com.callfire.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T14:43:00.245Z")
public class AgentDto   {
  
  private Long id = null;
  private Boolean enabled = null;
  private String name = null;
  private String email = null;
  private Long lastLogin = null;
  private List<Long> campaignIds = new ArrayList<Long>();
  private List<Long> groupIds = new ArrayList<Long>();
  private Long activeSessionId = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("enabled")
  public Boolean getEnabled() {
    return enabled;
  }
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("lastLogin")
  public Long getLastLogin() {
    return lastLogin;
  }
  public void setLastLogin(Long lastLogin) {
    this.lastLogin = lastLogin;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("campaignIds")
  public List<Long> getCampaignIds() {
    return campaignIds;
  }
  public void setCampaignIds(List<Long> campaignIds) {
    this.campaignIds = campaignIds;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("groupIds")
  public List<Long> getGroupIds() {
    return groupIds;
  }
  public void setGroupIds(List<Long> groupIds) {
    this.groupIds = groupIds;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("activeSessionId")
  public Long getActiveSessionId() {
    return activeSessionId;
  }
  public void setActiveSessionId(Long activeSessionId) {
    this.activeSessionId = activeSessionId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AgentDto {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  enabled: ").append(enabled).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  lastLogin: ").append(lastLogin).append("\n");
    sb.append("  campaignIds: ").append(campaignIds).append("\n");
    sb.append("  groupIds: ").append(groupIds).append("\n");
    sb.append("  activeSessionId: ").append(activeSessionId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
