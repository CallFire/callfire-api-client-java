package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class Region extends CallfireModel {
    private String prefix;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String lata;
    private String rateCenter;
    private Double latitude;
    private Double longitude;
    private String timeZone;
}
