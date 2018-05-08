package com.example.primes.algo;

import com.example.primes.algo.impl.BruteForce;
import org.junit.Before;

public class BruteForcePrimeAlgoTest extends AbstractPrimeAlgoTest {

    private BruteForce bruteForce;

    @Before
    public void setup() {
        bruteForce = new BruteForce();
    }

    @Override
    protected PrimeAlgorithm algorithmInTest() {
        return bruteForce;
    }

    @Override
    protected String getAlgorithmName() {
        return BruteForce.ALGO_NAME;
    }
}
