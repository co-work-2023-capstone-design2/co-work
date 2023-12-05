package com.example.cowork.payload.request;

import lombok.Data;

@Data
public class CreateCharacterRequest {
    private String gathering_code;
    private String user_email;
    private String user_image;
    private Integer member_role;
    private String user_explanation;
}
