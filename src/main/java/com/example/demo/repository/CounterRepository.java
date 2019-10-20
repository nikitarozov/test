package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * The interface Counter repository.
 */
@Repository
public interface CounterRepository {

    /**
     * add counter
     *
     * @param key the key
     */
    void add(String key);

    /**
     * delete counter
     *
     * @param key the key
     */
    void delete(String key);

    /**
     * get counter
     *
     * @param key the key
     * @return the long
     */
    long get(String key);

    /**
     * increment counter
     *
     * @param key the key
     */
    void increment(String key);

    /**
     * get all counters
     *
     * @return the all
     */
    Set<String> getAll();

    /**
     * get count by all
     *
     * @return the long
     */
    long countAll();

    /**
     * Counter exist
     *
     * @param key the key
     * @return the boolean
     */
    boolean isExist(String key);
}
