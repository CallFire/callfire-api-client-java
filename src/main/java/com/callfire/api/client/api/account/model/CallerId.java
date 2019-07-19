package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CallerId
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallerId extends CallfireModel {
    private String phoneNumber;
}
