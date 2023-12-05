package com.example.cowork.controller;

import com.example.cowork.payload.request.CreateCharacterRequest;
import com.example.cowork.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/createCharacter")
    public ResponseEntity<?> createCharacter(@RequestBody CreateCharacterRequest createCharacterRequest){
        return memberService.createCharacter(
                createCharacterRequest.getGathering_code(),
                createCharacterRequest.getUser_email(),
                createCharacterRequest.getUser_image(),
                createCharacterRequest.getMember_role(),
                createCharacterRequest.getUser_explanation()
        );

    }
}
