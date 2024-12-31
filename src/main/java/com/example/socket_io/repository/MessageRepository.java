package com.example.socket_io.repository;

import com.example.socket_io.entity.Message;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Query(value = "{'sender': ?0}")
    Optional<List<Message>> findMessageBySender(String sender);

    Optional<List<Message>> findMessagesByReceiver(String receiver);

    @Query(value = "{ $or: [ { 'sender': ?0, 'receiver': ?1 }, { 'sender': ?1, 'receiver': ?0 } ] }")
    Optional<List<Message>> findMessagesBySenderAndReceiver(String username1, String username2);
}
