package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    void whenCheckCasCount() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread = new Thread(count::increment);
        Thread thread1 = new Thread(count::increment);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        assertThat(count.get()).isEqualTo(2);
    }
}