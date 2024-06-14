package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T value;
    private final int start;
    private final int end;


    public SearchIndex(T[] array, T value, int start, int end) {
        this.array = array;
        this.value = value;
        this.start = start;
        this.end = end;

    }

    public int search() {
        int result = -1;
        for (int i = start; i <= end; i++) {
            if (array[i].equals(value)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            return search();
        }
        int middle = (start + end) / 2;
        SearchIndex<T> searchIndex1 = new SearchIndex<>(array, value, start, middle);
        SearchIndex<T> searchIndex2 = new SearchIndex<>(array, value, middle + 1, end);
        searchIndex1.fork();
        searchIndex2.fork();
        return Math.max(searchIndex1.join(), searchIndex2.join());
    }

    public static <T> int find(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchIndex<>(array, value, 0, array.length - 1));
    }
}
