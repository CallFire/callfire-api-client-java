package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.Voice;

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
public class CallRecipient extends Recipient {
    /** If dialplanXml != null then IVR Broadcast. */
    private String dialplanXml;
    private String liveMessage;
    private Long liveMessageSoundId;
    private String machineMessage;
    private Long machineMessageSoundId;
    private String transferMessage;
    private Long transferMessageSoundId;
    private String transferDigit;
    private String transferNumber;
    private Voice voice;
}
