package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SearchIndexTest {
    @Test
    void whenFindIndex() {
        Integer[] array = {1, 23, 45};
        int value = 45;
        assertThat(SearchIndex.find(array, value)).isEqualTo(2);
    }

    @Test
    void whenDiffType() {
        Object[] array = {1, 23, "task", 65, 3, "name", 12, 33, 56, 89, "surname", 223, 7, 11};
        assertThat(SearchIndex.find(array, "task")).isEqualTo(2);
    }

    @Test
    void whenAnotherType() {
        String[] array = {"task", "name", "surname", "a", "b"};
        assertThat(SearchIndex.find(array, "a")).isEqualTo(3);
    }

    @Test
    void whenLineSearch() {
        Integer[] array = {1, 2, 3, 5, 7, 8, 43, 23, 56, 89, 9};
        assertThat(SearchIndex.find(array, 43)).isEqualTo(6);
    }

    @Test
    void whenRecursiveSearch() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        assertThat(SearchIndex.find(array, 37)).isEqualTo(37);
    }

    @Test
    void whenElementNotFound() {
        Integer[] array = {1};
        assertThat(SearchIndex.find(array, 2)).isEqualTo(-1);
    }
}