package br.com.compass.filmes.cliente.proxy;

import br.com.compass.filmes.cliente.dto.apiPayment.request.RequestAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.request.RequestPayment;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponseAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponsePayment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gateway", url = "https://pb-getway-payment.herokuapp.com/v1")
public interface Gateway {

    @GetMapping(value = "auth")
    ResponseAuth getAuthToken(@RequestBody RequestAuth requestAuth);

    @GetMapping(value = "payments/credit-card")
    ResponsePayment getPayment(@RequestHeader("Authorization") String bearerToken, @RequestBody RequestPayment requestPayment);
}
