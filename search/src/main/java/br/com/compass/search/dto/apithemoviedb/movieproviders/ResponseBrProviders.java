package br.com.compass.search.dto.apithemoviedb.movieproviders;

import lombok.Data;

import java.util.List;

@Data
public class ResponseBrProviders {
    private String link;
    private List<ResponseApiMovieRentBuy> rent;
    private List<ResponseApiMovieRentBuy> buy;
    private List<ResponseApiMovieRentBuy> flatrate;
}
