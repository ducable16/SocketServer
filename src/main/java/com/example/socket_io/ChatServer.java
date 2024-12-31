package com.example.socket_io;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.socket_io.builder.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatServer {

    @Autowired
    private ServerBuilder serverBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ChatServer.class, args);
    }

//    @Bean
//    public CommandLineRunner start(SocketIOServer socketIOServer) {
//        return args -> {
//          //  socketIOServer.start();
//        };
//    }
}