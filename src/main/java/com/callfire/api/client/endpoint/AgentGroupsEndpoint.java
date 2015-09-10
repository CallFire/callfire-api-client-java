package com.callfire.api.client.endpoint;

import io.swagger.client.*;
import io.swagger.client.model.*;
import io.swagger.client.model.AgentGroupDto;
import io.swagger.client.model.PageDto;
import io.swagger.client.model.ResourceIdDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T14:43:00.245Z")
public class AgentgroupsApi {
  private ApiClient apiClient;

  public AgentgroupsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public AgentgroupsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Query agent groups
   * Query for agent groups using optional filters \n (Note: agentId and agentEmail are mutually exclusive, please only provide one)
   * @param fields Limit fields returned. Example fields=id,items(name,agents(id))
   * @param limit Max number of records per page to return
   * @param offset Offset to start of page
   * @param campaignId Id of campaign
   * @param name Agent group name
   * @param agentId Id of agent (agentId and agentEmail are mutually exclusive, please only provide one)
   * @param agentEmail Email of agent (agentId and agentEmail are mutually exclusive, please only provide one)
   * @return PageDto
   */
  public PageDto findAgentGroups (String fields, Long limit, Long offset, Long campaignId, String name, Long agentId, String agentEmail) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
    // create path and map variables
    String path = "/agent-groups".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    
    queryParams.addAll(apiClient.parameterToPairs("", "fields", fields));
    
    queryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
    
    queryParams.addAll(apiClient.parameterToPairs("", "offset", offset));
    
    queryParams.addAll(apiClient.parameterToPairs("", "campaignId", campaignId));
    
    queryParams.addAll(apiClient.parameterToPairs("", "name", name));
    
    queryParams.addAll(apiClient.parameterToPairs("", "agentId", agentId));
    
    queryParams.addAll(apiClient.parameterToPairs("", "agentEmail", agentEmail));
    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basicAuth" };
    
    

    

    
    
    TypeRef returnType = new TypeRef<PageDto>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * Create agent group
   * Create agent group using either list of agent ids or list of agent emails but not both
   * @param agentGroupDto 
   * @return ResourceIdDto
   */
  public ResourceIdDto createAgentGroup (AgentGroupDto agentGroupDto) throws ApiException {
    Object postBody = agentGroupDto;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'agentGroupDto' is set
     if (agentGroupDto == null) {
        throw new ApiException(400, "Missing the required parameter 'agentGroupDto' when calling createAgentGroup");
     }
     
    // create path and map variables
    String path = "/agent-groups".replaceAll("\\{format\\}","json");

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
    
    

    

    
    
    TypeRef returnType = new TypeRef<ResourceIdDto>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * Get agent group by id
   * Get agent group by id. Returns AgentGroupDto
   * @param id Id of agent group
   * @param fields Limit fields returned. Example fields=id,name,agents(id)
   * @return AgentGroupDto
   */
  public AgentGroupDto getAgentGroup (Long id, String fields) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling getAgentGroup");
     }
     
    // create path and map variables
    String path = "/agent-groups/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    
    queryParams.addAll(apiClient.parameterToPairs("", "fields", fields));
    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "basicAuth" };
    
    

    

    
    
    TypeRef returnType = new TypeRef<AgentGroupDto>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * Update agent group
   * Update existing agent group by id
   * @param id Id of agent group
   * @param agentGroup AgentGroup
   * @return String
   */
  public String updateAgentGroup (Long id, AgentGroupDto agentGroup) throws ApiException {
    Object postBody = agentGroup;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling updateAgentGroup");
     }
     
     // verify the required parameter 'agentGroup' is set
     if (agentGroup == null) {
        throw new ApiException(400, "Missing the required parameter 'agentGroup' when calling updateAgentGroup");
     }
     
    // create path and map variables
    String path = "/agent-groups/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
  /**
   * Delete agent group
   * Delete agent group by id
   * @param id Id of agent group
   * @return String
   */
  public String deleteAgentGroup (Long id) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'id' is set
     if (id == null) {
        throw new ApiException(400, "Missing the required parameter 'id' when calling deleteAgentGroup");
     }
     
    // create path and map variables
    String path = "/agent-groups/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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
    return apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    

    
    
    
    
    
    
    
    
    
    
  }
  
}
