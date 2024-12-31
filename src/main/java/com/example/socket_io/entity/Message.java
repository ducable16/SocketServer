package com.example.socket_io.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message {

    private String message;
    private String sender;
    private String receiver;

    public Message() {
        super();
    }
    public Message(String message, String sender, String receiver) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
