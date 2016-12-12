package com.github.baloise.rocketchatrestclient.model;

public class ServerCommitInfo {
    private String hash, date, author, subject, tag, branch;
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setSubject(String subject) { 
        this.subject = subject;
    }
    
    public String getSubject() {
        return this.subject;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public String getBranch() {
        return this.branch;
    }
}
