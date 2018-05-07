package com.example.primes.algo.impl;

import com.example.primes.algo.PrimeAlgorithm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BruceForce extends PrimeAlgorithm {

    private static final String ALGO_NAME = "BruceForce";


    @Override
    protected List<Integer> calculateTill(int limit) {
        return IntStream.rangeClosed(2, limit)
                .filter(x -> isPrime(x)).boxed()
                .collect(Collectors.toList());
    }

    private boolean isPrime(int x) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(x)))
                .filter(n -> (n & 0X1) != 0)
                .allMatch(n -> x % n != 0);
    }

    @Override
    public String getName() {
        return ALGO_NAME;
    }
}
