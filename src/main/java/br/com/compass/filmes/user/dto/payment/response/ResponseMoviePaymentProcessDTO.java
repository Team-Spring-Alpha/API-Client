package br.com.compass.filmes.user.dto.payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMoviePaymentProcessDTO {
    private Long movieId;
    private String title;
    private String link;
    private String store;
}
