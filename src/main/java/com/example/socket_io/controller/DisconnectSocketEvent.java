package com.example.socket_io.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.socket_io.entity.Client;
import com.example.socket_io.entity.Status;
import com.example.socket_io.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DisconnectSocketEvent implements DisconnectListener {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        String username = clientRepository.findClientBySessionID(socketIOClient.getSessionId().toString()).get().getUsername();
        clientRepository.save(new Client(username, socketIOClient.getSessionId().toString(), Status.OFFLINE));
        System.out.println("Client " + username + " disconnected:");
    }
}
