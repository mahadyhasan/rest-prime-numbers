package com.example.primes.algo;

import java.util.List;

public interface PrimeAlgorithm {

    List<Long> generatePrimes();

    Long getUpperLimit();

    enum PrimesStrategy {
        FORK_JOIN,
        BRUTE_FORCE,
        PARALLEL_STREAM,
        STREAM,
        ERATOSTHENES_SIEVE
    }
}
