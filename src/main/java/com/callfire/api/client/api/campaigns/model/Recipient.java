package com.callfire.api.client.api.campaigns.model;

import java.util.HashMap;
import java.util.Map;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipient extends CallfireModel {
    private String phoneNumber;
    private String fromNumber;
    private Long contactId;
    private Map<String, String> attributes = new HashMap<>();
}
