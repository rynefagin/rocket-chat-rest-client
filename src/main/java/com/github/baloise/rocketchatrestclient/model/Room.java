package com.github.baloise.rocketchatrestclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Room extends Identified {
    private String name, topic, description;
    private RoomType type;
    private RoomCreator creator;
    private String[] usernames;
    private int msgCount;
    private Date created, updated;
    private boolean readOnly, sysMsgs;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @JsonSetter("t")
    public void setType(RoomType type) {
        this.type = type;
    }

    @JsonGetter("t")
    public RoomType getType() {
        return this.type;
    }

    @JsonSetter("u")
    public void setRoomCreator(RoomCreator creator) {
        this.creator = creator;
    }

    @JsonGetter("u")
    public RoomCreator getRoomCreator() {
        return this.creator;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setUsernames(String[] usernames) {
        this.usernames = usernames;
    }

    public String[] getUsernames() {
        return this.usernames;
    }

    @JsonSetter("msgs")
    public void setMessageCount(int count) {
        this.msgCount = count;
    }

    @JsonGetter("msgs")
    public int getMessageCount() {
        return this.msgCount;
    }

    @JsonSetter("ts")
    public void setCreatedDate(Date created) {
        this.created = created;
    }

    @JsonGetter("ts")
    public Date getCreatedDate() {
        return this.created;
    }

    @JsonSetter("_updatedAt")
    public void setUpdatedDate(Date updated) {
        this.updated = updated;
    }

    @JsonGetter("_updatedAt")
    public Date getUpdatedDate() {
        return this.updated;
    }

    @JsonSetter("ro")
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @JsonGetter("ro")
    public boolean isReadOnly() {
        return this.readOnly;
    }

    @JsonSetter("sysMes")
    public void setDisplayingSystemMessages(boolean display) {
        this.sysMsgs = display;
    }

    @JsonGetter("sysMes")
    public boolean isDisplayingSystemMessages() {
        return this.sysMsgs;
    }
}
