package com.example.cowork.service;

import com.example.cowork.model.MemberModel;
import com.example.cowork.payload.response.MessageResponse;
import com.example.cowork.payload.response.StateResponse;
import com.example.cowork.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public ResponseEntity<?> createCharacter(
            String gathering_code,
            String user_email,
            String user_image,
            Integer member_role,
            String user_explanation
    ){
        MemberModel newMember = new MemberModel();
        newMember.setGathering_code(gathering_code);
        newMember.setUser_email(user_email);
        newMember.setMember_image(user_image);
        newMember.setMember_role(member_role);
        newMember.setMember_explanation(user_explanation);

        try {
            memberRepository.save(newMember);
        }catch(DataIntegrityViolationException e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(400, "db 저장 error"));
        }

        return ResponseEntity.ok(new StateResponse(200));
    }
}
