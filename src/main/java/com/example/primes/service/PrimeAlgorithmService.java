package com.example.primes.service;

import com.example.primes.algo.PrimeAlgorithm;
import com.example.primes.algo.impl.BruteForce;
import com.example.primes.algo.impl.SieveOfEratosthenes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrimeAlgorithmService {

    private Map<String, PrimeAlgorithm> algorithmMap = new HashMap<>();


    @PostConstruct
    public void init() {
        addAlgorithm((new SieveOfEratosthenes()));
        addAlgorithm(new BruteForce());
    }

    public PrimeAlgorithm getAlgorithm(String name) {
        return algorithmMap.get(name);
    }


    public void addAlgorithm(PrimeAlgorithm algorithm) {
        algorithmMap.put(algorithm.getName(), algorithm);
    }

    public Map<String, PrimeAlgorithm> getAlgorithmMap() {
        return algorithmMap;
    }
}
