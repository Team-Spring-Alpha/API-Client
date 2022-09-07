package br.com.compass.filmes.user.repository;

import br.com.compass.filmes.user.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
