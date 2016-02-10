package com.josenaves.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a MainThread that dispatches events for processing.
 */
public final class MainThread implements Runnable, EventProcessor {

    private static final int CAPACITY = 100;

    private static volatile boolean keepRunning = true;

    // BlockingQueue is a thread safe implementation of Queue
    private final static BlockingQueue<EventHolder> queue = new ArrayBlockingQueue<>(CAPACITY);

    // Holds and event and its listener
    private class EventHolder {
        private Event event;
        private EventListener listener;

        public EventHolder(Event event, EventListener listener) {
            this.event = event;
            this.listener = listener;
        }
    }

    @Override
    public void postEvent(Event event, EventListener listener) {
        try {
            if (null != event && null != listener) {
                queue.put(new EventHolder(event, listener));
            }
            else {
                System.err.println("ERROR: um must pass event and listener not null");
            }
        } catch (InterruptedException e) {
            System.err.println("An error has occurred.");
        }
    }

    @Override
    public void run() {
        while (keepRunning || !queue.isEmpty()) {
            if (!queue.isEmpty()) {
                // handle the event from the queue
                EventHolder eventHolder = queue.remove();
                Event event = eventHolder.event;
                EventListener listener = eventHolder.listener;

                event.setListener(listener);

                new Thread(event).start();
            }
        }
        System.out.println("Event loop finished.");
    }

    public static synchronized void terminate() {
        MainThread.keepRunning = false;
    }
}
