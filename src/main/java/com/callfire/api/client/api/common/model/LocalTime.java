package com.callfire.api.client.api.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto to represent time (hour, minute, second)
 * 0 based time, example (23, 0, 59)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalTime extends CallfireModel {
    private Integer hour;
    private Integer minute;
    private Integer second;
}
