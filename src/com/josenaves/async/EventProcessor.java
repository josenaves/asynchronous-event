package com.josenaves.async;

/**
 * Contract for event processors.
 */
public interface EventProcessor {
    void postEvent(Event event, EventListener listener);
}
