package com.example.cowork.repository;

import com.example.cowork.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String>{
    // 필요한 경우 사용자 정의 쿼리 추가
}
