package com.example.tdd;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.tdd.Movie.Movie;
import com.example.tdd.Movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MovieTests {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    MovieRepository movieRepository;

    @Test
    void shouldReturnAMovie() {
        final var movieName = "Valimai";
        final var value = new Movie();
        value.setName(movieName);
        when(movieRepository.findByName(movieName)).thenReturn(value);

        final var response = restTemplate.getForEntity("/movies/" + movieName, Movie.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(movieName);
    }

    @Test
    void shouldReturnNotFoundIfMovieDoesNotExist() {
        final var movieName = "Beast";
        when(movieRepository.findByName(movieName)).thenReturn(null);

        final var response = restTemplate.getForEntity(format("/movies/%s", movieName) + movieName,
            Error.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("Movie doesn't exist");
    }

}
