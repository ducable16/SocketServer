package com.example.socket_io.controller;

import com.example.socket_io.entity.Client;
import com.example.socket_io.entity.Message;
import com.example.socket_io.repository.ClientRepository;
import com.example.socket_io.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/sent-messages")
    public ResponseEntity<?> getSentMessages(@RequestParam("username") String username) {
        Optional<List<Message>> messages = messageRepository.findMessageBySender(username);
        if(!messages.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messages.get());
    }

    @GetMapping("/chat-messages")
    public ResponseEntity<?> getChatMessages(@RequestParam String username1, @RequestParam String username2) {
        Optional<List<Message>> messages = messageRepository.findMessagesBySenderAndReceiver(username1, username2);
        if(!messages.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messages.get());
    }


}
