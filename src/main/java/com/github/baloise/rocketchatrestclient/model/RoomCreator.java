package com.github.baloise.rocketchatrestclient.model;

/**
 * Represents a room's creator, only containing "_id" and "username".
 *
 * @author Bradley Hilton (graywolf336)
 * @version 0.0.1
 * @since 0.1.0
 */
public class RoomCreator extends Identified {
    private String username;
    
    /**
     * Sets the username of this room creator.
     * 
     * @param username of the room creator
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Gets the username of this room creator.
     * 
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }
}
