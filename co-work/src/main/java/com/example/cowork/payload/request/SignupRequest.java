package com.example.cowork.payload.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String user_email;
    private String user_name;
    private String user_pw;
}
