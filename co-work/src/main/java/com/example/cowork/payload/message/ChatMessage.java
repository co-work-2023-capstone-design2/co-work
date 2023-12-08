package com.example.cowork.payload.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private String gathering_code;
    private String content;
    private String sender;
    private String sender_name;
    private LocalDateTime sendTime;
}
