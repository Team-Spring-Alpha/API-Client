package br.com.compass.search.dto.apithemoviedb.moviecredits;

import lombok.Data;

import java.util.List;

@Data
public class ResponseApiMovieCredits {
    private Long id;
    private List<ResponseApiMovieCreditsCast> cast;
    private List<ResponseApiMovieCreditsCrew> crew;
}
