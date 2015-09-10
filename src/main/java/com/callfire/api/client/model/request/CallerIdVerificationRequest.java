package com.callfire.api.client.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;

@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T14:43:00.245Z")
public class CallerIdVerificationRequest   {
  
  private String verificationCode = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("verificationCode")
  public String getVerificationCode() {
    return verificationCode;
  }
  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CallerIdVerificationRequest {\n");
    
    sb.append("  verificationCode: ").append(verificationCode).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
