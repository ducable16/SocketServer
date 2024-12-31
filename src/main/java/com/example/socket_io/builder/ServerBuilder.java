package com.example.socket_io.builder;


import com.corundumstudio.socketio.SocketIOServer;
import com.example.socket_io.controller.ConnectSocketEvent;
import com.example.socket_io.controller.DisconnectSocketEvent;
import com.example.socket_io.controller.SendMessageEvent;
import com.example.socket_io.controller.TypingEvent;
import com.example.socket_io.entity.TypingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerBuilder {

    @Autowired
    private SocketIOServer server;
    @Autowired
    private SendMessageEvent sendMessageEvent;
    @Autowired
    private ConnectSocketEvent connectSocketEvent;
    @Autowired
    private DisconnectSocketEvent disconnectSocketEvent;
    @Autowired
    private TypingEvent typingEvent;

    public void start() {
        server.addEventListener("chat_message", String.class, sendMessageEvent);
        server.addEventListener("typing", String.class, typingEvent);
        server.addConnectListener(connectSocketEvent);
        server.addDisconnectListener(disconnectSocketEvent);
        server.start();
    }

    public void stop() {
        server.stop();
    }
}
