package br.com.compass.filmes.cliente.proxy;

import br.com.compass.filmes.cliente.dto.apiPayment.request.RequestAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.request.RequestPayment;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponseAuth;
import br.com.compass.filmes.cliente.dto.apiPayment.response.ResponsePayment;
import br.com.compass.filmes.cliente.enums.PaymentVendorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GatewayProxy {


    @Autowired
    private Gateway gateway;

    public ResponseAuth getAuthToken(PaymentVendorEnum paymentVendorEnum) {
        RequestAuth requestAuth = new RequestAuth(paymentVendorEnum.getClientId(), paymentVendorEnum.getApiKey());
        return gateway.getAuthToken(requestAuth);
    }

    public ResponsePayment getPayment(String bearerToken, RequestPayment requestPayment) {
        return gateway.getPayment("Bearer " + bearerToken, requestPayment);
    }
}
