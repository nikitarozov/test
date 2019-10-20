package com.example.demo;

import com.example.demo.repository.CounterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests extends Assert {

    private final int countIncrements = 600;
    private final int countTask = 100000;
    private final int threadPull = 20;

    @Autowired
    private CounterRepository counterRepository;

    private static String getRandomString() {
        byte[] array = new byte[15];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    @Test
    void testCounter() {
        String name = "new";
        counterRepository.add(name);
        for (int i = 0; i < this.countIncrements; i++) {
            counterRepository.increment(name);
        }
        isTrue(counterRepository.get(name) == this.countIncrements, "error counter increment");
        counterRepository.delete(name);
        isTrue(!counterRepository.isExist(name), "error with delete counter");
    }

    @Test
    void testConcurrencyCounter() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(this.threadPull);
        class Task implements Runnable {
            private final String key;

            private Task(String key) {
                this.key = key;
            }

            @Override
            public void run() {
                counterRepository.add(this.key);
                for (int i = 0; i < DemoApplicationTests.this.countIncrements; i++) {
                    counterRepository.increment(this.key);
                }
                isTrue(counterRepository.get(this.key) == DemoApplicationTests.this.countIncrements, "error counter increment");
            }
        }
        for (int i = 0; i < this.countTask; i++) {
            threadPool.execute(new Task(getRandomString()));
        }
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        isTrue(counterRepository.countAll() == this.countTask * this.countIncrements, "not equals all count");
        isTrue(counterRepository.getAll().size() == this.countTask, "not equals all counter");
    }


}
