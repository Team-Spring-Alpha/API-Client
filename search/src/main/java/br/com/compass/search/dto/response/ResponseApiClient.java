package br.com.compass.search.dto.response;

import lombok.Data;

@Data
public class ResponseApiClient {
    private String title;
    private String genrers;
    private String releaseYear;
    private String overview;
    private String posterPath;
    private Double rentValue;
}
