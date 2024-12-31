package com.example.socket_io.repository;

import com.example.socket_io.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query(value = "{'_id': ?0}")
    Optional<Client> findClientByUsername(String username);

    Optional<Client> findClientBySessionID(String sessionID);

}
