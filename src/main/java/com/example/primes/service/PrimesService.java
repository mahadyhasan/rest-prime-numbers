package com.example.primes.service;

import com.example.primes.algo.PrimeAlgorithm;
import com.example.primes.domain.PrimeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PrimesService {

    List<PrimeResponse> getPrimeResults();

    Optional<PrimeResponse> getPrimesResult(String resultId);

    PrimeResponse generatePrime(Long upperLimit, Optional<PrimeAlgorithm.PrimesStrategy> algorithm);

    void purgeCacheOlderThanInterval(long intervalInMinutes);


}
