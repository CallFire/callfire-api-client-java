package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CccCampaign extends Broadcast {
    private Script script;
    private String smartDropSoundText;
    private Voice smartDropSoundTextVoice;
    private Long smartDropSoundId;
    private Boolean recorded;
    private Boolean allowAnyTransfer;
    private Integer multilineDialingRatio;
    private Boolean multilineDialingEnabled;
    private Integer scrubLevel;
    @Builder.Default private List<Agent> agents = new ArrayList<>();
    @Builder.Default private List<AgentGroup> agentGroups = new ArrayList<>();
    @Builder.Default private List<TransferNumber> transferNumbers = new ArrayList<>();
}
