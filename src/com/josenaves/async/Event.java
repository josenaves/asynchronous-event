package com.josenaves.async;

/**
 * Represents an event (immutable).
 */
public final class Event {

    private String owner;
    private int timeout;

    public Event(String owner, int timeout) {
        this.owner = owner;
        this.timeout = timeout;
    }

    public String getOwner() {
        return owner;
    }

    public int getTimeout() {
        return timeout;
    }

}
