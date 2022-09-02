package br.com.compass.filmes.cliente.dto.apiPayment.request;

import lombok.Data;

@Data
public class RequestPaymentCustomer {
    private String documentType = "CPF";
    private String documentNumber;
}
