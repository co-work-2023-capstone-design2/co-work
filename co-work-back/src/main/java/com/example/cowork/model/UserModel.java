package com.example.cowork.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.sql.Date;

@Entity
@Table(name = "users")  // 테이블명 지정
@Data
public class UserModel {
    @Id
    private String user_email;
    private String user_name;
    private String user_pw;
    private Date user_created_at;
    private Date user_updated_at;
    private Date user_deleted_at;
}
