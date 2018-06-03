package com.example.primes.domain;

import com.example.primes.algo.PrimeAlgorithm.PrimesStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PrimeResponse {

    private final UUID resultId;
    private final Long upperLimit;
    private final List<Long> primes;
    private final PrimesStrategy primesStrategy;
    @JsonIgnore
    private final LocalDateTime createTime;
    private String errorMessage;
    private Long processingTimeInNanoSeconds;

    public PrimeResponse(Long upperLimit) {
        this(upperLimit, PrimesStrategy.STREAM);
    }

    public PrimeResponse(Long upperLimit, PrimesStrategy primesStrategy) {
        this.resultId = UUID.randomUUID();
        this.upperLimit = upperLimit;
        this.primes = new ArrayList<>();
        this.primesStrategy = primesStrategy;
        createTime = LocalDateTime.now();
    }

    public String getResultId() {
        return resultId.toString();
    }

    public PrimesStrategy getPrimesStrategy() {
        return primesStrategy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Long getUpperLimit() {

        return upperLimit;
    }

    public List<Long> getPrimes() {
        return primes;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setProcessingTimeInNanoSeconds(Long processingTimeInNanoSeconds) {
        this.processingTimeInNanoSeconds = processingTimeInNanoSeconds;
    }

    public boolean add(Long primeNumber) {
        return this.primes.add(primeNumber);
    }

    public boolean addAll(List<Long> primeNumbers) {
        return this.primes.addAll(primeNumbers);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("resultId", resultId)
                .add("upperLimit", upperLimit)
                .add("primes", primes)
                .add("primesStrategy", primesStrategy)
                .add("errorMessage", errorMessage)
                .add("processingTimeInNanoSeconds", processingTimeInNanoSeconds)
                .toString();
    }

    @Override
    public int hashCode() {
        return resultId.hashCode();
    }

}
