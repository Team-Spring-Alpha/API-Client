package br.com.compass.filmes.cliente.proxy;

import br.com.compass.filmes.cliente.dto.apiPayment.request.RequestAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.request.RequestPayment;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponseAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponsePayment;
import br.com.compass.filmes.cliente.enums.UserEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GatewayProxy {


    @Autowired
    private Gateway gateway;

    public ResponseAuth getAuthToken(UserEnum userEnum) {
        RequestAuth requestAuth = new RequestAuth(userEnum.getClientId(), userEnum.getApiKey());
        return gateway.getAuthToken(requestAuth);
    }

    public ResponsePayment getPayment(String bearerToken, RequestPayment requestPayment) {
        return gateway.getPayment("Bearer " + bearerToken, requestPayment);
    }
}
