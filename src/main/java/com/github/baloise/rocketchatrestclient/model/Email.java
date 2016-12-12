package com.github.baloise.rocketchatrestclient.model;

/**
 * Represents an email address a user has on their account.
 *
 * @author Bradley Hilton (graywolf336)
 * @since 0.1.0
 * @version 0.0.1
 */
public class Email {
    private String address;
    private boolean verified;

    /**
     * Sets the address portion of the email
     *
     * @param address of the email
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the address of this email
     *
     * @return the email address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets whether this email has been verified or not
     *
     * @param verified email or not
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * Gets whether this email has been verified or not
     *
     * @return
     */
    public boolean getVerified() {
        return this.verified;
    }
}
