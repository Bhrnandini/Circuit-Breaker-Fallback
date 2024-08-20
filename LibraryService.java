package com.example.library_service.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LibraryService {

    public Mono<String> borrowBook() {
        return Mono.just("Book got issued from the Library");
    }
}
