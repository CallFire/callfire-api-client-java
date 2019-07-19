package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferNumber extends CallfireModel {
    private String name;
    private Boolean allowAssistedTransfer;
    private String phoneNumber;
}
