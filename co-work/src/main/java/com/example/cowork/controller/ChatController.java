package com.example.cowork.controller;

import com.example.cowork.payload.request.GatheringCodeRequest;
import com.example.cowork.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    public ChatService chatService;

    @PostMapping("/addGatheringList")
    public ResponseEntity<?> addGatheringList (@RequestBody GatheringCodeRequest codeRequest){
        return chatService.createChatRoom(codeRequest.getGathering_code());
    }


}
