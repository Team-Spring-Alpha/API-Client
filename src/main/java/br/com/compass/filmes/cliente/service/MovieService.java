package br.com.compass.filmes.cliente.service;

import br.com.compass.filmes.cliente.dto.client.response.apiMovie.ResponseApiMovieManager;
import br.com.compass.filmes.cliente.enums.GenresEnum;
import br.com.compass.filmes.cliente.enums.ProvidersEnum;
import br.com.compass.filmes.cliente.proxy.MovieSearchProxy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final ModelMapper modelMapper;

    @Autowired
    MovieSearchProxy movieSearchProxy;

    public List<ResponseApiMovieManager> findMoviesRecommendations(Long movieId) {
        return movieSearchProxy.getMovieByRecommendation(movieId);
    }

    public List<ResponseApiMovieManager> findByFilters(GenresEnum movieGenre, LocalDate dateGte, LocalDate dateLte, ProvidersEnum movieProvider,
                                                       List<String> moviePeoples, String movieName) {
        return movieSearchProxy.getMovieSearchByFilters(movieGenre, dateGte, dateLte, movieProvider, moviePeoples, movieName);
    }


}
