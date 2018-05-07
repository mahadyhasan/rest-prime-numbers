package com.example.primes.algo;

import java.util.List;

public abstract class PrimeAlgorithm {

    public List<Integer> getAllPrimes(int limit) {
        List<Integer> primeNumbers = calculateTill(limit);
        return primeNumbers;
    }

    protected abstract List<Integer> calculateTill(int limit);

    public abstract String getName();
}
