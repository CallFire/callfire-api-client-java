package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object used to create campaign sound
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallCreateSound extends CallfireModel {

    /**
     * Name of sound to create
     *
     * @param name name of sound to create
     * @return name of sound to create
     */
    private String name;

    /**
     * Number to send call to
     *
     * @param toNumber number call is sent to
     * @return number call is sent to
     */
    private String toNumber;
}
