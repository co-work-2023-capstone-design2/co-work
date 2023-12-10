package com.example.cowork.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Data
public class RolesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roles_id;
    String roles_name;
}
