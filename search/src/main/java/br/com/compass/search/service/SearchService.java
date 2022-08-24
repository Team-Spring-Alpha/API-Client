package br.com.compass.search.service;

import br.com.compass.search.dto.apiclient.response.ResponseApiClient;
import br.com.compass.search.dto.apithemoviedb.searchbyname.ResponseApiSearchByName;
import br.com.compass.search.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final WebClient.Builder webBuider;

    private final ModelMapperUtils modelMapperUtils;

    @Value("${API_KEY}")
    private String apiKey;

    public List<ResponseApiClient> findByName(String movieName) {
        ResponseApiSearchByName responseApiSearchByName = webBuider.build().get().uri(uriBuilder -> uriBuilder
                .scheme("https").host("api.themoviedb.org")
                .path("/3/search/movie")
                .queryParam("language", "pt-BR")
                .queryParam("api_key", apiKey)
                .queryParam("include_adult", false)
                .queryParam("page", 1)
                .queryParam("query", movieName).build()).retrieve().bodyToMono(ResponseApiSearchByName.class).block();

        return modelMapperUtils.responseSearchByNameToApiClient(responseApiSearchByName);
    }

    public ResponseApiClient showMovieInfo(String movieName) {
        ResponseApiSearchByName responseApiSearchByName = webBuider.build().get().uri(uriBuilder -> uriBuilder
                .scheme("https").host("api.themoviedb.org")
                .path("/3/search/movie")
                .queryParam("language", "pt-BR")
                .queryParam("api_key", apiKey)
                .queryParam("include_adult", false)
                .queryParam("page", 1)
                .queryParam("query", movieName).build()).retrieve().bodyToMono(ResponseApiSearchByName.class).block();

       ResponseApiClient responseApiClient = new ResponseApiClient();
       return responseApiClient;
    }

    public List<ResponseApiClient> findMoviesRecommendations(Long movieId) {
        ResponseApiSearchByName responseApiSearchByName = webBuider.build()
                .get().uri(uriBuilder -> uriBuilder
                .scheme("https").host("api.themoviedb.org")
                .path("/3/movie/" + movieId + "/recommendations")
                .queryParam("api_key", apiKey)
                .queryParam("language", "pt-BR")
                .queryParam("page", 1)
                .build()).retrieve()
                .bodyToMono(ResponseApiSearchByName.class)
                .block();

        return modelMapperUtils.responseSearchByNameToApiClient(responseApiSearchByName);
    }

    public List<ResponseApiClient> findByGenre(Long movieGenre) {
        ResponseApiSearchByName responseApiSearchByName = webBuider.build().get().uri(uriBuilder -> uriBuilder
                .scheme("https").host("api.themoviedb.org")
                .path("/3/discover/movie")
                .queryParam("language", "pt-BR")
                .queryParam("api_key", apiKey)
                .queryParam("include_adult", false)
                .queryParam("page", 1)
                .queryParam("with_genres", movieGenre)
                .build()).retrieve().bodyToMono(ResponseApiSearchByName.class).block();

        return modelMapperUtils.responseSearchByNameToApiClient(responseApiSearchByName);
    }

    public List<ResponseApiClient> findByDate(LocalDate dateGte, LocalDate dateLte) {
        ResponseApiSearchByName responseApiSearchByName = webBuider.build().get().uri(uriBuilder -> uriBuilder
                        .scheme("https").host("api.themoviedb.org")
                        .path("/3/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "pt-BR")
                        .queryParam("include_adult", false)
                        .queryParam("page", 1)
                        .queryParamIfPresent("primary_release_date.gte", Optional.ofNullable(dateGte))
                        .queryParamIfPresent("primary_release_date.lte", Optional.ofNullable(dateLte))
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros")))
                .bodyToMono(ResponseApiSearchByName.class)
                .block();
        return modelMapperUtils.responseSearchByNameToApiClient(responseApiSearchByName);
    }
}
