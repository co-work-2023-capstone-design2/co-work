package com.example.cowork.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String user_email;
    private String user_pw;
}
