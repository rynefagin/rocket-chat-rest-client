package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Represents an object that has the value "_id".
 *
 * @since 0.0.1
 * @version 0.1.0
 */
public abstract class Identified implements Comparable<Identified> {
    private String _id;

    /**
     * Sets the "_id" of this object.
     *
     * @param id the value to set
     */
    @JsonSetter("_id")
    public void setId(String id) {
        this._id = id;
    }

    /**
     * Gets the "_id" field of this record.
     *
     * @return the _id value of this record
     */
    @JsonGetter("_id")
    public String getId() {
        return this._id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Identified) obj)._id.equals(_id);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + _id;
    }

    @Override
    public int compareTo(Identified o) {
        return toString().compareTo(o.toString());
    }
}
