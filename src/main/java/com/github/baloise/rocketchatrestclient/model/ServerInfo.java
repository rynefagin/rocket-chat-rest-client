package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ServerInfo {
    private String version;
    private ServerBuildInfo buildInfo;
    private ServerCommitInfo commitInfo;
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    @JsonSetter("build")
    public void setBuildInfo(ServerBuildInfo info) {
        this.buildInfo = info;
    }
    
    @JsonGetter("build")
    public ServerBuildInfo getBuildInfo() {
        return this.buildInfo;
    }
    
    @JsonSetter("commit")
    public void setCommitInfo(ServerCommitInfo info) {
        this.commitInfo = info;
    }
    
    @JsonSetter("commit")
    public ServerCommitInfo getCommitInfo() {
        return this.commitInfo;
    }
}
