package com.callfire.api.client.api.account.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Builder;
import lombok.Getter;

/**
 * Request object for GET /orders API
 * <p>
 * https://www.callfire.com/v2/swagger-ui/index.html#!/orders/findOrders
 */
@Getter
@Builder
public class FindOrdersRequest extends CallfireModel {

}
