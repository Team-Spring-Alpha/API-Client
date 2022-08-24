package br.com.compass.search.dto.apithemoviedb.movieproviders;

import lombok.Data;

@Data
public class ResponseApiMovieProviders {
    private Long id;
    private ResponseApiMovieProvidersResults results;
}
