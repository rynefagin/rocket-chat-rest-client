package com.github.baloise.rocketchatrestclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ServerBuildInfo {
    private Date date;
    private String nodeVersion, arch, platform, osRelease;
    private long totalMemory, freeMemory;
    private int cpus;
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setNodeVersion(String nodeVersion) {
        this.nodeVersion = nodeVersion;
    }
    
    public String getNodeVersion() {
        return this.nodeVersion;
    }
    
    @JsonSetter("arch")
    public void setArchitecture(String arch) {
        this.arch = arch;
    }
    
    @JsonGetter("arch")
    public String getArchitecture() {
        return this.arch;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public void setOsRelease(String osRelease) {
        this.osRelease = osRelease;
    }
    
    public String getOsRelease() {
        return this.osRelease;
    }
    
    public void setTotalMemory(long total) {
        this.totalMemory = total;
    }
    
    public long getTotalMemory() {
        return this.totalMemory;
    }
    
    public void setFreeMemory(long free) {
        this.freeMemory = free;
    }
    
    public long getFreeMemory() {
        return this.freeMemory;
    }
    
    @JsonSetter("cpus")
    public void setCPUCount(int cpus) {
        this.cpus = cpus;
    }
    
    @JsonGetter("cpus")
    public int getCPUCount() {
        return this.cpus;
    }
}
