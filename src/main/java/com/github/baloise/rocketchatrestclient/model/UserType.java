package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the different types of user accounts.
 *
 * @author Bradley Hilton (graywolf336)
 * @since 0.1.0
 * @version 0.0.1
 */
public enum UserType {
    /** A normal user. */
    @JsonProperty("user")
    USER,
    
    /** A bot user, not a normal person. */
    @JsonProperty("bot")
    BOT;
}
