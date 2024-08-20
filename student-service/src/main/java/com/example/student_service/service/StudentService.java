package com.example.student_service.service;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    ReactiveCircuitBreaker reactiveCircuitBreaker;

    public StudentService(ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory)
    {
        this.reactiveCircuitBreaker = reactiveResilience4JCircuitBreakerFactory.create("libraryService");
    }

    public Mono<String> getLibraryBook_Existing() {
        return WebClient.create().get().uri("http://localhost:8902/library/borrowBook")
                .retrieve().bodyToMono(String.class);
    }

    public Mono<String> getLibraryBook() {
           return this.reactiveCircuitBreaker.run(WebClient.create().get().uri("http://localhost:8902/library/borrowBook")
           .retrieve().bodyToMono(String.class), throwable-> {
           System.out.println("Some error occurred in library service : "+ throwable.getMessage());
           return fromFallbackLibrary();
        });
    }

    private Mono<String> fromFallbackLibrary() {
        return Mono.just("Book got issued from the Fallback Library ");
    }


}
