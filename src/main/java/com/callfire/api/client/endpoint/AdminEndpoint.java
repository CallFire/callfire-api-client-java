package com.callfire.api.client.endpoint;

import io.swagger.client.*;
import io.swagger.client.model.*;
import io.swagger.client.model.CallerIdVerificationRequest;
import io.swagger.client.model.StatsDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminEndpoint {

  
  /**
   * Get callerIds.
   * Get callerIds associated with account.
   * @return List<String>
   */
  public List<String> getCallerIds () throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
    // create path and map variables
    String path = "/admin/callerids".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basicAuth" };
    
    

    

    
    
    TypeRef returnType = new TypeRef<List<String>>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * Send generated verification code to callerid number.
   * Send generated verification code to callerid number. After receiving\n\n verification code on phone call POST\n&#39;/callerids/{callerid}/verification-code&#39; to\n\n verify number.
   * @param callerid 
   * @return String
   */
  public String sendVerificationCodeToCallerId (String callerid) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'callerid' is set
     if (callerid == null) {
        throw new ApiException(400, "Missing the required parameter 'callerid' when calling sendVerificationCodeToCallerId");
     }
     
    // create path and map variables
    String path = "/admin/callerids/{callerid}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "callerid" + "\\}", apiClient.escapeString(callerid.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basicAuth" };
    
    

    

    
    
    TypeRef returnType = new TypeRef<String>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * Verify callerId by providing calling number and verificationCode received on phone.
   * Verify callerId by providing calling number and verificationCode received on phone.
   * @param callerid 
   * @param verify 
   * @return Boolean
   */
  public Boolean verifyCallerId (String callerid, CallerIdVerificationRequest verify) throws ApiException {
    Object postBody = verify;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'callerid' is set
     if (callerid == null) {
        throw new ApiException(400, "Missing the required parameter 'callerid' when calling verifyCallerId");
     }
     
     // verify the required parameter 'verify' is set
     if (verify == null) {
        throw new ApiException(400, "Missing the required parameter 'verify' when calling verifyCallerId");
     }
     
    // create path and map variables
    String path = "/admin/callerids/{callerid}/verification-code".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "callerid" + "\\}", apiClient.escapeString(callerid.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basicAuth" };
    
    

    

    
    
    TypeRef returnType = new TypeRef<Boolean>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * 
   * 
   * @return StatsDto
   */
  public StatsDto getStats () throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
    // create path and map variables
    String path = "/admin/stats".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basicAuth" };
    
    

    

    
    
    TypeRef returnType = new TypeRef<StatsDto>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
  }
  
}
