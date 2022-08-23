package br.com.compass.search.service;

import br.com.compass.search.dto.response.ResponseApiClient;
import br.com.compass.search.dto.response.ResponseApiSearchByName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final WebClient.Builder webBuider;

    @Value("${API_KEY}")
    private String apiKey;

    public ResponseApiClient findByName(String movieName) {
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
