package com.example.socket_io.controller;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import com.example.socket_io.entity.Client;
import com.example.socket_io.entity.Message;
import com.example.socket_io.entity.Status;
import com.example.socket_io.entity.TypingData;
import com.example.socket_io.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class TypingEvent implements DataListener<String> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SocketIOServer server;

    @Override
    public void onData (SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TypingData typingData = objectMapper.readValue(data, TypingData.class);
        System.out.println(typingData.getSender() + " is typing...");

        Optional<Client> result = clientRepository.findClientByUsername(typingData.getReceiver());
        if (result.isPresent() && result.get().getStatus() == Status.ONLINE) {
            String receiverSessionID = result.get().getSessionID();
            SocketIOClient recipientClient = server.getClient(UUID.fromString(receiverSessionID));
            recipientClient.sendEvent("typing", typingData);
        }
    };
}


