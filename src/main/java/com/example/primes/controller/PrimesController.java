package com.example.primes.controller;

import com.example.primes.algo.PrimeAlgorithm;
import com.example.primes.algo.domain.PrimeResponse;
import com.example.primes.exception.InvalidParameterException;
import com.example.primes.service.PrimeAlgorithmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/primes")
@Slf4j
public class PrimesController {

    @Autowired
    private PrimeAlgorithmService primeCalculatorService;


    @RequestMapping(value = "/{upperLimit}", method = RequestMethod.GET)
    public PrimeResponse getPrimeNumbers(@PathVariable(value = "upperLimit") final int upperLimit,
                                         @RequestParam(value = "algorithm") final String algorithm) {

        log.info("Received request to generate prime numbers up to {}, using algorithm {}", upperLimit, algorithm);

        PrimeAlgorithm primeAlgorithm = getAlgorithm(algorithm);

        return new PrimeResponse(upperLimit, primeAlgorithm.getAllPrimes(upperLimit));


    }

    private PrimeAlgorithm getAlgorithm(final String algorithm) {
        return primeCalculatorService.getAlgorithmMap().entrySet()
                .stream()
                .filter(e -> e.getKey().equals(algorithm))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException("Invalid algorithm given: " + algorithm));
    }
}
