package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the different types of rooms available.
 *
 * @author Bradley Hilton (graywolf336)
 * @since 0.1.0
 * @version 0.0.1
 */
public enum RoomType {
    /** Public Channel */
    @JsonProperty("c")
    PUBLIC_CHANNEL,
    
    /** Direct Message */
    @JsonProperty("d")
    DIRECT_MESSAGE,
    
    /** Private Group */
    @JsonProperty("p")
    PRIVATE_GROUP;
}
