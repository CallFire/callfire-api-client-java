package com.callfire.api.client.api.campaigns.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sounds for a CallBroadcast.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallBroadcastSounds {
    private String liveSoundText;
    private Voice liveSoundTextVoice;
    private Long liveSoundId;
    private String machineSoundText;
    private Voice machineSoundTextVoice;
    private Long machineSoundId;
    private String transferSoundText;
    private Voice transferSoundTextVoice;
    private Long transferSoundId;
    private String transferDigit;
    private String transferNumber;
    private String dncSoundText;
    private Voice dncSoundTextVoice;
    private Long dncSoundId;
    private String dncDigit;
}
