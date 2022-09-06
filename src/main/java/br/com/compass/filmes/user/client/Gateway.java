package br.com.compass.filmes.user.client;

import br.com.compass.filmes.user.dto.payment.request.RequestAuth;
import br.com.compass.filmes.user.dto.payment.request.RequestPayment;
import br.com.compass.filmes.user.dto.payment.response.ResponseAuth;
import br.com.compass.filmes.user.dto.payment.response.ResponsePayment;
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
