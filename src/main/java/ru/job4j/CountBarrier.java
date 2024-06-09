package ru.job4j;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(4);
        Thread countThread = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    for (int i = 0; i < 5; i++) {
                        countBarrier.count();
                    }
                }
        );
        Thread awaitThread = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.await();
                }
        );
        countThread.start();
        awaitThread.start();
    }
}
