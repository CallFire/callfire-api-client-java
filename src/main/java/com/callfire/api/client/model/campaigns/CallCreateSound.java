package com.callfire.api.client.model.campaigns;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Object used to create campaign sound
 */
public class CallCreateSound extends BaseModel {
    private String name;
    private String toNumber;

    /**
     * Get name of sound to create
     *
     * @return name of sound to create
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of sound to create
     *
     * @param name name of sound to create
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get number call is sent to
     *
     * @return number call is sent to
     */
    public String getToNumber() {
        return toNumber;
    }

    /**
     * Set number call is sent to
     *
     * @param toNumber number call is sent to
     */
    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name)
            .append("toNumber", toNumber)
            .toString();
    }
}
