package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryCounterRepository implements CounterRepository {

    private ConcurrentMap<String, AtomicLong> dataSource = new ConcurrentHashMap<>();

    public void increment(String key) {
        AtomicLong counter = dataSource.get(key);
        if (counter == null) {
            throw new NotFoundException("Counter not found");
        }
        counter.getAndIncrement();
    }

    public void add(String key) {
        if (dataSource.containsKey(key)) {
            throw new AlreadyExistException("Counter already exist");
        }
        dataSource.putIfAbsent(key, new AtomicLong(0));
    }

    public long get(String key) {
        AtomicLong counter = dataSource.get(key);
        if (counter == null) {
            throw new NotFoundException("Counter not found");
        }
        return counter.longValue();
    }

    public void delete(String key) {
        dataSource.remove(key);
    }

    public Set<String> getAll() {
        return dataSource.keySet();
    }

    public boolean isExist(String key) {
        return dataSource.containsKey(key);
    }

    public long countAll() {
        return dataSource.values().parallelStream().mapToLong(AtomicLong::longValue).sum();
    }
}
