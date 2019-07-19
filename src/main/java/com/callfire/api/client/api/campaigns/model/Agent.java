package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class Agent extends CallfireModel {
    private Long id;
    private Boolean enabled;
    private String name;
    private String email;
    private Date lastLogin;
    private Long activeSessionId;
    @Builder.Default private List<Long> campaignIds = new ArrayList<>();
    @Builder.Default private List<Long> groupIds = new ArrayList<>();
}
