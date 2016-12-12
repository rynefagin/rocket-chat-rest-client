package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the status of this user, both their connection and their displayed one.
 *
 * @author Bradley Hilton (graywolf336)
 * @since 0.1.0
 * @version 0.0.1
 */
public enum UserStatus {
    @JsonProperty("online")
    ONLINE,
    
    @JsonProperty("offline")
    OFFLINE,
    
    @JsonProperty("away")
    AWAY,
    
    @JsonProperty("busy")
    BUSY;
}
