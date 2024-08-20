package com.example.library_service.controller;

import com.example.library_service.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/library")
public class LibraryController
{
    @Autowired
    LibraryService libraryService;

    @GetMapping("/borrowBook")
    public Mono<String> borrowBook() {
        return libraryService.borrowBook();
    }
}
