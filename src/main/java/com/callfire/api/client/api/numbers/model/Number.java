package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Number extends CallfireModel {
    @Setter private String number;
    private String nationalFormat;
    private Boolean tollFree;
    private Region region;
}
