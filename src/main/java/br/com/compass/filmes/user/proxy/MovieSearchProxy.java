package br.com.compass.filmes.user.proxy;

import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseApiMovieManagerDTO;
import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseMovieByIdDTO;
import br.com.compass.filmes.user.enums.GenresEnum;
import br.com.compass.filmes.user.enums.ProvidersEnum;
import br.com.compass.filmes.user.exceptions.MovieNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovieSearchProxy {

    @Autowired
    private MovieSearch movieManager;


    public List<ResponseApiMovieManagerDTO> getMovieSearchByFilters(GenresEnum movieGenre, LocalDate dateGte, LocalDate dateLte,
                                                                    ProvidersEnum movieProvider, List<String> moviePeoples, String movieName) {
        try {
            return movieManager.getMovieByFilters(movieGenre, dateGte, dateLte, movieProvider, moviePeoples, movieName);
        } catch (FeignException.FeignClientException.NotFound exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<ResponseApiMovieManagerDTO> getMovieByRecommendation(Long movieId) {
        try {
            return movieManager.getMovieByRecommendations(movieId);
        } catch (FeignException.FeignClientException.NotFound exception) {
            throw new MovieNotFoundException("movie id: " + movieId);
        }
    }

    public ResponseMovieByIdDTO getMovieById(Long movieId){
        try {
            return movieManager.getMovieById(movieId);
        } catch (FeignException.FeignClientException.NotFound exception) {
            throw new MovieNotFoundException("movie id: " + movieId);
        }
    }
}
