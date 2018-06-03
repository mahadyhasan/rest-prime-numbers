//package com.example.primes.algo;
//
//import com.example.primes.exception.InvalidParameterException;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//public class SieveOfEratosthenes extends PrimeAlgorithm {
//
//    public static final String ALGO_NAME = "SieveOfEratosthenes";
//
//
//    /**
//     * Create a list of consecutive integers from 2 to n: (2, 3, 4, …, n)
//     * Initially, let p be equal 2, the first prime number
//     * Starting from p, count up in increments of p and mark each of these numbers greater than p itself in the list.
//     * These numbers will be 2p, 3p, 4p, etc.; note that some of them may have already been marked
//     * Find the first number greater than p in the list that is not marked. If there was no such number, stop.
//     * Otherwise, let p now equal this number (which is the next prime), and repeat from step 3
//     *
//     * @param limit
//     * @return list of prime numbers
//     */
//    @Override
//    protected List<Integer> calculateTill(int limit) { //11
//
//        if (limit < 0) {
//            throw new InvalidParameterException("Upper limit number cannot be less than 0");
//        }
//
//        boolean prime[] = new boolean[limit + 1];
//        Arrays.fill(prime, true);
//        for (int p = 2; p * p <= limit; p++) {
//            if (prime[p]) {
//                for (int i = p * 2; i <= limit; i += p) {
//                    prime[i] = false;
//                }
//            }
//        }
//
//        List<Integer> primeNumbers = new LinkedList<>();
//        for (int i = 2; i <= limit; i++) {
//            if (prime[i]) {
//                primeNumbers.add(i);
//            }
//        }
//
//        return primeNumbers;
//
//    }
//
//
//    @Override
//    public String getName() {
//        return ALGO_NAME;
//    }
//}
