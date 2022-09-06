package br.com.compass.filmes.cliente.repository;

import br.com.compass.filmes.cliente.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByEmail(@Param("email") String email);
}
