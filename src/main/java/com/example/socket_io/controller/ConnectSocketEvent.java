package com.example.socket_io.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.example.socket_io.entity.Client;
import com.example.socket_io.entity.Status;
import com.example.socket_io.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectSocketEvent implements ConnectListener {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        String username = socketIOClient.getHandshakeData().getUrlParams().get("username").getFirst();
        clientRepository.save(new Client(username, socketIOClient.getSessionId().toString(), Status.ONLINE ));
        System.out.println("Client " + username + " connected with sessionID: " + socketIOClient.getSessionId());
    }
}
