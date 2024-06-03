package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String saveDir;

    public Wget(String url, int speed, String saveDir) {
        this.url = url;
        this.speed = speed;
        this.saveDir = saveDir;
    }
    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(saveDir)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long downloadedBytes = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                downloadedBytes += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (downloadedBytes >= speed) {
                    var downloadAt = System.currentTimeMillis();
                    long downloadTime = start - downloadAt;
                    if (downloadTime < 1000) {
                        Thread.sleep(1000 - downloadTime);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        String saveDir = getFileName(args[0]);
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed, saveDir));
        wget.start();
        wget.join();
    }

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid argument length. Check the number of arguments");
        }
        try {
            new URL(args[0]).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if (Integer.parseInt(args[1]) == 0) {
            throw new IllegalArgumentException("Check the argument options");
        }
    }

    private static String getFileName(String file) {
        String[] name = file.split("/");
        return name[name.length - 1];
    }
}

