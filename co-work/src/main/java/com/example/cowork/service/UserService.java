package com.example.cowork.service;

import com.example.cowork.model.UserModel;
import com.example.cowork.payload.response.MessageResponse;
import com.example.cowork.payload.response.StateResponse;
import com.example.cowork.repository.UserRepository;
import com.example.cowork.etcData.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.persistence.PersistenceContext;
//import javax.persistence.EntityManager;
//import java.sql.Date;
//import java.time.LocalDate;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @PersistenceContext
//    private EntityManager entityManager;

    public ResponseEntity<?> login(String user_email, String user_pw){
        UserModel user = userRepository.findById(user_email).orElse(null);

        if (user != null && user_pw.equals(user.getUser_pw())){
            // 로그인 성공
            return ResponseEntity
                    .ok(new StateResponse(200));
        } else {
            // 로그인 실패;
            return ResponseEntity
                    .badRequest()
                    .body(new StateResponse(400));
        }
    }

    @Transactional
    public ResponseEntity<?> signup(String user_email, String user_name, String user_pw) {

        // 사용자가 이미 존재하는지 확인
        if (userRepository.existsById(user_email)){
            // 이미 존재하는 경우 회원가입 실패
            return ResponseEntity
                    .badRequest()
                    .body(new StateResponse(400));
        }

      /*  System.out.println("확인용");
        System.out.println("user_email: " + user_email);
        System.out.println("user_pw: " + user_pw);*/

        // 사용자 생성
        UserModel newUser = new UserModel();
        newUser.setUser_email(user_email);
        newUser.setUser_name(user_name);
        newUser.setUser_pw(user_pw);
        newUser.setUser_created_at(Date.getCurrentDate());

        // 데이터베이스에 사용자 추가
        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(400, "db 저장 error"));
        }


        // 추가된 사용자 모델 반환
        return ResponseEntity
                .ok(new StateResponse(200));
    }
}
