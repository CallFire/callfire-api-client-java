package com.callfire.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T14:43:00.245Z")
public class PageDto   {
  
  private Long limit = null;
  private Long offset = null;
  private Long totalCount = null;
  private List<BatchDto> items = new ArrayList<BatchDto>();


  /**
   * Max number of items returned. If items.size() < limit assume no more items.
   **/
  @ApiModelProperty(value = "Max number of items returned. If items.size() < limit assume no more items.")
  @JsonProperty("limit")
  public Long getLimit() {
    return limit;
  }
  public void setLimit(Long limit) {
    this.limit = limit;
  }


  /**
   * Offset from start of paging source
   **/
  @ApiModelProperty(value = "Offset from start of paging source")
  @JsonProperty("offset")
  public Long getOffset() {
    return offset;
  }
  public void setOffset(Long offset) {
    this.offset = offset;
  }


  /**
   * Total count of results available. -1 if unknown.
   **/
  @ApiModelProperty(value = "Total count of results available. -1 if unknown.")
  @JsonProperty("totalCount")
  public Long getTotalCount() {
    return totalCount;
  }
  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }


  /**
   * Items
   **/
  @ApiModelProperty(value = "Items")
  @JsonProperty("items")
  public List<BatchDto> getItems() {
    return items;
  }
  public void setItems(List<BatchDto> items) {
    this.items = items;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageDto {\n");
    
    sb.append("  limit: ").append(limit).append("\n");
    sb.append("  offset: ").append(offset).append("\n");
    sb.append("  totalCount: ").append(totalCount).append("\n");
    sb.append("  items: ").append(items).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
