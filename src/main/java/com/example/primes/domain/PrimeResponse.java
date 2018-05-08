package com.example.primes.domain;

import lombok.Data;

import java.util.List;

@Data
public class PrimeResponse {

    private Integer initial;

    private List<Integer> primes;

    public PrimeResponse(Integer initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public PrimeResponse() {

    }
}
