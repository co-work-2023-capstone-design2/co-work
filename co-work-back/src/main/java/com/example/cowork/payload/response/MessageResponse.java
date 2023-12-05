package com.example.cowork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {
    private int state;
    private String message;
}
