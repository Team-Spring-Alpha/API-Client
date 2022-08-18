package br.com.compass.filmes.cliente.repository;

import br.com.compass.filmes.cliente.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}
