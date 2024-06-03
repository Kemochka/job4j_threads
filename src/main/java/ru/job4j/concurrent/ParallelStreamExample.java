package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> multiplication = list.stream()
                .reduce((left, right) -> left * right);
        System.out.println(multiplication.get());
        /*List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());
        Optional<Integer> multiplication = stream.reduce((left, right) -> left * right);
        System.out.println(multiplication.get());

        IntStream parallel = IntStream.range(1, 100).parallel();
        System.out.println(parallel.isParallel());
        IntStream sequential = parallel.sequential();
        System.out.println(sequential.isParallel());

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().parallel().peek(System.out::println).toList();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().parallel().forEach(System.out::println);

        List<Integer> list = Arrays.asList(1, 3, 4, 5, 2);
        list.stream().parallel().forEachOrdered(System.out::println);*/
    }
}
