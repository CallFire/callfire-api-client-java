package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TTS representation
 */
public class TextToSpeech extends BaseModel {
    private VoiceEnum voice;
    private String message;

    /**
     * TTS voice types
     */
    public enum VoiceEnum {
        MALE1, FEMALE1, FEMALE2, SPANISH1, FRENCHCANADIAN1,
    }

    /**
     * Get TTS voice
     *
     * @return voice type
     */
    public VoiceEnum getVoice() {
        return voice;
    }

    /**
     * Set TTS voice
     *
     * @param voice voice type
     */
    public void setVoice(VoiceEnum voice) {
        this.voice = voice;
    }

    /**
     * Get TTS text message
     *
     * @return text message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set TTS text message
     *
     * @param message text message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("voice", voice)
            .append("message", message)
            .toString();
    }
}
