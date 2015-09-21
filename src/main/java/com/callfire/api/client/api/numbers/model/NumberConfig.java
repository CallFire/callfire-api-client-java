package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class NumberConfig extends CallfireModel {
    private String number;
    private ConfigType configType;
    private CallTrackingConfig callTrackingConfig;
    private IvrInboundConfig ivrInboundConfig;

    public enum ConfigType {
        IVR, TRACKING;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ConfigType getConfigType() {
        return configType;
    }

    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

    public CallTrackingConfig getCallTrackingConfig() {
        return callTrackingConfig;
    }

    public void setCallTrackingConfig(CallTrackingConfig callTrackingConfig) {
        this.callTrackingConfig = callTrackingConfig;
    }

    public IvrInboundConfig getIvrInboundConfig() {
        return ivrInboundConfig;
    }

    public void setIvrInboundConfig(IvrInboundConfig ivrInboundConfig) {
        this.ivrInboundConfig = ivrInboundConfig;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("number", number)
            .append("configType", configType)
            .append("callTrackingConfig", callTrackingConfig)
            .append("ivrInboundConfig", ivrInboundConfig)
            .toString();
    }
}
