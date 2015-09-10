package com.callfire.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T14:43:00.245Z")
public class AgentGroupDto   {
  
  private Long id = null;
  private String name = null;
  private List<AgentDto> agents = new ArrayList<AgentDto>();
  private List<Long> campaignIds = new ArrayList<Long>();


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
  @JsonProperty("agents")
  public List<AgentDto> getAgents() {
    return agents;
  }
  public void setAgents(List<AgentDto> agents) {
    this.agents = agents;
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AgentGroupDto {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  agents: ").append(agents).append("\n");
    sb.append("  campaignIds: ").append(campaignIds).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
