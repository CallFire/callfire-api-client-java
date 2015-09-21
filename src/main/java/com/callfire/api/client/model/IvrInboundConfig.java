package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class IvrInboundConfig extends BaseModel {
    private String dialplanXml;

    public String getDialplanXml() {
        return dialplanXml;
    }

    public void setDialplanXml(String dialplanXml) {
        this.dialplanXml = dialplanXml;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("dialplanXml", dialplanXml)
            .toString();
    }
}
