package com.josenaves.async;

/**
 * Represents a listener for events
 */
public interface EventListener {
    void onSuccess(Event event);
    void onFailure(Event event);
}
