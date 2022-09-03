package br.com.compass.filmes.cliente.dto.client.response.apiMovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseMovieById {

    private Long id;
    @JsonProperty("movie_name")
    private String movieName;
    @JsonProperty("just_watch")
    private ResponseJustWatch justWatch;
}
