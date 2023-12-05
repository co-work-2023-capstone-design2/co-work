package com.example.cowork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GatheringInfoResponse {
    private int state;
    private String gathering_code;
    private String gathering_name;
    private String gathering_owner;
    private String gathering_explanation;
}
