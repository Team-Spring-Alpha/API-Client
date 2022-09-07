package br.com.compass.filmes.user.proxy;

import br.com.compass.filmes.user.dto.payment.request.RequestAuthDTO;
import br.com.compass.filmes.user.dto.payment.request.RequestPaymentDTO;
import br.com.compass.filmes.user.enums.UserEnum;
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
        RequestAuthDTO requestAuthDTO = new RequestAuthDTO(UserEnum.PEDRO.getClientId(), UserEnum.PEDRO.getApiKey());

        gatewayProxy.getAuthToken(UserEnum.PEDRO);

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