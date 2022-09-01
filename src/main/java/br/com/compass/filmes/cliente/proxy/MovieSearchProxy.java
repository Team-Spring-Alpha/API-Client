package br.com.compass.filmes.cliente.proxy;

import br.com.compass.filmes.cliente.dto.client.response.apiMovie.ResponseApiMovieManager;
import br.com.compass.filmes.cliente.enums.GenresEnum;
import br.com.compass.filmes.cliente.enums.ProvidersEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieSearchProxy {

    @Autowired
    private MovieSearch movieManager;


    public List<ResponseApiMovieManager> getMovieSearchByFilters(GenresEnum movieGenre, LocalDate dateGte, LocalDate dateLte,
                                                                 ProvidersEnum movieProvider, List<String> moviePeoples, String movieName) {
        List<ResponseApiMovieManager> movieByFilters = movieManager.getMovieByFilters(movieGenre, dateGte, dateLte,
                                                                                      movieProvider, moviePeoples, movieName);
        return movieByFilters;
    }

    public List<ResponseApiMovieManager> getMovieByRecommendation(Long movieId) {
        List<ResponseApiMovieManager> movieByRecommendations = movieManager.getMovieByRecommendations(movieId);
        return movieByRecommendations;
    }
}
