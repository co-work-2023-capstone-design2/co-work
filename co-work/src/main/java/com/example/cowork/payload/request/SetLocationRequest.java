package com.example.cowork.payload.request;

import lombok.Data;

@Data
public class SetLocationRequest {
    private String gathering_code;
    private int x;
    private int y;
}
