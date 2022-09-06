package br.com.compass.filmes.user.rabbitMq;

import br.com.compass.filmes.user.dto.apiAllocationHistory.RequestAllocation;
import br.com.compass.filmes.user.dto.apiAllocationHistory.RequestAllocationMovie;
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

        RequestAllocationMovie allocationMovie = new RequestAllocationMovie(1L, "movie test");
        List<RequestAllocationMovie> allocationMovieList = new ArrayList<>();
        allocationMovieList.add(allocationMovie);
        RequestAllocation requestAllocation = new RequestAllocation("idTest", "853", allocationMovieList, "approved");

        messageHistory.sendMessage(requestAllocation);

        Mockito.verify(rabbitTemplate).convertAndSend(routingKey, requestAllocation);

    }
}