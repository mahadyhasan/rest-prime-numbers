package com.example.primes.algo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PrimeResponse {

    private Integer limit;

    private List<Integer> primeList;
}
