package com.example.primes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PrimeResponse {

    private Integer initial;

    private List<Integer> primes;
}
