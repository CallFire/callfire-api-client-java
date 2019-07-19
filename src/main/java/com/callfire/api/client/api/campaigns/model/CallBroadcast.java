package com.callfire.api.client.api.campaigns.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CallBroadcast that represents an IVR or Voice broadcast. If 'dialplanXml' field
 * is set then broadcast is IVR, otherwise Voice.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallBroadcast extends RetriableBroadcast {
    private List<Recipient> recipients;
    /**
     * IVR xml document describing dialplan. If dialplanXml != null then this is IVR broadcast
     */
    private String dialplanXml;
    /**
     * Voice broadcast sounds
     */
    private CallBroadcastSounds sounds;
    private AnsweringMachineConfig answeringMachineConfig;
    private Integer maxActiveTransfers;
}
