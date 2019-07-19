package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalTimeRestriction extends CallfireModel {
    private Boolean enabled;
    private Integer beginHour;
    private Integer beginMinute;
    private Integer endHour;
    private Integer endMinute;
}
