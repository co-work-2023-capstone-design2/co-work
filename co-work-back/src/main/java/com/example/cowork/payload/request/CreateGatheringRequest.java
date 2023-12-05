package com.example.cowork.payload.request;

import lombok.Data;

@Data
public class CreateGatheringRequest {
    private String gathering_code;
    private String gathering_exterior;
    private String gathering_name;
    private String gathering_owner;
    private int gathering_floor;
    private String gathering_explanation;
}
