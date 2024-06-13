package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());
    public void emailTo(User user) {
        pool.submit(new Thread(() -> {
            String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
            System.out.println(subject);
            System.out.println(body);
        }));
    }

    public void send(String subject, String body, String email) {
    }

    private void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Anna", "qwerty@mail.ru"));
        emailNotification.close();
    }
}
