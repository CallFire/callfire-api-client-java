package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents text auto-reply object
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextAutoReply extends CallfireModel {

    /**
     * Unique ID of text auto reply
     *
     * @param id unique ID of text auto reply
     * @return unique ID of text auto reply
     */
    private Long id;

    /**
     * Phone number to configure an auto reply message
     *
     * @param number phone number to configure an auto reply message
     * @return phone number to configure an auto reply message
     */
    private String number;

    /**
     * Keyword associated with account
     *
     * @param keyword keyword associated with account
     * @return keyword associated with account
     */
    private String keyword;

    /**
     * Matching text is either null or empty which represents all matches.
     * All other text, for example 'rocks', will be matched as case insensitive whole words.
     *
     * @param match text to match
     * @return matching text is either null or empty which represents all matches.
     */
    private String match;

    /**
     * Templated message to return as auto reply (ex: 'Here is an echo - ${text.message}')
     *
     * @param message templated message to set
     * @return templated message
     */
    private String message;
}
