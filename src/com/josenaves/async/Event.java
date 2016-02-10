package com.josenaves.async;

import java.util.Random;

/**
 * Represents an event (immutable).
 */
public final class Event implements Runnable {

    private String owner;
    private int timeout;

    private EventListener listener;

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

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        // do some arbitrary logic
        System.out.println("Processing event " + getOwner() + "...");
        try {
            Thread.sleep(getTimeout());
        } catch (InterruptedException e) {
            System.err.println("Error on Event run method");
        }

        if (new Random(System.currentTimeMillis()).nextBoolean()) {
            // success
            listener.onSuccess(this);
        }
        else {
            // failure
            listener.onFailure(this);
        }

    }
}
