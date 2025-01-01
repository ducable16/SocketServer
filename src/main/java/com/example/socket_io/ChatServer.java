package com.example.socket_io;

import com.example.socket_io.builder.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatServer implements CommandLineRunner {

    @Autowired
    private ServerBuilder serverBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ChatServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        serverBuilder.start();
    }
}