package com.example.cowork.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="chat")
@Data
public class ChatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chat_id;
    private String chat_session;
    private String gathering_code;
    private String content;
    private String sender;
    @Column(name="sendtime")
    private LocalDateTime sendTime;
}
