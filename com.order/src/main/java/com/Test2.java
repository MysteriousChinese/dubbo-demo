package com;

import org.apache.dubbo.common.utils.AtomicPositiveInteger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Test2 {
    public static void main(String[] args) {
        ConcurrentMap<String, AtomicPositiveInteger> sequences =
                new ConcurrentHashMap<String, AtomicPositiveInteger>();

        String key = "1";
        AtomicPositiveInteger sequence = sequences.get(key);
        if (sequence == null) {
            sequences.putIfAbsent(key, new AtomicPositiveInteger());
            sequence = sequences.get(key);
        }

        int currentSequence = sequence.getAndIncrement();

        for (int i = 0; i < 10; i++) {

            sequence = sequences.get(key);
        }

    }
}
