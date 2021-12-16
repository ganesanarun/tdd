package com.example.tdd.Movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movies/{movieName}")
    ResponseEntity<Movie> movieBy(@PathVariable String movieName) {
        return ResponseEntity.ok(new Movie(movieName));
    }

}
