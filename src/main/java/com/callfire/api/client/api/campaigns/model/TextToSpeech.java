package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TTS representation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextToSpeech extends CallfireModel {
    private Voice voice;
    private String message;
}
