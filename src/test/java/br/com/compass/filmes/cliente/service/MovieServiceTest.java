package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.client.MovieSearchProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = MovieService.class)
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieSearchProxy movieSearchProxy;

    @Test
    @DisplayName("should get movies by recommendation")
    void shouldGetMovieByRecommendation() {
        movieService.findMoviesRecommendations(2L);
        Mockito.verify(movieSearchProxy).getMovieByRecommendation(2L);
    }

    @Test
    void findByFilters() {
        movieService.findByFilters(null, null, null, null, null, null);
        Mockito.verify(movieSearchProxy).getMovieSearchByFilters(null, null, null, null, null, null);
    }
}