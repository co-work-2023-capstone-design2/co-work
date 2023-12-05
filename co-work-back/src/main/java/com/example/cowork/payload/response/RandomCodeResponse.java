package com.example.cowork.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RandomCodeResponse {
    private Integer state;
    private String gathering_code;
}
