package com.example.primes.controller;

import com.example.primes.Application;
import com.example.primes.domain.ApiErrorResponse;
import com.example.primes.domain.PrimeResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimesRestControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;
    private HttpEntity<String> entity;
    private ResponseEntity<PrimeResponse> response;
    private ResponseEntity<ApiErrorResponse> errorResponse;


    @Before
    public void setup() {
        headers = new HttpHeaders();
        restTemplate = new TestRestTemplate();
        entity = new HttpEntity<>(null, headers);
    }

    @Test
    public void bruteForcewhenValidNumberAndAlgoGiven_returnPrimes() {

        response =
                restTemplate.exchange(
                        createURLWithPort("/primes/10?algorithm=BruteForce"),
                        HttpMethod.GET, entity, PrimeResponse.class);

        assertEquals(Arrays.asList(2, 3, 5, 7), response.getBody().getPrimes());
        assertEquals(Integer.valueOf(10), response.getBody().getInitial());
    }

    @Test
    public void SOEwhenValidNumberAndAlgoGiven_returnPrimes() {

        response =
                restTemplate.exchange(
                        createURLWithPort("/primes/10?algorithm=SieveOfEratosthenes"),
                        HttpMethod.GET, entity, PrimeResponse.class);

        assertEquals(Arrays.asList(2, 3, 5, 7), response.getBody().getPrimes());
        assertEquals(Integer.valueOf(10), response.getBody().getInitial());
    }


    @Test
    @Ignore
    public void whenNoAlgo_thenApiErrorResponse() {

        errorResponse =
                restTemplate.exchange(
                        createURLWithPort("/primes/10"),
                        HttpMethod.GET, entity, ApiErrorResponse.class);

        assertEquals("BAD_REQUEST", errorResponse.getBody().getError_code());
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getBody().getStatus());

    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
