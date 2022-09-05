package br.com.compass.filmes.cliente.repository;

import br.com.compass.filmes.cliente.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {

    ClientEntity findByClientEmail(@Param("email") String email);
}
