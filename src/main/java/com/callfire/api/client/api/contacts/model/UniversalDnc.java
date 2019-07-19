package com.callfire.api.client.api.contacts.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class UniversalDnc extends CallfireModel {

    /**
     * Required number for DNC
     *
     * @return toNumber
     */
    private String toNumber;

    /**
     * Optional destination/source number for DNC
     *
     * @return source number
     */
    private String fromNumber;

    /**
     * @return true if toNumber can receive calls or If toNumber can call fromNumber
     */
    private Boolean inboundCall;

    /**
     * @return true if toNumber can receive texts or if toNumber can text fromNumber
     */
    private Boolean inboundText;

    /**
     * @return true if toNumber can send call or If fromNumber can call toNumber
     */
    private Boolean outboundCall;

    /**
     * @return true if toNumber can send texts or If fromNumber can text toNumber
     */
    private Boolean outboundText;

    public Boolean isInboundCall() {
        return inboundCall;
    }

    public Boolean isInboundText() {
        return inboundText;
    }

    public Boolean isOutboundCall() {
        return outboundCall;
    }

    public Boolean isOutboundText() {
        return outboundText;
    }
}
