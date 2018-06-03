package com.example.primes.service;

import com.example.primes.domain.PrimeResponse;
import com.example.primes.task.PrimeGeneratorTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.primes.algo.PrimeAlgorithm.PrimesStrategy;

@Slf4j
@Service
public class PrimesServiceImpl implements PrimesService {

    private Map<String, PrimeResponse> resultCache = new HashMap<>();

    @Autowired
    private PrimeGeneratorTask primeGeneratorTask;

    @Override

    public List<PrimeResponse> getPrimeResults() {
        return this.resultCache.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrimeResponse> getPrimesResult(String resultId) {
        return this.resultCache.values()
                .stream()
                .filter(r -> r.getResultId().equals(resultId))
                .findAny();
    }

    @Override
    public PrimeResponse generatePrime(Long upperLimit, Optional<PrimesStrategy> algorithm) {
        PrimeResponse primeResponse = validatePrimesInput(upperLimit, algorithm);
        this.primeGeneratorTask.queueForProcessing(primeResponse);
        resultCache.put(primeResponse.getResultId(), primeResponse);
        return primeResponse;
    }

    private PrimeResponse validatePrimesInput(Long upperLimit, Optional<PrimesStrategy> algorithm) {
        if (upperLimit <= 0) {
            throw new IllegalArgumentException("Upper limit input must be greater than 0");
        }
        if (algorithm.isPresent()) {
            return new PrimeResponse(upperLimit, algorithm.get());
        } else {
            return new PrimeResponse(upperLimit);
        }
    }

    @Override
    public void purgeCacheOlderThanInterval(long intervalInMinutes) {
        List<String> keys = new ArrayList<>();
        resultCache.values()
                .stream()
                .filter(p -> p.getCreateTime().compareTo(LocalDateTime.now().minusMinutes(intervalInMinutes)) < 0)
                .forEach(k -> {
                    log.info("Removing Key {} Created At: {}", k.getResultId(), k.getCreateTime());
                    keys.add(k.getResultId());
                });

        keys.stream()
                .forEach(k -> resultCache.remove(k));
    }
}
