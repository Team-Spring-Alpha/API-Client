package br.com.compass.search.service;

import br.com.compass.search.dto.apiclient.response.ResponseApiClient;
import br.com.compass.search.dto.apiclient.response.ResponseMovieInformation;
import br.com.compass.search.dto.apithemoviedb.searchbyname.ResponseApiSearchByName;
import br.com.compass.search.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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
}
