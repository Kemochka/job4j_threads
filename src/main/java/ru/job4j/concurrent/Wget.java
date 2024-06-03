package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread loader = new Thread(
                () -> {
                    try {
                        for (int index = 0; index <= 100; index++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + index  + "%");
                    }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        loader.start();
    }
}
