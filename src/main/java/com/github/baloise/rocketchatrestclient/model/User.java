package com.github.baloise.rocketchatrestclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Represents a Rocket.Chat user.
 * <p>
 * The user's "services" fields aren't added yet, feel free to submit a pull request to implement
 * that if you need it.
 *
 * @author Bradley Hilton (graywolf336)
 * @version 0.0.1
 * @since 0.1.0
 */
public class User extends Identified {
    private String username, name;
    private String[] roles;
    private Email[] emails;
    private Date createdAt, lastLogin;
    private UserType type;
    private UserStatus status, statusConnection;
    private boolean active;
    private int utcOffset;

    /**
     * Sets the username of this user.
     *
     * @param username of this user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the username of this user.
     *
     * @return user of this user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the display name of this user.
     *
     * @param name of this user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the display name of this user.
     *
     * @return the name of this user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the {@link Email} addresses this user has on their account.
     *
     * @param emails the {@link Email} addresses
     */
    public void setEmails(Email[] emails) {
        this.emails = emails;
    }

    /**
     * Gets the {@link Email} addresses this user has.
     *
     * @return the {@link Email} addresses
     */
    public Email[] getEmails() {
        return this.emails;
    }

    /**
     * Sets the created date of this user.
     *
     * @param date this user was created
     */
    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    /**
     * Gets the date this user was created
     *
     * @return creation date of this user
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Sets the last login date of this user.
     *
     * @param date this user last logged in
     */
    public void setLastLogin(Date date) {
        this.lastLogin = date;
    }

    /**
     * Gets the date this user last logged in.
     *
     * @return last logged in date
     */
    public Date getLastLogin() {
        return this.lastLogin;
    }

    /**
     * Sets the roles which this user has assigned to them.
     *
     * @param roles the user is assigned
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    /**
     * Gets the roles the user has been assigned.
     *
     * @return the roles assigned to this user
     */
    public String[] getRoles() {
        return this.roles;
    }

    /**
     * Sets whether this user is active or not
     *
     * @param active whether this user is active or not
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets whether this user is active or not, non-active users can not log in.
     *
     * @return whether this user is logged in or not
     */
    @JsonGetter("active")
    public boolean isActive() {
        return this.active;
    }

    /**
     * Sets the {@link UserType} of user this is.
     *
     * @param type of user
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * Gets the {@link UserType} of user this is
     *
     * @return the {@link UserType} of user
     */
    public UserType getType() {
        return this.type;
    }

    /**
     * Sets the {@link UserStatus} of the user, their set one.
     *
     * @param status of the user
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Gets the {@link UserStatus} of the user
     *
     * @return the user's status
     */
    public UserStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the user's {@link UserStatus Connection Status}, which is whether they're connected to
     * the server or not.
     *
     * @param status the connection status of the user
     */
    public void setStatusConnection(UserStatus status) {
        //TODO: This check might not be needed, however "BUSY" isn't a valid connection status unless something changes
        if (status != UserStatus.ONLINE && status != UserStatus.OFFLINE && status != UserStatus.AWAY)
            throw new IllegalArgumentException("The provided status is not a valid one for Connection Status.");

        this.statusConnection = status;
    }

    /**
     * Gets the user's {@link UserStatus Connection Status}, which is whether they're connected to
     * the server or not.
     *
     * @return the connection status of the user
     */
    public UserStatus getStatusConnection() {
        return this.statusConnection;
    }

    /**
     * Sets the UTC Offset of this user.
     *
     * @param offset of this user
     */
    public void setUtcOffset(int offset) {
        this.utcOffset = offset;
    }

    /**
     * Gets the UTC Offset of this user
     *
     * @return utc offset for this user
     */
    public int getUtcOffset() {
        return this.utcOffset;
    }
}
