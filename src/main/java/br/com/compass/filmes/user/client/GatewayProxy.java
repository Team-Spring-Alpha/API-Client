package br.com.compass.filmes.cliente.client;

import br.com.compass.filmes.cliente.dto.payment.request.RequestAuthDTO;
import br.com.compass.filmes.cliente.dto.payment.request.RequestPaymentDTO;
import br.com.compass.filmes.cliente.dto.payment.response.ResponseAuthDTO;
import br.com.compass.filmes.cliente.dto.payment.response.ResponsePaymentDTO;
import br.com.compass.filmes.cliente.enums.PaymentVendorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GatewayProxy {


    @Autowired
    private Gateway gateway;

    public ResponseAuthDTO getAuthToken(PaymentVendorEnum paymentVendorEnum) {
        RequestAuthDTO requestAuthDTO = new RequestAuthDTO(paymentVendorEnum.getClientId(), paymentVendorEnum.getApiKey());
        return gateway.getAuthToken(requestAuthDTO);
    }

    public ResponsePaymentDTO getPayment(String bearerToken, RequestPaymentDTO requestPaymentDTO) {
        return gateway.getPayment("Bearer " + bearerToken, requestPaymentDTO);
    }
}
