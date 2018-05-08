package com.example.primes.algo;

import com.example.primes.algo.impl.SieveOfEratosthenes;
import org.junit.Before;

public class SieveOfEratosthenesPrimeAlgoTest extends AbstractPrimeAlgoTest {

    private SieveOfEratosthenes sieveOfEratosthenes;

    @Before
    public void setup() {
        sieveOfEratosthenes = new SieveOfEratosthenes();
    }

    @Override
    protected PrimeAlgorithm algorithmInTest() {
        return sieveOfEratosthenes;
    }

    @Override
    protected String getAlgorithmName() {
        return SieveOfEratosthenes.ALGO_NAME;
    }
}
