package br.com.compass.filmes.user.dto.moviepayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RequestMoviePaymentDTO {
    @JsonProperty("movies_buy")
    private List<RequestRentOrBuyDTO> moviesBuy;
    @JsonProperty("movies_rent")
    private List<RequestRentOrBuyDTO> moviesRent;
    @CreditCardNumber
    @NotBlank
    @JsonProperty("credit_card_number")
    private String creditCardNumber;
}
