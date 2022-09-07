package br.com.compass.filmes.user.producer;

import br.com.compass.filmes.user.dto.allocation.history.request.RequestAllocationDTO;
import br.com.compass.filmes.user.dto.allocation.history.request.RequestAllocationMovieDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = MessageHistoryProducer.class)
class MessageHistoryProducerTest {

    @Autowired
    private MessageHistoryProducer messageHistoryProducer;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void shouldSendAMessage() {
        String routingKey = "payment.status";

        RequestAllocationMovieDTO allocationMovie = new RequestAllocationMovieDTO(1L, "movie test");
        List<RequestAllocationMovieDTO> allocationMovieList = new ArrayList<>();
        allocationMovieList.add(allocationMovie);
        RequestAllocationDTO requestAllocationDTO = new RequestAllocationDTO("idTest", "853", allocationMovieList, "approved");

        messageHistoryProducer.sendMessage(requestAllocationDTO);

        Mockito.verify(rabbitTemplate).convertAndSend(routingKey, requestAllocationDTO);

    }
}