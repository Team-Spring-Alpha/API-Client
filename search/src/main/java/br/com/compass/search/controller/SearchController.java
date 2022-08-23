package br.com.compass.search.controller;


import br.com.compass.search.dto.response.ResponseApiClient;
import br.com.compass.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/movie-name")
    public ResponseEntity<ResponseApiClient> getMovieByName(@RequestParam String movieName) {
        ResponseApiClient responseApiClient = searchService.findByName(movieName);
        return ResponseEntity.ok(responseApiClient);
    }
}
