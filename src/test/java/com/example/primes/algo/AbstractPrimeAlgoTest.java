package com.example.primes.algo;

import com.example.primes.exception.InvalidParameterException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractPrimeAlgoTest {

    protected abstract PrimeAlgorithm algorithmInTest();

    protected abstract String getAlgorithmName();


    @Test
    public void givenUpperLimitWhenCalculateThenResult() {
        List<Integer> allPrimes = algorithmInTest().getAllPrimes(10);
        assertTrue(allPrimes.containsAll(Arrays.asList(2, 3, 5, 7)));

        allPrimes = algorithmInTest().getAllPrimes(2);
        assertTrue(allPrimes.containsAll(Arrays.asList(2)));

        allPrimes = algorithmInTest().getAllPrimes(50);
        assertTrue(allPrimes.containsAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)));

        assertEquals(algorithmInTest().getAllPrimes(1).size(), 0);


    }

    @Test(expected = InvalidParameterException.class)
    public void givenNegativeNumberThenException() {
        algorithmInTest().getAllPrimes(-11);
    }


}
