package br.com.compass.filmes.cliente.repository;

import br.com.compass.filmes.cliente.entities.ClientPaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientPaymentRepository extends MongoRepository<ClientPaymentEntity, String>{
}
