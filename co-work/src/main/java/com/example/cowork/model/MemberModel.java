package com.example.cowork.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="members")
@Data
public class MemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_id;
    private String member_image;
    private String gathering_code;
    private String user_email;
    private int member_role;
    private String member_explanation;
    private Date member_joined_at;
    private Date member_leaved_at;
    private Date member_updated_at;
}
