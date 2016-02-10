package com.josenaves.async;

public class Main {

    public static void main(String[] args) throws Exception {

        MainThread mainThread = new MainThread();

        new Thread(mainThread).start();

        // create arbitrary events
        mainThread.postEvent(
                new Event("Event 1", 10000),
                new EventListener() {
                    @Override
                    public void onSuccess(Event event) {
                        System.out.println("Event " + event.getOwner() + " successfully processed.");
                    }

                    @Override
                    public void onFailure(Event event) {
                        System.out.println("An error has occurred processing event " + event.getOwner() + ".");
                    }
                }

        );

        mainThread.postEvent(
                new Event("Event 2", 5000),
                new EventListener() {
                    @Override
                    public void onSuccess(Event event) {
                        System.out.println("Event " + event.getOwner() + " successfully processed.");
                    }

                    @Override
                    public void onFailure(Event event) {
                        System.out.println("An error has occurred processing event " + event.getOwner() + ".");
                    }
                }

        );

        mainThread.postEvent(
                new Event("Event 3", 3000),
                new EventListener() {
                    @Override
                    public void onSuccess(Event event) {
                        System.out.println("Event " + event.getOwner() + " successfully processed.");
                    }

                    @Override
                    public void onFailure(Event event) {
                        System.out.println("An error has occurred processing event " + event.getOwner() + ".");
                    }
                }

        );

        Thread.sleep(19000);
        MainThread.terminate();

        System.out.println("Finished");
    }

}
