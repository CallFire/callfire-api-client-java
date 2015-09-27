package com.callfire.api.client.api.contacts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UniversalDnc extends CallfireModel {
    private String toNumber;
    private String fromNumber;
    private Boolean inboundCall;
    private Boolean inboundText;
    private Boolean outboundCall;
    private Boolean outboundText;

    /**
     * Get required number for DNC
     *
     * @return toNumber
     */
    public String getToNumber() {
        return toNumber;
    }

    /**
     * Set required number for DNC
     *
     * @param toNumber required number for DNC
     */
    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    /**
     * Get optional destination/source number for DNC
     *
     * @return source number
     */
    public String getFromNumber() {
        return fromNumber;
    }

    /**
     * Set optional destination/source number for DNC
     *
     * @param fromNumber source number
     */
    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    /**
     * @return true if toNumber can receive calls or If toNumber can call fromNumber
     */
    public Boolean isInboundCall() {
        return inboundCall;
    }

    /**
     * @param inboundCall set to true if toNumber should receive calls or if toNumber should call fromNumber
     */
    public void setInboundCall(Boolean inboundCall) {
        this.inboundCall = inboundCall;
    }

    /**
     * @return true if toNumber can receive texts or if toNumber can text fromNumber
     */
    public Boolean isInboundText() {
        return inboundText;
    }

    /**
     * @param inboundText set to true if toNumber should receive texts or if toNumber should text fromNumber
     */
    public void setInboundText(Boolean inboundText) {
        this.inboundText = inboundText;
    }

    /**
     * @return true if toNumber should send calls or if fromNumber should call toNumber
     */
    public Boolean isOutboundCall() {
        return outboundCall;
    }

    /**
     * @param outboundCall set to true if toNumber should send calls or if fromNumber should call toNumber
     */
    public void setOutboundCall(Boolean outboundCall) {
        this.outboundCall = outboundCall;
    }

    /**
     * @return true if toNumber can send texts or If fromNumber can text toNumber
     */
    public Boolean isOutboundText() {
        return outboundText;
    }

    /**
     * @param outboundText set to true if toNumber should send texts or if fromNumber should text toNumber
     */
    public void setOutboundText(Boolean outboundText) {
        this.outboundText = outboundText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("toNumber", toNumber)
            .append("fromNumber", fromNumber)
            .append("inboundCall", inboundCall)
            .append("inboundText", inboundText)
            .append("outboundCall", outboundCall)
            .append("outboundText", outboundText)
            .toString();
    }
}
