package br.com.compass.filmes.user.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMovieByIdDTO {
    private Long id;
    @JsonProperty("movie_name")
    private String movieName;
    @JsonProperty("just_watch")
    private ResponseJustWatchDTO justWatch;
}
