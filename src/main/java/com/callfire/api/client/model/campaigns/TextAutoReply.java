package com.callfire.api.client.model.campaigns;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents text auto-reply object
 */
public class TextAutoReply extends BaseModel {
    private Long id;
    private String number;
    private String keyword;
    private String match;
    private String message;

    /**
     * Get unique ID of text auto reply
     *
     * @return unique ID of text auto reply
     */
    public Long getId() {
        return id;
    }

    /**
     * Set unique ID of text auto reply
     *
     * @param id unique ID of text auto reply
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get phone number to configure an auto reply message
     *
     * @return phone number to configure an auto reply message
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set phone number to configure an auto reply message
     *
     * @param number phone number to configure an auto reply message
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Get keyword associated with account
     *
     * @return keyword associated with account
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Set keyword associated with account
     *
     * @param keyword keyword associated with account
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Get matching text is either null or empty which represents all matches.
     * All other text, for example 'rocks', will be matched as case insensitive whole words.
     *
     * @return matching text is either null or empty which represents all matches.
     */
    public String getMatch() {
        return match;
    }

    /**
     * Set matching text, null or empty represents all matches.
     * All other text, for example 'rocks', will be matched as case insensitive whole words.
     *
     * @param match text to match
     */
    public void setMatch(String match) {
        this.match = match;
    }

    /**
     * Get templated message to return as auto reply (ex: 'Here is an echo - ${text.message}')
     *
     * @return templated message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set templated message to return as auto reply (ex: 'Here is an echo - ${text.message}')
     *
     * @param message templated message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("number", number)
            .append("keyword", keyword)
            .append("match", match)
            .append("message", message)
            .toString();
    }
}
