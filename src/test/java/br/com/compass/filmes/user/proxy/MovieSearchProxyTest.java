package br.com.compass.filmes.user.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = MovieSearchProxy.class)
class MovieSearchProxyTest {

    @Autowired
    private MovieSearchProxy movieSearchProxy;

    @MockBean
    private MovieSearch movieManager;

    @Test
    @DisplayName("should get movies by filters")
    void shouldGetMoviesByFilters() {
        movieSearchProxy.getMovieSearchByFilters(null, null,
                null, null, null, null);

        Mockito.verify(movieManager).getMovieByFilters(null, null,
                null, null, null, null);

    }

    @Test
    @DisplayName("should get movies reccomendations from other movie by id")
    void shouldGetMoviesRecommendationsFromOtherMovieById() {
        movieSearchProxy.getMovieByRecommendation(1L);

        Mockito.verify(movieManager).getMovieByRecommendations(1L);
    }

    @Test
    @DisplayName("should get movie info by search by id")
    void getMovieById() {
        movieSearchProxy.getMovieById(1L);

        Mockito.verify(movieManager).getMovieById(1L);
    }
}