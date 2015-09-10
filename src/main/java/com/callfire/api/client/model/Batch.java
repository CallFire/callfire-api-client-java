package com.callfire.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T14:43:00.245Z")
public class BatchDto   {
  
  private Long id = null;
  private String name = null;
  public enum StatusEnum {
     NEW,  VALIDATING,  ERRORS,  SOURCE_ERROR,  ACTIVE, 
  };
  private StatusEnum status = null;
  private Long broadcastId = null;
  private Long created = null;
  private Integer size = null;
  private Integer remaining = null;
  private Boolean enabled = null;

  
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
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("broadcastId")
  public Long getBroadcastId() {
    return broadcastId;
  }
  public void setBroadcastId(Long broadcastId) {
    this.broadcastId = broadcastId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("created")
  public Long getCreated() {
    return created;
  }
  public void setCreated(Long created) {
    this.created = created;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }
  public void setSize(Integer size) {
    this.size = size;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("remaining")
  public Integer getRemaining() {
    return remaining;
  }
  public void setRemaining(Integer remaining) {
    this.remaining = remaining;
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchDto {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("  broadcastId: ").append(broadcastId).append("\n");
    sb.append("  created: ").append(created).append("\n");
    sb.append("  size: ").append(size).append("\n");
    sb.append("  remaining: ").append(remaining).append("\n");
    sb.append("  enabled: ").append(enabled).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
