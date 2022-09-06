package br.com.compass.filmes.user.client;

import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseApiMovieManagerDTO;
import br.com.compass.filmes.user.dto.user.response.apiMovie.ResponseMovieByIdDTO;
import br.com.compass.filmes.user.enums.GenresEnum;
import br.com.compass.filmes.user.enums.ProvidersEnum;
import br.com.compass.filmes.user.exceptions.MovieNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovieSearchProxy {

    @Autowired
    private MovieSearch movieManager;


    public List<ResponseApiMovieManagerDTO> getMovieSearchByFilters(GenresEnum movieGenre, LocalDate dateGte, LocalDate dateLte,
                                                                    ProvidersEnum movieProvider, List<String> moviePeoples, String movieName) {
        return movieManager.getMovieByFilters(movieGenre, dateGte, dateLte, movieProvider, moviePeoples, movieName);
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
