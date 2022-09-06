package br.com.compass.filmes.user.rabbitMq;

import br.com.compass.filmes.user.dto.apiAllocationHistory.RequestAllocation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageHistory {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(RequestAllocation requestAllocation) {
        String routingKey = "payment.status";
        rabbitTemplate.convertAndSend(routingKey, requestAllocation);
    }

}
