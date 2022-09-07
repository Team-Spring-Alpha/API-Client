package br.com.compass.filmes.cliente.repository;

import br.com.compass.filmes.cliente.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
