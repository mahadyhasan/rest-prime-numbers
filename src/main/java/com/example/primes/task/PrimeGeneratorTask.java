package com.example.primes.task;

import com.example.primes.algo.PrimeAlgorithm.PrimesStrategy;
import com.example.primes.domain.PrimeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class PrimeGeneratorTask {

    BlockingQueue<PrimeResponse> sharedQueue = new LinkedBlockingQueue<>();


    public void queueForProcessing(PrimeResponse primeResponse) {
        try {
            this.sharedQueue.put(primeResponse);
            log.info("Queued {}", primeResponse);
        } catch (InterruptedException e) {
            e.printStackTrace();
            primeResponse.setErrorMessage(e.getMessage());
        }
    }

    @PostConstruct
    public void initIt() {
        new Thread(new Consumer(this.sharedQueue))
                .start();
        log.info("Initialized PrimeGeneratorTask");
    }

    private class Consumer implements Runnable {

        private final BlockingQueue<PrimeResponse> sharedQueue;

        public Consumer(BlockingQueue<PrimeResponse> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            while (true) {
                PrimeResponse primesResult = null;
                try {
                    primesResult = sharedQueue.take();
                    log.info("Processing {}", primesResult);
                    Long startTime = System.nanoTime();
                    List<Long> primes = generatePrimes(primesResult.getUpperLimit(), primesResult.getPrimesStrategy());
                    Long endTime = System.nanoTime();
                    primesResult.addAll(primes);
                    primesResult.setProcessingTimeInNanoSeconds(endTime - startTime);
                    log.info("Processed {}", primesResult);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    primesResult.setErrorMessage(e.getMessage());
                }
            }
        }

        private List<Long> generatePrimes(Long upperLimit, PrimesStrategy primesStrategy) {
            return null;
        }
    }

}
