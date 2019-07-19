package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentGroup extends CallfireModel {
    private Long id;
    private String name;
    private List<Agent> agents = new ArrayList<>();
    private List<Long> campaignIds = new ArrayList<>();
}
