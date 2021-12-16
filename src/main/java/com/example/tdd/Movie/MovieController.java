package com.example.tdd.Movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies/{movieName}")
    ResponseEntity movieBy(@PathVariable String movieName) {
        final var maybeMovie = movieRepository.findByName(movieName);
        if (maybeMovie == null) {
            return new ResponseEntity(new Error("Movie doesn't exist"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(maybeMovie);
    }

}
