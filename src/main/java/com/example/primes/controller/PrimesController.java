package com.example.primes.controller;

import com.codahale.metrics.annotation.Timed;
import com.example.primes.algo.PrimeAlgorithm.PrimesStrategy;
import com.example.primes.domain.PrimeResponse;
import com.example.primes.service.PrimesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/v1/primes")
@Slf4j
public class PrimesController {

    @Autowired
    private PrimesService primesService;

    @RequestMapping(value = "/strategy/default/{upperLimit:\\d+}", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<PrimeResponse> generatePrimesDefault(@PathVariable("upperLimit") Long upperLimit) {
        return new ResponseEntity<>(this.primesService.generatePrime(upperLimit, Optional.empty()), HttpStatus.OK);
    }

    @RequestMapping(value = "/strategy/{strategyName}/{upperLimit:\\d+}", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<PrimeResponse> generatePrimes(
            @PathVariable("strategyName") PrimesStrategy strategyName,
            @PathVariable("upperLimit") Long upperLimit) {
        return new ResponseEntity<>(this.primesService.generatePrime(upperLimit, Optional.of(strategyName)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PrimeResponse> getGeneratedPrimeById(@PathVariable("uuid") String resultId) {
        Optional<PrimeResponse> primeResponseOptional = this.primesService.getPrimesResult(resultId);
        PrimeResponse primeResponse;
        if (!primeResponseOptional.isPresent()) {
            primeResponse = new PrimeResponse(null);
            primeResponse.setErrorMessage("No Result Found");
        } else {
            primeResponse = primeResponseOptional.get();
        }
        return new ResponseEntity<>(primeResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PrimeResponse>> getAllGeneratedPrimes() {
        return new ResponseEntity<>(this.primesService.getPrimeResults(), HttpStatus.OK);
    }

    @RequestMapping(value = "/strategyNames",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<String>> getPrimesStrategyNames() {
        return new ResponseEntity<>(
                Stream.of(PrimesStrategy.values())
                        .map(PrimesStrategy::name)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

}

