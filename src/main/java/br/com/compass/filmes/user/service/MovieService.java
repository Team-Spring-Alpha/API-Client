package br.com.compass.filmes.user.service;

import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseApiMovieManagerDTO;
import br.com.compass.filmes.user.enums.GenresEnum;
import br.com.compass.filmes.user.enums.ProvidersEnum;
import br.com.compass.filmes.user.client.MovieSearchProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieSearchProxy movieSearchProxy;

    public List<ResponseApiMovieManagerDTO> findMoviesRecommendations(Long movieId) {
        return movieSearchProxy.getMovieByRecommendation(movieId);
    }

    public List<ResponseApiMovieManagerDTO> findByFilters(GenresEnum movieGenre, LocalDate dateGte, LocalDate dateLte, ProvidersEnum movieProvider,
                                                          List<String> moviePeoples, String movieName) {
        return movieSearchProxy.getMovieSearchByFilters(movieGenre, dateGte, dateLte, movieProvider, moviePeoples, movieName);
    }
}
