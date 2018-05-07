# Prime Number Service

This is a Spring boot REST Application which calculates prime numbers up to a given limit.
Prime numbers can be generated via multiple supported algorithms:

1) Brute Force Prime Algorithm

2) Sieve Of Eratosthenes Prime Algorithm


# Build Procedure

1. Install Maven
2. Install Git
3. Set JAVA_HOME tp an available JDK 8
4. Clone repository from Git
5. Go to parent folder & run maven command `mvn clean install`

# Prime Numbers Request

### Generate Prime Numbers with Brute Force
```
http://localhost:8081/primes/10?algorithm=BruteForce
```

### Generate Prime Numbers with Sieve Of Eratosthenes
```
http://localhost:8081/primes/100?algorithm=SieveOfEratosthenes
```