package br.com.compass.filmes.cliente.dto.user.response.apiMovie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMovieById {
    private Long id;
    @JsonProperty("movie_name")
    private String movieName;
    @JsonProperty("just_watch")
    private ResponseJustWatch justWatch;
}
