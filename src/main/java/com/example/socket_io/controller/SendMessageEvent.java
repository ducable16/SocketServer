package com.example.socket_io.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.example.socket_io.entity.Client;
import com.example.socket_io.entity.Message;
import com.example.socket_io.entity.Status;
import com.example.socket_io.repository.ClientRepository;
import com.example.socket_io.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

@Controller
public class SendMessageEvent implements DataListener<String> {
    @Autowired
    private SocketIOServer server;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
        public void onData(SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Message chatMessage = objectMapper.readValue(data, Message.class);
        messageRepository.save(chatMessage);

        Optional<Client> result = clientRepository.findClientByUsername(chatMessage.getReceiver());
        if (!result.isPresent()) System.out.println("Client " + chatMessage.getReceiver() + " not found!");
        else if (result.get().getStatus() == Status.ONLINE) {
            String receiverSessionID = result.get().getSessionID();
            SocketIOClient recipientClient = server.getClient(UUID.fromString(receiverSessionID));
            System.out.println("Message from " + chatMessage.getSender() + " to " + chatMessage.getReceiver() + ", content: " + chatMessage.getMessage());
            recipientClient.sendEvent("chat_message", chatMessage);
        }
    }
}
