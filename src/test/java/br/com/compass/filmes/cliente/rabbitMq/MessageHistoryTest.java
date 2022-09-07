package br.com.compass.filmes.cliente.rabbitMq;

import br.com.compass.filmes.cliente.dto.apiAllocationHistory.request.RequestAllocationDTO;
import br.com.compass.filmes.cliente.dto.apiAllocationHistory.request.RequestAllocationMovieDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = MessageHistory.class)
class MessageHistoryTest {

    @Autowired
    private MessageHistory messageHistory;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void shouldSendAMessage() {
        String routingKey = "payment.status";

        RequestAllocationMovieDTO allocationMovie = new RequestAllocationMovieDTO(1L, "movie test");
        List<RequestAllocationMovieDTO> allocationMovieList = new ArrayList<>();
        allocationMovieList.add(allocationMovie);
        RequestAllocationDTO requestAllocationDTO = new RequestAllocationDTO("idTest", "853", allocationMovieList, "approved");

        messageHistory.sendMessage(requestAllocationDTO);

        Mockito.verify(rabbitTemplate).convertAndSend(routingKey, requestAllocationDTO);

    }
}