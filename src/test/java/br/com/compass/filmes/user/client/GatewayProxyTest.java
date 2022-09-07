package br.com.compass.filmes.cliente.client;

import br.com.compass.filmes.cliente.dto.payment.request.RequestAuthDTO;
import br.com.compass.filmes.cliente.dto.payment.request.RequestPaymentDTO;
import br.com.compass.filmes.cliente.enums.PaymentVendorEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = GatewayProxy.class)
class GatewayProxyTest {
    @Autowired
    private GatewayProxy gatewayProxy;

    @MockBean
    private Gateway gateway;

    @Test
    @DisplayName("should get auth token sucessful")
    void shouldGetAuthTokenSucessful() {
        RequestAuthDTO requestAuthDTO = new RequestAuthDTO(PaymentVendorEnum.PEDRO.getClientId(), PaymentVendorEnum.PEDRO.getApiKey());

        gatewayProxy.getAuthToken(PaymentVendorEnum.PEDRO);

        Mockito.verify(gateway).getAuthToken(requestAuthDTO);
    }

    @Test
    @DisplayName("should get payment from gateway")
    void shouldGetPaymentFromGateWay() {
        RequestPaymentDTO requestPaymentDTO = new RequestPaymentDTO();
        gatewayProxy.getPayment("test", requestPaymentDTO);

        Mockito.verify(gateway).getPayment("Bearer test", requestPaymentDTO);
    }
}