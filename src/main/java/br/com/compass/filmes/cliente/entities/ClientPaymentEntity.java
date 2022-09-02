package br.com.compass.filmes.cliente.entities;

import br.com.compass.filmes.cliente.dto.client.request.apiMovieManager.RequestRentOrBuy;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "ClientPayment")
public class ClientPaymentEntity {

    private RequestRentOrBuy movies;
    private String creditCardNumber;

}
