package com.callfire.api.client.api.callstexts.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class ActionRecord extends CallfireModel {
    private Long id;
    private Double billedAmount;
    private Date finishTime;
    private String toNumber;
    private String callerName;
    private String switchId;
    private Set<String> labels = new HashSet<>();
}
