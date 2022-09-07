package br.com.compass.filmes.cliente.client;

import br.com.compass.filmes.cliente.dto.payment.request.RequestAuthDTO;
import br.com.compass.filmes.cliente.dto.payment.request.RequestPaymentDTO;
import br.com.compass.filmes.cliente.dto.payment.response.ResponseAuthDTO;
import br.com.compass.filmes.cliente.dto.payment.response.ResponsePaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gateway", url = "https://pb-getway-payment.herokuapp.com/v1")
public interface Gateway {

    @GetMapping(value = "auth")
    ResponseAuthDTO getAuthToken(@RequestBody RequestAuthDTO requestAuthDTO);

    @GetMapping(value = "payments/credit-card")
    ResponsePaymentDTO getPayment(@RequestHeader("Authorization") String bearerToken, @RequestBody RequestPaymentDTO requestPaymentDTO);
}
