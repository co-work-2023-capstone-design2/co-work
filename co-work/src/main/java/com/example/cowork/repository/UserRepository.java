package com.example.cowork.repository;

import com.example.cowork.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserModel, String>{
    // 필요한 경우 사용자 정의 쿼리 추가
    @Query("SELECT u.user_name FROM UserModel u WHERE u.user_email = :userEmail")
    String findUserNameByUserEmail(@Param("userEmail") String userEmail);

}
