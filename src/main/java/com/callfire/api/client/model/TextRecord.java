package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TextRecord extends BaseModel {
    private Long id;
    private Float billedAmount;
    private Long created;
    private Long finishTime;
    private String message;
    private TextResult textResult;

    public enum TextResult {
        SENT, RECEIVED, DNT, TOO_BIG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(Float billedAmount) {
        this.billedAmount = billedAmount;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TextResult getTextResult() {
        return textResult;
    }

    public void setTextResult(TextResult textResult) {
        this.textResult = textResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("billedAmount", billedAmount)
            .append("created", created)
            .append("finishTime", finishTime)
            .append("message", message)
            .append("textResult", textResult)
            .toString();
    }
}
