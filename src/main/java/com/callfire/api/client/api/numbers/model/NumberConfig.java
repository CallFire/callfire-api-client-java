package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberConfig extends CallfireModel {
    private String number;
    private ConfigType configType;
    private CallTrackingConfig callTrackingConfig;
    private IvrInboundConfig ivrInboundConfig;

    public enum ConfigType {
        IVR,
        TRACKING;
    }
}
