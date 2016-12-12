package com.github.baloise.rocketchatrestclient;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

//This is an internal class that really shouldn't be used,
//it's just meant to handle the response from the server
public class RocketChatClientResponse {
    private boolean success;
    private ServerInfo info;
    private Message[] messages;
    private Message message;
    private User[] users;
    private User user;
    private Room[] channels, ims, groups;
    private Room channel, group;

    public void setSuccess(boolean result) {
        this.success = result;
    }

    public boolean isSuccessful() {
        return this.success;
    }
    
    @JsonSetter("info")
    public void setServerInfo(ServerInfo info) {
        this.info = info;
    }
    
    @JsonGetter("info")
    public ServerInfo getServerInfo() {
        return this.info;
    }
    
    public boolean hasServerInfo() {
        return this.info != null;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public Message[] getMessages() {
        return this.messages;
    }

    public boolean hasMessages() {
        return this.messages != null;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    public boolean hasMessage() {
        return this.message != null;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return this.users;
    }

    public boolean hasUsers() {
        return this.users != null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public boolean hasUser() {
        return this.user != null;
    }
    
    public void setChannels(Room[] channels) {
        this.channels = channels;
    }
    
    public Room[] getChannels() {
        return this.channels;
    }
    
    public boolean hasChannels() {
        return this.channels != null;
    }
    
    public void setChannel(Room channel) {
        this.channel = channel;
    }
    
    public Room getChannel() {
        return this.channel;
    }
    
    public boolean hasChannel() {
        return this.channel != null;
    }
    
    public void setGroups(Room[] groups) {
        this.groups = groups;
    }
    
    public Room[] getGroups() {
        return this.groups;
    }
    
    public boolean hasGroups() {
        return this.groups != null;
    }
    
    public void setGroup(Room group) {
        this.group = group;
    }
    
    public Room getGroup() {
        return this.group;
    }
    
    public boolean hasGroup() {
        return this.group != null;
    }
    
    @JsonSetter("ims")
    public void setDirectMessages(Room[] dms) {
        this.ims = dms;
    }
    
    @JsonGetter("ims")
    public Room[] getDirectMessages() {
        return this.ims;
    }
    
    public boolean hasDirectMessages() {
        return this.ims != null;
    }
}
