package br.com.compass.filmes.cliente.proxy;

import br.com.compass.filmes.cliente.dto.apiMovie.ResponseApiMovieManager;
import br.com.compass.filmes.cliente.dto.apiMovie.ResponseMovieById;
import br.com.compass.filmes.cliente.enums.GenresEnum;
import br.com.compass.filmes.cliente.enums.ProvidersEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(value = "movieManager", url = "${custom.movie-manager-url}")
public interface MovieSearch {
    @GetMapping(value = "/{movieId}/recommendations")
    List<ResponseApiMovieManager> getMovieByRecommendations(@PathVariable("movieId") Long movieId);

    @GetMapping(value = "/movie-search")
    List<ResponseApiMovieManager> getMovieByFilters(@RequestParam(name = "movie_genrer", required = false) GenresEnum movieGenre,
                                                    @RequestParam(name = "release_date_after", required = false)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDateAfter,
                                                    @RequestParam(name = "release_date_before", required = false)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDateBefore,
                                                    @RequestParam(name = "movie_provider", required = false) ProvidersEnum movieProvider,
                                                    @RequestParam(name = "movie_peoples", required = false) List<String> moviePeoples,
                                                    @RequestParam(name = "movie_name", required = false) String movieName);

    @GetMapping(value = "/movie/{movieId}")
    ResponseMovieById getMovieById(@PathVariable("movieId") Long movieId);
}
