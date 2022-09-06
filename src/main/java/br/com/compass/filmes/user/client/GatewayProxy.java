package br.com.compass.filmes.user.client;

import br.com.compass.filmes.user.dto.payment.request.RequestAuth;
import br.com.compass.filmes.user.dto.payment.request.RequestPayment;
import br.com.compass.filmes.user.dto.payment.response.ResponseAuth;
import br.com.compass.filmes.user.dto.payment.response.ResponsePayment;
import br.com.compass.filmes.user.enums.UserEnum;
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
