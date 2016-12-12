package com.github.baloise.rocketchatrestclient.model;

public class Message extends Identified {
    private String msg;

    public Message() {
        this.msg = "";
    }

    public Message(String message) {
        this.msg = message;
    }

    public void setMsg(String message) {
        this.msg = message;
    }

    public String getMsg() {
        return this.msg;
    }
}
