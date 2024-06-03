package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
            progress.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        var process = new char[] {'-', '\\', '|', '/'};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[i]);
                Thread.sleep(500);
                i++;
                if (i == process.length) {
                    i = 0;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
