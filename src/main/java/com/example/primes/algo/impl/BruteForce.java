package com.example.primes.algo.impl;

import com.example.primes.algo.PrimeAlgorithm;
import com.example.primes.exception.InvalidParameterException;

import java.util.LinkedList;
import java.util.List;

public class BruteForce extends PrimeAlgorithm {

    public static final String ALGO_NAME = "BruteForce";


    @Override
    protected List<Integer> calculateTill(int limit) {

        if (limit < 0) {
            throw new InvalidParameterException("Upper limit number cannot be less than 0");
        }

        List<Integer> primeNumbers = new LinkedList<>();

        for (int i = 1; i <= limit; i++) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private static boolean isPrimeBruteForce(int number) {

        // even numbers stop here.
        // Only 2 is an even prime number & the first prime number
        // 1 is not a prime number
        if (number % 2 == 0 || number == 1) {
            return (number == 2);
        }

        // odd numbers from 3 to n get here
        // goes inside a loop only if i*i <= number
        // so, numbers 3, 5, 7 skip this for loop
        // 9, 11, 13, 15, etc get in as 3*3 = 9, 3*3 < 11, and so on
        for (int i = 3; i * i <= number; i += 2) {
            //divisible by other than itself
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return ALGO_NAME;
    }
}
